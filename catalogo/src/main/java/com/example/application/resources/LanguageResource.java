package com.example.application.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.contracts.services.LanguageService;
import com.example.domains.entities.models.CategoryDTO;
import com.example.domains.entities.models.LanguageDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/languages/v1")
public class LanguageResource {
	@Autowired
	private LanguageService languageService;
	
	@GetMapping()
	public List getAll(){
	
		return languageService.getAll();
		}
	
	@GetMapping(path="/{id}")
	public LanguageDTO getOne(@PathVariable int id)throws NotFoundException{
		var item= languageService.getOne(id);
		if(item.isEmpty()) 
			throw new NotFoundException();
		
		return  LanguageDTO .from(item.get());
	}
	
	@PostMapping
	public ResponseEntity<Object>create(@Valid @RequestBody LanguageDTO item)throws BadRequestException,
	DuplicateKeyException, InvalidDataException{
		var newItem= languageService.add(LanguageDTO.from(item));
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getLanguageId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody LanguageDTO item) throws BadRequestException,
	NotFoundException, InvalidDataException {
		if(id != item.getLanguageId()) 
			
		throw new BadRequestException("No coinciden los identificadores");
		
		languageService.modify(LanguageDTO.from(item));
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		languageService.deleteById(id);
	}

}
