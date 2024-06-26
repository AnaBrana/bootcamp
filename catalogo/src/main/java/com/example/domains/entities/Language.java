package com.example.domains.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import com.example.domains.core.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * The persistent class for the language database table.
 * 
 */
@Entity
@Table(name="language")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language  extends EntityBase<Language> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="language_id")
	private int languageId;

	@Column(name="last_update", insertable=false, updatable=false)
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Timestamp lastUpdate;

	@Column
	@Size(max=20)
	private String name;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="language")
	private List<Film> films1;

	//bi-directional many-to-one association to Film
	@OneToMany(mappedBy="languageVO")
	private List<Film> films2;

	public Language() {
	}
	
	

	public Language(int languageId, String name) {
		super();
		this.languageId = languageId;
		this.name = name;
	}



	@Override
	public int hashCode() {
		return Objects.hash(languageId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Language other = (Language) obj;
		return languageId == other.languageId;
	}



	@Override
	public String toString() {
		return "Language: id: " + languageId + ", lastUpdate: " + lastUpdate + ", idioma: " + name;
	}



	public int getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Film> getFilms1() {
		return this.films1;
	}

	public void setFilms1(List<Film> films1) {
		this.films1 = films1;
	}

	public Film addFilms1(Film films1) {
		getFilms1().add(films1);
		films1.setLanguage(this);

		return films1;
	}

	public Film removeFilms1(Film films1) {
		getFilms1().remove(films1);
		films1.setLanguage(null);

		return films1;
	}

	public List<Film> getFilms2() {
		return this.films2;
	}

	public void setFilms2(List<Film> films2) {
		this.films2 = films2;
	}

	public Film addFilms2(Film films2) {
		getFilms2().add(films2);
		films2.setLanguageVO(this);

		return films2;
	}

	public Film removeFilms2(Film films2) {
		getFilms2().remove(films2);
		films2.setLanguageVO(null);

		return films2;
	}

}