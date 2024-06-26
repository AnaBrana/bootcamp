package com.example.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.example.domains.core.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film  extends EntityBase<Film>implements Serializable {
	private static final long serialVersionUID = 1L;
	public static enum Rating {
	    GENERAL_AUDIENCES("G"),
	    PARENTAL_GUIDANCE_SUGGESTED("PG"),
	    PARENTS_STRONGLY_CAUTIONED("PG-13"),
	    RESTRICTED("R"),
	    ADULTS_ONLY("NC-17");

	    String value;
	    
	    Rating(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }
		public static Rating getEnum(String value) {
			switch (value) {
			case "G": return Rating.GENERAL_AUDIENCES;
			case "PG": return Rating.PARENTAL_GUIDANCE_SUGGESTED;
			case "PG-13": return Rating.PARENTS_STRONGLY_CAUTIONED;
			case "R": return Rating.RESTRICTED;
			case "NC-17": return Rating.ADULTS_ONLY;
			default:
				throw new IllegalArgumentException("Unexpected value: " + value);
			}
		}
		public static final String[] VALUES = {"G", "PG", "PG-13", "R", "NC-17"};
	}
	@Converter
	private static class RatingConverter implements AttributeConverter<Rating, String> {
	    @Override
	    public String convertToDatabaseColumn(Rating rating) {
	        if (rating == null) {
	            return null;
	        }
	        return rating.getValue();
	    }
	    @Override
	    public Rating convertToEntityAttribute(String value) {
	        if (value == null) {
	            return null;
	        }

	        return Rating.getEnum(value);
	    }
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;

	@Lob
	private String description;

	@Column(name="last_update", insertable = false, updatable = false, nullable=false)
	private Timestamp lastUpdate;

	@Positive
	private Integer length;

	@Convert(converter = RatingConverter.class)
	private Rating rating;

	//@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
	@Min(1895)
	@Column(name="release_year")
	private Short releaseYear;

	@Positive
	@Column(name="rental_duration", nullable=false)
	private Byte rentalDuration;

	@Positive
	@DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=2, fraction=2)
	@Column(name="rental_rate", nullable=false, precision=10, scale=2)
	private BigDecimal rentalRate;

	@DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
	@Column(name="replacement_cost", nullable=false, precision=10, scale=2)
	private BigDecimal replacementCost;

	@NotBlank
	@Size(max = 128)
	@Column(nullable=false, length=128)
	private String title;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language_id")
	@NotNull
	private Language language;

	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="original_language_id")
	private Language languageVO;

	//bi-directional many-to-one association to FilmActor
	@OneToMany(mappedBy="film", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<FilmActor> filmActors = new ArrayList<FilmActor>();

	//bi-directional many-to-one association to FilmCategory
	@OneToMany(mappedBy="film", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<FilmCategory> filmCategories = new ArrayList<FilmCategory>();

	public Film() {
	}

	public Film(int filmId) {
		this.filmId = filmId;
	}

	public Film(int filmId, @NotBlank @Size(max = 128) String title, String description, @Min(1895) Short releaseYear,
			@NotNull Language language, Language languageVO, @Positive Byte rentalDuration,
			@Positive @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 2, fraction = 2) BigDecimal rentalRate,
			@Positive Integer length,
			@DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 3, fraction = 2) BigDecimal replacementCost,
			Rating rating) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.languageVO = languageVO;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
	}

	public Film(@NotBlank @Size(max = 128) String title,
			@NotNull Language language, 
			@Positive Byte rentalDuration,
			@Positive @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 2, fraction = 2) BigDecimal rentalRate,
			@Positive int length,
			@DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 3, fraction = 2) BigDecimal replacementCost) {
		super();
		this.title = title;
		this.language = language;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
		if(filmActors != null && filmActors.size() > 0)
			filmActors.forEach(item -> { if(item.getId().getFilmId() != filmId) item.getId().setFilmId(filmId); });
		if(filmCategories != null && filmCategories.size() > 0)
			filmCategories.forEach(item -> { if(item.getId().getFilmId() != filmId) item.getId().setFilmId(filmId); });
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Rating getRating() {
		return this.rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Short getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(Byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage() {
		return this.language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getLanguageVO() {
		return this.languageVO;
	}

	public void setLanguageVO(Language languageVO) {
		this.languageVO = languageVO;
	}

	// Gestión de actores
	//Devuelve una lista de actores asociados con la película.
	public List<Actor> getActors() {
		//Usa stream para recorrer filmActors (lista de FilmActor).
		//Para cada FilmActor, obtiene el Actor asociado y lo convierte en una lista.
		return this.filmActors.stream().map(item -> item.getActor()).toList();
	}
	//Reemplaza la lista de actores asociada con la película con una nueva lista.
	//Limpia la lista de actores actual usando clearActors() si no está vacía.
	//Añade cada actor de la lista source usando addActor(item).
	public void setActors(List<Actor> source) {
		if(filmActors == null || !filmActors.isEmpty()) clearActors();
		source.forEach(item -> addActor(item));
	}
	//Limpia la lista de FilmActor.
	//Inicializa filmActors como una nueva lista vacía 
	public void clearActors() {
		filmActors = new ArrayList<FilmActor>() ;
	}
	//Añade un actor a la lista de filmActors
	public void addActor(Actor actor) {
		//Crea una nueva instancia de FilmActor con la película (this) y el actor.
		FilmActor filmActor = new FilmActor(this, actor);
		filmActors.add(filmActor);
	}
	//Añade un actor usando su ID.
	public void addActor(int actorId) {
		addActor(new Actor(actorId));
	}
	//Elimina un actor de la lista de filmActors
	//Encuentra el FilmActor correspondiente al actor dado usando stream y filter
	public void removeActor(Actor actor) {
		var filmActor = filmActors.stream().filter(item -> item.getActor().equals(actor)).findFirst();
		if(filmActor.isEmpty())
			return;
		filmActors.remove(filmActor.get());
	}

	// Gestión de categorias

	//Devuelve una lista de categorías asociadas con la película
	//Usa stream para recorrer filmCategories.
	//Para cada FilmCategory, obtiene la Category asociada y la convierte en una lista.
	public List<Category> getCategories() {
		return this.filmCategories.stream().map(item -> item.getCategory()).toList();
	}
	//Reemplaza la lista de categorías asociada con la película con una nueva lista.
	//Limpia la lista de categorías actual usando clearCategories() si no está vacía.
	//Añade cada categoría de la lista source usando addCategory(item)
	public void setCategories(List<Category> source) {
		if(filmCategories == null || !filmCategories.isEmpty()) clearCategories();
		source.forEach(item -> addCategory(item));
	}
	//Limpia la lista de FilmCategory
	public void clearCategories() {
		filmCategories = new ArrayList<FilmCategory>() ;
	}
	//Añade una categoría a la lista de filmCategories
	//Crea una nueva instancia de FilmCategory con la película (this) y la categoría
	public void addCategory(Category item) {
		FilmCategory filmCategory = new FilmCategory(this, item);
		filmCategories.add(filmCategory);
	}
	//Añade una categoría usando su ID
	public void addCategory(int id) {
		addCategory(new Category(id));
	}
	//Elimina una categoría de la lista de filmCategories
	public void removeCategory(Category ele) {
		var filmCategory = filmCategories.stream().filter(item -> item.getCategory().equals(ele)).findFirst();
		if(filmCategory.isEmpty())
			return;
		filmCategories.remove(filmCategory.get());
	}

	@Override
	public int hashCode() {
		return Objects.hash(filmId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return filmId == other.filmId;
	}

	public Film merge(Film target) {
		target.title = title;
		target.description = description;
		target.releaseYear = releaseYear;
		target.language = language;
		target.languageVO = languageVO;
		target.rentalDuration = rentalDuration;
		target.rentalRate = rentalRate;
		target.length = length;
		target.replacementCost = replacementCost;
		target.rating = rating;
		// Borra los actores que sobran
		target.getActors().stream()
			.filter(item -> !getActors().contains(item))
			.forEach(item -> target.removeActor(item));
		// Añade los actores que faltan
		getActors().stream()
			.filter(item -> !target.getActors().contains(item))
			.forEach(item -> target.addActor(item));
		// Añade los categorias que faltan
		target.getCategories().stream()
			.filter(item -> !getCategories().contains(item))
			.forEach(item -> target.removeCategory(item));
		// Borra los categorias que sobran
		getCategories().stream()
			.filter(item -> !target.getCategories().contains(item))
			.forEach(item -> target.addCategory(item));
		return target;
	}



}