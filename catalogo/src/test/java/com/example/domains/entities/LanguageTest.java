package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LanguageTest {
	
	@Test
	@DisplayName("Es un lenguaje válido")
	void testIsValid() {
		var fixture = new Language(0,"asturiano");
		assertTrue(fixture.isValid());
	}
	
	@DisplayName("El idioma de tener entre 2 y 45 caracteres, y no puede estar en blanco")
	@ParameterizedTest(name = "idioma: -{0}- -> {1}")
	@CsvSource(value = { "'','ERRORES: name: el tamaño debe estar entre 2 y 45, no debe estar vacío.'"})
	void testLanguageIsInvalid(String valor, String error) {
		var fixture = new Language(0,valor);
		assertEquals(error, fixture.getErrorsMessage());
	}
}
