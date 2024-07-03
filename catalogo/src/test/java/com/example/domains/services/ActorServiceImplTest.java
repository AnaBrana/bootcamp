package com.example.domains.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

import com.example.domains.contracts.repositories.ActorRepository;
import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;

@DataJpaTest
@ComponentScan(basePackages = "com.example")
class ActorServiceImplTest {

	@MockBean
	ActorRepository actorRepository;

	@Autowired
	ActorService actorService;

	@Test
	void getAll_isNotEmptyTest() {
		List<Actor> lista = new ArrayList<>(Arrays.asList(
				new Actor(1, "Ana", "BRAÑA"),
				new Actor(2, "Carmen", "MAURA"), 
				new Actor(3, "Keanu", "REEVES")));

		when(actorRepository.findAll()).thenReturn(lista);
		var rslt = actorService.getAll();
		assertThat(rslt.size()).isEqualTo(3);
		verify(actorRepository, times(1)).findAll();
	}

	@Test
	void GetByIdTest() {
		when(actorRepository.findById(1)).thenReturn(Optional.of(new Actor(1, "Ana", "BRAÑA")));
		var rslt = actorService.getOne(1);
		assertThat(rslt.isPresent()).isTrue();

	}

	@Test
	void GetOneNotValidTest() {
		when(actorRepository.findById(1)).thenReturn(Optional.empty());
		var rslt = actorService.getOne(1);
		assertThat(rslt.isEmpty()).isTrue();

	}

	@Test
	void addTest() throws DuplicateKeyException, InvalidDataException {
		when(actorRepository.save(any(Actor.class))).thenReturn(null, null);
		assertThrows(InvalidDataException.class, () -> actorService.add(null));
		verify(actorRepository, times(0)).save(null);
	}
	@Test
	void addDuplicateKeyKOTest() throws DuplicateKeyException, InvalidDataException {
		when(actorRepository.findById(1)).thenReturn(Optional.of(new Actor(1, "Ana", "BRAÑA")));
		when(actorRepository.existsById(1)).thenReturn(true);
		assertThrows(DuplicateKeyException.class, () -> actorService.add(new Actor(1, "Ana", "BRAÑA")));
	}

}
