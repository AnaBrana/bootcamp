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
import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.Category;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;

@DataJpaTest
@ComponentScan(basePackages = "com.example")
public class CategoryServiceImplTest {
	@MockBean
	CategoryRepository dao;

	@Autowired
	CategoryService srv;

	@Test
	void testGetAll_isNotEmpty() {
		List<Category> lista = new ArrayList<>(Arrays.asList(
				new Category(1, "Action"),
				new Category(2, "Animation"), 
				new Category(3, "Children")));

		when(dao.findAll()).thenReturn(lista);
		var rslt = srv.getAll();
		assertThat(rslt.size()).isEqualTo(3);
		verify(dao, times(1)).findAll();
	}

	@Test
	void testGetOne_valid() {
		List<Category> lista = new ArrayList<>(
				Arrays.asList(
						new Category(1, "Action"),
						new Category(2, "Animation"), 
						new Category(3, "Children")));

		when(dao.findById(1)).thenReturn(Optional.of(new Category(1, "Action")));
		var rslt = srv.getOne(1);
		assertThat(rslt.isPresent()).isTrue();

	}

	@Test
	void testGetOne_notfound() {
		when(dao.findById(1)).thenReturn(Optional.empty());
		var rslt = srv.getOne(1);
		assertThat(rslt.isEmpty()).isTrue();

	}

	@Test
	void testAddKO() throws DuplicateKeyException, InvalidDataException {
		when(dao.save(any(Category.class))).thenReturn(null, null);
		assertThrows(InvalidDataException.class, () -> srv.add(null));
		verify(dao, times(0)).save(null);
	}
	@Test
	void testAddDuplicateKeyKO() throws DuplicateKeyException, InvalidDataException {
		when(dao.findById(1)).thenReturn(Optional.of(new Category(1, "Action")));
		when(dao.existsById(1)).thenReturn(true);
		assertThrows(DuplicateKeyException.class, () -> srv.add(new Category(1, "Action")));
	}
}
