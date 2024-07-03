package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repositories.FilmRepository;
import com.example.domains.contracts.services.FilmService;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@DataJpaTest
@ComponentScan(basePackages = "com.example")
public class FilmServiceImplTest {
	@MockBean
	FilmRepository filmRepository;

	@Autowired
	FilmService filmService;

	@Test
	void getAllTest() {
		List<Film> films = Arrays.asList(
				new Film(1, "titulo 1", new Language(1, "English"), 
						(byte) 6, new BigDecimal("0.99"),
						new BigDecimal("20.99")),
				new Film(2, "titulo 2", new Language(1, "English"),
						(byte) 6, new BigDecimal("0.99"),
						new BigDecimal("20.99")),
				new Film(3, "titulo 3", new Language(1, "English"),
						(byte) 6, new BigDecimal("0.99"),
						new BigDecimal("20.99")));

		when(filmRepository.findAll()).thenReturn(films);
		var result = filmService.getAll();
		assertThat(result.size()).isEqualTo(3);
		verify(filmRepository, times(1)).findAll();
	}

	@Test
	void getByIdTest() {
		Film film = new Film(1, "titulo 1", new Language(1, "English"), 
				(byte) 6, new BigDecimal("0.99"),
				new BigDecimal("20.99"));
		when(filmRepository.findById(1)).thenReturn(Optional.of(film));
		var result = filmService.getOne(1);
		assertThat(result.isPresent()).isTrue();
		assertThat(result.get().getTitle()).isEqualTo("titulo 1");
		verify(filmRepository, times(1)).findById(1);
	}

	@Test
	void getByIdInvalidTest() {
		when(filmRepository.findById(1)).thenReturn(Optional.empty());
		var result = filmService.getOne(1);
		assertThat(result.isEmpty()).isTrue();
		verify(filmRepository, times(1)).findById(1);
	}

	@Test
	void addTest() throws DuplicateKeyException, InvalidDataException {
		Film film = new Film(1, "titulo 1", new Language(1, "English"), 
				(byte) 6, new BigDecimal("0.99"),
				new BigDecimal("20.99"));
		when(filmRepository.existsById(film.getFilmId())).thenReturn(false);
		when(filmRepository.save(film)).thenReturn(film);

		var result = filmService.add(film);
		assertThat(result).isEqualTo(film);
		verify(filmRepository, times(1)).save(film);
	}

	@Test
	void add_DuplicateKeyTest() {
		Film film = new Film(1, "titulo 1", new Language(1, "English"), 
				(byte) 6, new BigDecimal("0.99"),
				new BigDecimal("20.99"));
		when(filmRepository.existsById(film.getFilmId())).thenReturn(true);

		assertThrows(DuplicateKeyException.class, () -> filmService.add(film));
		verify(filmRepository, times(1)).existsById(film.getFilmId());
	}

	@Test
	void modifyTest() throws NotFoundException, InvalidDataException {
		Film film = new Film(1, "titulo 1", new Language(1, "English"), 
				(byte) 6, new BigDecimal("0.99"),
				new BigDecimal("20.99"));
		when(filmRepository.findById(film.getFilmId())).thenReturn(Optional.of(film));
		when(filmRepository.save(film)).thenReturn(film);

		var result = filmService.modify(film);
		assertThat(result).isEqualTo(film);
		verify(filmRepository, times(1)).findById(film.getFilmId());
		verify(filmRepository, times(1)).save(film);
	}

	@Test
	void deleteTest() throws InvalidDataException {
		Film film = new Film(1, "titulo 1", new Language(1, "English"), 
				(byte) 6, new BigDecimal("0.99"),
				new BigDecimal("20.99"));
		doNothing().when(filmRepository).deleteById(film.getFilmId());

		filmService.delete(film);
		verify(filmRepository, times(1)).deleteById(film.getFilmId());
	}

}
