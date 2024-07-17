package com.example.domains.entities.models;

import org.springframework.beans.factory.annotation.Value;

public interface ActorShort {
	@Value("#{target.ActorId}")
	int getId();
	@Value("#{target.amp}")
	String getNombre();
	@Value("#{target.lastName}")
	String getApellidos();
}
