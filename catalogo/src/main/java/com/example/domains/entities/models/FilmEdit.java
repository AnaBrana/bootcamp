package com.example.domains.entities.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.example.domains.entities.Film;
import com.example.domains.entities.Film.Rating;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class FilmEdit implements Serializable{
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
	
	
	public static FilmEdit from(Film source) {
		return new FilmEdit(
			source.getFilmId(), 
			source.getDescription(),
			source.getLength(),
			source.getRating().getValue(),
			source.getReleaseYear(),
			source.getRentalDuration(),
			source.getRentalRate(),
			source.getReplacementCost(),
			source.getTitle(),
			source.getLanguage().getLanguageId(),
			source.getLanguageVO()!=null ? source.getLanguageVO().getLanguageId():null,
			source.getActors().stream().map(o -> o.getActorId()).toList(),
			source.getCategories().stream().map(o ->o.getCategoryId()).toList()
		);	
	}
	
	public static Film from(FilmEdit source) {
		
		var target=new Film(
				source.getFilmId(),
				source.getTitle(),
				source.getDescription(),
				source.getReleaseYear(),
				new Language(source.getLanguage()),
				source.getLanguageVO()==null ? null :new Language(source.getLanguageVO()),
				source.getRentalDuration(),
				source.getRentalRate(),
				source.getLength(),
				source.getReplacementCost(),
				source.getRating()==null ? null :Rating.getEnum( source.getRating())
		);
		source.getActorId().forEach(id -> target.addActor(id));
		source.getCategoryId().forEach(id -> target.addCategory(id));
		return target;

}


}
