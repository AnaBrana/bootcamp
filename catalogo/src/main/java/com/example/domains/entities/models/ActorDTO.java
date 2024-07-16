package com.example.domains.entities.models;

import java.io.Serializable;

import com.example.domains.entities.Actor;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
@Schema(name = "Actor DTO", description = "Datos para crear y modificar actores")
public class ActorDTO implements Serializable {
	@JsonProperty("id")
	private int actorId;
	@JsonProperty("nombre")
	private String firstName;
	@JsonProperty("apellidos")
	private String lastName;

	public static ActorDTO from(Actor source) {
		return new ActorDTO(
				source.getActorId(), 
				source.getFirstName(), 
				source.getLastName()
				);
	}
	public static Actor from(ActorDTO source) {
		return new Actor(
				source.getActorId(), 
				source.getFirstName(), 
				source.getLastName()
				);
	}
}
