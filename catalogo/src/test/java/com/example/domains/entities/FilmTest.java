package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class FilmTest {

	@Test
	void test() {
		
		Film film = new Film(1, "titulo 1", new Language(1, "English"), 
				(byte) 6, new BigDecimal("0.99"),
				new BigDecimal("20.99"));
		assertTrue(film.isValid());
	}

}
