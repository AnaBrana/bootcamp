package com.example.ioc;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.NonNull;

@Component("saludaEs")
//@Qualifier("es")
@Scope("prototype")
@Profile("es")
public class SaludaImpl implements Saluda {
	
	/*
	 * public static class SaludaEvento{ private String tipo; private String
	 * destination; public SaludaEvento(String tipo, String destination) { super();
	 * this.tipo = tipo; this.destination = destination; }
	 * 
	 * public String tipo() { return tipo; } public String destination() { return
	 * destination; }
	 * 
	 * }
	 */
	
	public static record SaludaEvent(String tipo, String destination) {
		
	}
	
	private ApplicationEventPublisher publisher;
	
	private Entorno entorno;
	
	public SaludaImpl(Entorno entorno, ApplicationEventPublisher publisher) {
		this.entorno = entorno;
		this.publisher= publisher;
	}
	 
	protected void onSaluda(@NonNull String tipo, @NonNull String destination) {
		publisher.publishEvent(new SaludaEvent(tipo,destination));
	}
	@Override
	public void saluda(@NonNull String nombre) {
		entorno.write("Hola " + nombre);
		onSaluda("saluda",nombre);
	}

	@Override
	public int getContador() {
		return entorno.getContador();
	}
	
	public Optional<Entorno>getEntorno(){
		return Optional.ofNullable(entorno);
	}

}
