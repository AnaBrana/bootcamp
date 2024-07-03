package com.example.application.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Language;
import com.example.domains.entities.models.LanguageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LanguageResource.class)
public class LanguageResourceTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private LanguageService languageService;

	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void getAllTest() throws Exception {
		List<Language> lista = Arrays.asList(
		        new Language(1, "English"),
		        new Language(2, "Italian"),
		        new Language(3, "Japanese"));
		when(languageService.getAll()).thenReturn(lista);
		mockMvc.perform(get("/api/languages/v1").accept(MediaType.APPLICATION_JSON))
			.andExpectAll(
					status().isOk(), 
					content().contentType("application/json"),
					jsonPath("$.size()").value(3)
					);
	}

	@Test
	void getByIdTest() throws Exception {
		int id = 1;
		var ele = new Language(id, "Action");
		when(languageService.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/languages/v1/{id}", id))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(id))
	        .andExpect(jsonPath("$.idioma").value(ele.getName()))
	        .andDo(print());
	}
	
	@Test
	void getByIdNotValidTest() throws Exception {
		int id = 1;
		when(languageService.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/languages/v1/{id}", id))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.title").value("Not Found"))
	        .andDo(print());
	}

	@Test
	void postTest() throws Exception {
		int id = 1;
		var ele = new Language(id, "Action");
		when(languageService.add(any())).thenReturn(ele);
		mockMvc.perform(post("/api/languages/v1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(LanguageDTO.from(ele)))
			)
			.andExpect(status().isCreated())
	        .andExpect(header().string("Location", "http://localhost/api/languages/v1/1"))
	        .andDo(print());
	}


}
