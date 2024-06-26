package com.example.domains.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class FilmTest {
			
	@Test
	@DisplayName("Es una película válida")
	void testIsValid() {
		var idioma= new Language(1);
		var fixture = new Film(1,(byte) 6,
	            new BigDecimal(0.99),
	            new BigDecimal(20.99),
	            "ACADEMY DINOSAUR",idioma);
		assertTrue(fixture.isValid());
	}
	
	@DisplayName("El título de tener entre 2 y 45 caracteres, y no puede estar en blanco")
	@ParameterizedTest(name = "title: -{0}- -> {1}")
	@CsvSource(value = { "'','ERRORES: title: el tamaño debe estar entre 2 y 45, no debe estar vacío.'"})
	void testTitleIsInvalid(String valor, String error) {
		var idioma= new Language(1);
		var fixture =new Film(1,(byte) 6,
	            new BigDecimal(0.99),
	            new BigDecimal(20.99),
	           valor,idioma);
		assertEquals(error, fixture.getErrorsMessage());
	}
	
	
	 @DisplayName("La duración del alquiler debe ser positiva")
	    @ParameterizedTest(name = "rentalDuration: -{0}- -> {1}")
	    @CsvSource(value = {
	        "0,ERRORES: rentalDuration: debe ser positivo.",
	        "-1,ERRORES: rentalDuration: debe ser positivo."
	    })
	 void testRentalDurationIsInvalid(byte valor, String error) {
	        var idioma = new Language(1);
	        var fixture = new Film(1, valor,
	                new BigDecimal(0.99),
	                new BigDecimal(20.99),
	                "ACADEMY DINOSAUR", idioma);
	        assertEquals(error, fixture.getErrorsMessage());
	    }
	 
	 @DisplayName("La tarifa de alquiler debe ser un número válido")
	    @ParameterizedTest(name = "rentalRate: -{0}- -> {1}")
	    @CsvSource(value = {
	        "0,ERRORES: rentalRate: valor numérico fuera de límites (se esperaba <2 dígitos>.<2 dígitos>).",
	        "100.99,ERRORES: rentalRate: valor numérico fuera de límites (se esperaba <2 dígitos>.<2 dígitos>)."
	    })
	    void testRentalRateIsInvalid(BigDecimal valor, String error) {
	        var idioma = new Language(1);
	      
	        var fixture = new Film(1, (byte) 6,
	        		 new BigDecimal(20.99),
	                valor,
	               "ACADEMY DINOSAUR", idioma);
	        assertEquals(error, fixture.getErrorsMessage());
	    }

	    @DisplayName("El coste de reemplazo debe ser un número válido")
	    @ParameterizedTest(name = "replacementCost: -{0}- -> {1}")
	    @CsvSource(value = {
	        "0,ERRORES: replacementCost: valor numérico fuera de límites (se esperaba <3 dígitos>.<2 dígitos>).",
	        "1000.99,ERRORES: replacementCost: valor numérico fuera de límites (se esperaba <3 dígitos>.<2 dígitos>)."
	    })
	    void testReplacementCostIsInvalid(BigDecimal valor, String error) {
	        var idioma = new Language(1);
	       
	        var fixture = new Film(1, (byte) 6,
	        		valor,
	                new BigDecimal(0.99),
	                "ACADEMY DINOSAUR", idioma);
	        assertEquals(error, fixture.getErrorsMessage());
	    }

	    @DisplayName("El idioma no debe ser nulo")
	    @ParameterizedTest(name = "language_id: -{0}- -> {1}")
	    @CsvSource(value = {
	        "0,ERRORES: language_id: no debe ser nulo."
	    })
	    void testLanguageIsInvalid(Integer valor, String error) {
	        Language idioma = new Language(valor);
	        var fixture = new Film(1, (byte) 6,
	                new BigDecimal(0.99),
	                new BigDecimal(20.99),
	                "ACADEMY DINOSAUR", idioma);
	        assertEquals(error, fixture.getErrorsMessage());
	    }
	
	
}
