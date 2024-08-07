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

import com.example.domains.contracts.repositories.CategoryRepository;
import com.example.domains.contracts.repositories.LanguageRepository;
import com.example.domains.contracts.services.CategoryService;
import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Category;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;

@DataJpaTest
@ComponentScan(basePackages = "com.example")
public class LanguageServiceImplTest {
	@MockBean
	LanguageRepository languageRepo;

	@Autowired
	LanguageService languageService;
	
	@Test
	void testGetAll_isNotEmpty() {
		List<Language> lista = new ArrayList<>(Arrays.asList(
				new Language(1, "English"),
				new Language(2, "French"), 
				new Language(3, "Spanish")));

		when(languageRepo.findAll()).thenReturn(lista);
		var rslt =  languageService.getAll();
		assertThat(rslt.size()).isEqualTo(3);
		verify(languageRepo, times(1)).findAll();
	}

	@Test
	void testGetOne_valid() {
		when(languageRepo.findById(1)).thenReturn(Optional.of(new Language(1, "English")));
		var rslt =  languageService.getOne(1);
		assertThat(rslt.isPresent()).isTrue();

	}

	@Test
	void testGetOne_notfound() {
		when(languageRepo.findById(1)).thenReturn(Optional.empty());
		var rslt =  languageService.getOne(1);
		assertThat(rslt.isEmpty()).isTrue();

	}

	@Test
	void testAddKO() throws DuplicateKeyException, InvalidDataException {
		when(languageRepo.save(any(Language.class))).thenReturn(null, null);
		assertThrows(InvalidDataException.class, () ->  languageService.add(null));
		verify(languageRepo, times(0)).save(null);
	}
	@Test
	void testAddDuplicateKeyKO() throws DuplicateKeyException, InvalidDataException {
		when(languageRepo.findById(1)).thenReturn(Optional.of(new Language(1, "English")));
		when(languageRepo.existsById(1)).thenReturn(true);
		assertThrows(DuplicateKeyException.class, () ->  languageService.add(new Language(1, "English")));
	}

}
