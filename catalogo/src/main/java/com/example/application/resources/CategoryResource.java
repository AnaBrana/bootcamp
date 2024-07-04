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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.CategoryService;
import com.example.domains.entities.models.CategoryDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories/v1")
@Tag(name = "Microservice Categorías", 
description = "API que permite el mantenimiento de categorías de películas")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping()
	@Operation(summary="Buscar todos las categorías", 
	description = "Devuelve una lista de categorías")
	public List getAll(){
	
		return categoryService.getAll();
		}
	
	@GetMapping(path="/{id}")
	@Operation(summary="Buscar una categoría", 
	description = "Devuelve una categoría por su identificador")
	@ApiResponse(responseCode = "200", description = "Categoría encontrada")
	@ApiResponse(responseCode = "404", description = "Categoría no encontrada")
	public CategoryDTO getOne(@Parameter(description = "Identificador de la categoría",
			required = true)@PathVariable int id)throws NotFoundException{
		var item= categoryService.getOne(id);
		if(item.isEmpty()) 
			throw new NotFoundException();
		
		return  CategoryDTO .from(item.get());
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Categoría creada")
	@ApiResponse(responseCode = "400", description = 
	"Petición erronea, id duplicada o"
			+ " algún dato es erroneo en el cuerpo")
	public ResponseEntity<Object>create(@Valid @RequestBody CategoryDTO item)throws BadRequestException,
	DuplicateKeyException, InvalidDataException{
		var newItem= categoryService.add(CategoryDTO.from(item));
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getCategoryId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiResponse(responseCode = "204", description = "Categoría modificada")
	@ApiResponse(responseCode = "404", description = "Categoría no existente")
	@ApiResponse(responseCode = "400", description =
	"Petición erronea, id duplicada o"
			+ " algún dato es erroneo en el cuerpo")
	public void update(@Parameter(description = 
			"Identificador de la categoría", required = true)
	@PathVariable int id, @Valid @RequestBody CategoryDTO item) throws BadRequestException,
	NotFoundException, InvalidDataException {
		if(id != item.getCategoryId()) 
			
		throw new BadRequestException("No coinciden los identificadores");
		
		categoryService.modify(CategoryDTO.from(item));
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiResponse(responseCode = "204", description = "Categoría eliminada o no existe")
	//@ApiResponse(responseCode = "404", description = "Categoría no existente")
	public void delete(@Parameter(description = "Identificador de la categoría", 
			required = true)@PathVariable int id) {
		categoryService.deleteById(id);
	}

}
