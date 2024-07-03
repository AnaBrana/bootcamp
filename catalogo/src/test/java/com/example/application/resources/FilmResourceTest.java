package com.example.application.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import com.example.domains.contracts.services.FilmService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(FilmResource.class)
public class FilmResourceTest {

	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private FilmService filmService;

	@Autowired
	ObjectMapper objectMapper;
}
