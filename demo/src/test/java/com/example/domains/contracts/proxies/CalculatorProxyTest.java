package com.example.domains.contracts.proxies;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculatorProxyTest {

	@Autowired
	CalculatorProxy calculadora;
	
	@Test
	void testAdd() {
		assertEquals(0.3,calculadora.add(0.1, 0.2));
	}
	
	@Test
	void testSubstract() {
		assertEquals(0.1,calculadora.sustract(1,0.9));
	}
	
	@Test
	void testMultiply() {
		assertEquals(5,calculadora.multiply(2, 2.5));
	}
	
	@Test
	void testDivideOK() {
		assertEquals(0.5,calculadora.divide(1, 2));
	}
	
	/*@Test
	void testDivideKO() {
		
	}*/
}
