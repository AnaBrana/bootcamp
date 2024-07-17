package com.example.application.resources;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.ActorShort;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Value;

@WebMvcTest(ActorResource.class)
class ActorResourceTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private ActorService actorService;

	@Autowired
	ObjectMapper objectMapper;
	
	
	
	@Value
	static class ActorShortMock implements ActorShort {
		int id;
		String nombre;
	
		@Override
		public String getApellidos() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	@Test
	void getAllStringTest() throws Exception {
		List<ActorShort> lista = new ArrayList<>(
		        Arrays.asList(new ActorShortMock(1, "Ana BRAÑA"),
		        		new ActorShortMock(2, "Carmen Maura"),
		        		new ActorShortMock(3, "Keanu Reeves")));
		when(actorService.getByProjection(ActorShort.class)).thenReturn(lista);
		mockMvc.perform(get("/api/actores/v1?modo=short").accept(MediaType.APPLICATION_JSON))
			.andExpectAll(
					status().isOk(), 
					content().contentType("application/json"),
					jsonPath("$.size()").value(3)
					);

	}

	@Test
	void getAllPageableTest() throws Exception {
		List<ActorShort> lista = new ArrayList<>(
		        Arrays.asList(new ActorShortMock(1, "Ana BRAÑA"),
		        		new ActorShortMock(2, "Carmen Maura"),
		        		new ActorShortMock(3, "Keanu Reeves")));

		when(actorService.getByProjection(PageRequest.of(0, 20), ActorShort.class))
			.thenReturn(new PageImpl<>(lista));
		mockMvc.perform(get("/api/actores/v1").queryParam("page", "0"))
			.andExpectAll(
				status().isOk(), 
				content().contentType("application/json"),
				jsonPath("$.content.size()").value(3),
				jsonPath("$.size").value(3)
				);
	}

	@Test
	void getByIdTest() throws Exception {
		int id = 1;
		var ele = new Actor(id, "Ana", "BRAÑA");
		when(actorService.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/actores/v1/{id}", id))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(id))
	        .andExpect(jsonPath("$.nombre").value(ele.getFirstName()))
	        .andExpect(jsonPath("$.apellidos").value(ele.getLastName()))
	        .andDo(print());
	}
	@Test
	void getByIdNotValidTest() throws Exception {
		int id = 1;
		var ele = new Actor(id, "Ana", "BRAÑA");
		when(actorService.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/actores/v1/{id}", id))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.title").value("Not Found"))
	        .andDo(print());
	}


	@Test
	void postTest() throws Exception {
		int id = 1;
		var ele = new Actor(id, "Ana", "BRAÑA");
		when(actorService.add(ele)).thenReturn(ele);
		mockMvc.perform(post("/api/actores/v1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(ActorDTO.from(ele)))
			)
			.andExpect(status().isCreated())
	        .andExpect(header().string("Location", "http://localhost/api/actores/v1/1"))
	        .andDo(print())
	        ;
	}



}

