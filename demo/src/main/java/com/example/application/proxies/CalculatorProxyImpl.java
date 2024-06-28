package com.example.application.proxies;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.domains.contracts.proxies.CalculatorProxy;
import com.example.webservice.schema.AddRequest;
import com.example.webservice.schema.AddResponse;
import com.example.webservice.schema.DivideRequest;
import com.example.webservice.schema.DivideResponse;
import com.example.webservice.schema.MultiplyRequest;
import com.example.webservice.schema.MultiplyResponse;
import com.example.webservice.schema.SustractRequest;
import com.example.webservice.schema.SustractResponse;

public class CalculatorProxyImpl extends WebServiceGatewaySupport implements CalculatorProxy {
	
	//Sumar
	public double add(double a, double b) {
		var request = new AddRequest();
		request.setOp1(a);
		request.setOp2(b);
		var response = (AddResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://example.com/webservices/schemas/calculator"));
		return response.getAddResult();
	}

	//Restar
	@Override
	public double sustract(double a, double b) {
		var request = new SustractRequest();
		request.setOp1(a);
		request.setOp2(b);
		var response = (SustractResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://example.com/webservices/schemas/calculator"));
		return response.getSustractResult();
	}

	//Multiplicar
	@Override
	public double multiply(double a, double b) {
		var request = new MultiplyRequest();
		request.setOp1(a);
		request.setOp2(b);
		var response = (MultiplyResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://example.com/webservices/schemas/calculator"));
		return response.getMultiplyResult();
	}

	//Dividir
	@Override
	public double divide(double a, double b) {
		var request = new DivideRequest();
		request.setOp1(a);
		request.setOp2(b);
		var response = (DivideResponse) getWebServiceTemplate().marshalSendAndReceive(request,
				new SoapActionCallback("http://example.com/webservices/schemas/calculator"));
		return response.getDivideResult();
	}
	
	
	

}
