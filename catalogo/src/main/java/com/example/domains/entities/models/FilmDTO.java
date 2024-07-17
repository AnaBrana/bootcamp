package com.example.domains.entities.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.example.domains.entities.Film.Rating;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class FilmDTO implements Serializable{
	@JsonProperty("id")
	private int filmId;
	
	private String description;
	
	private Integer length;
	
	private String rating;
	
	private Short releaseYear;
	
	private byte rentalDuration;
	
	private BigDecimal rentalRate;
	
	private BigDecimal replacementCost;
	
	private String title;
	
	@JsonProperty("idioma")
	private String language;
	
	@JsonProperty("idiomaVO")
	private String languageVO;
	
	//@JsonProperty("lista_actores")
	//private Actor nombreActor;
	@JsonProperty("lista-categorias")
	private List<String> nombreCategoria;
	
	@JsonProperty("lista-actores")
	private List<String> actores;
	


	public static FilmDTO from(Film source) {
			return  new FilmDTO(
				source.getFilmId(), 
				source.getDescription(),
				source.getLength(),
				source.getRating().name(),
				source.getReleaseYear(),
				source.getRentalDuration(),
				source.getRentalRate(),
				source.getReplacementCost(),
				source.getTitle(),
				source.getLanguage().getName(),
				source.getLanguageVO()!=null ? source.getLanguageVO().getName():null,
				source.getCategories().stream().map(o -> o.getName()).toList(),
				source.getActors().stream().map(o -> o.getFirstName()+ " "+o.getLastName()).toList()
				
				);	
	
	}


	
}
