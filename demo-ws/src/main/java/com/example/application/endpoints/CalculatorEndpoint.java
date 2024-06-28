package com.example.application.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.webservices.schemas.calculator.AddRequest;
import com.example.webservices.schemas.calculator.AddResponse;
import com.example.webservices.schemas.calculator.DivideResponse;
import com.example.webservices.schemas.calculator.MultiplyRequest;
import com.example.webservices.schemas.calculator.MultiplyResponse;
import com.example.webservices.schemas.calculator.SustractRequest;
import com.example.webservices.schemas.calculator.SustractResponse;

@Endpoint
public class CalculatorEndpoint {
	
	private static final String NAMESPACE_URI= "http://example.com/webservices/schemas/calculator";
	
	//Sumar
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addRequest")
	@ResponsePayload
	public AddResponse add(@RequestPayload AddRequest request) {
		var result = new AddResponse();
		result.setAddResult(request.getOp1() + request.getOp2());
		return result;
	}
	
	//Restar
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "sustractRequest")
	@ResponsePayload
	public SustractResponse sustract(@RequestPayload SustractRequest request) {
		var result = new SustractResponse();
		result.setSustractResult(request.getOp1() - request.getOp2());
		return result;
	}
	
	//Multiplicar
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "multiplyRequest")
	@ResponsePayload
	public MultiplyResponse multiply(@RequestPayload MultiplyRequest request) {
		var result = new MultiplyResponse();
		result.setMultiplyResult(request.getOp1() * request.getOp2());
		return result;
	}
	
	//Dividir
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "divideRequest")
	@ResponsePayload
	public DivideResponse divide(@RequestPayload MultiplyRequest request) {
		var result = new DivideResponse();
		result.setDivideResult(request.getOp1() / request.getOp2());
		return result;
	}
}
