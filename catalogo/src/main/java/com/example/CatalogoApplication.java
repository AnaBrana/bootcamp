package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.domains.contracts.services.ActorService;
import com.example.domains.contracts.services.CategoryService;
import com.example.domains.contracts.services.FilmService;
import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;
import com.example.domains.entities.Film;
import com.example.domains.entities.Language;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}
	
	@Autowired
	ActorService actorService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	FilmService filmService;
	
	@Autowired
	LanguageService languageService;
	
	
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		System.err.println("Aplicación arrancada...");
		//actorService.getByProjection(Actor.class).forEach(System.out::println);
		//categoryService.getByProjection(Category.class).forEach(System.out::println);
		//filmService.getByProjection(Film.class).forEach(System.out::println);
		//languageService.getByProjection(Language.class).forEach(System.out::println);
		
	
	}

}
