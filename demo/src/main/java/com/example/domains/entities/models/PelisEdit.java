package com.example.domains.entities.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PelisEdit {
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
	
	@JsonProperty("language_id")
	private Integer language;
	
	@JsonProperty("original_language_id")
	private Integer languageVO;
	
	@JsonProperty("actor-id")
	private List<Integer> actorId;
	
	@JsonProperty("category_id")
	private List<Integer>categoryId;
	
	

}
