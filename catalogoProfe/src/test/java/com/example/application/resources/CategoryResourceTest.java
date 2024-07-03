package com.example.application.resources;

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

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Category;
import com.example.domains.entities.models.CategoryDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryResource.class)
public class CategoryResourceTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private CategoryService categoryService;

	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void getAllTest() throws Exception {
		List<Category> lista = Arrays.asList(
		        new Category(1, "Action"),
		        new Category(2, "Animacion"),
		        new Category(3, "Horror"));
		when(categoryService.getAll()).thenReturn(lista);
		mockMvc.perform(get("/api/categories/v1").accept(MediaType.APPLICATION_JSON))
			.andExpectAll(
					status().isOk(), 
					content().contentType("application/json"),
					jsonPath("$.size()").value(3)
					);
	}

	@Test
	void getByIdTest() throws Exception {
		int id = 1;
		var ele = new Category(id, "Action");
		when(categoryService.getOne(id)).thenReturn(Optional.of(ele));
		mockMvc.perform(get("/api/categories/v1/{id}", id))
			.andExpect(status().isOk())
	        .andExpect(jsonPath("$.id").value(id))
	        .andExpect(jsonPath("$.nombre").value(ele.getName()))
	        .andDo(print());
	}
	
	@Test
	void getByIdNotValidTest() throws Exception {
		int id = 1;
		when(categoryService.getOne(id)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/categories/v1/{id}", id))
			.andExpect(status().isNotFound())
			.andExpect(jsonPath("$.title").value("Not Found"))
	        .andDo(print());
	}

	@Test
	void postTest() throws Exception {
		int id = 1;
		var ele = new Category(id, "Action");
		when(categoryService.add(any())).thenReturn(ele);
		mockMvc.perform(post("/api/categories/v1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(CategoryDTO.from(ele)))
			)
			.andExpect(status().isCreated())
	        .andExpect(header().string("Location", "http://localhost/api/categories/v1/1"))
	        .andDo(print());
	}


}
