package com.example.application.resources;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.example.domains.contracts.services.ActorService;
import com.example.domains.entities.models.ActorDTO;
import com.example.domains.entities.models.ActorShort;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/actores/v1")
@Tag(name = "Microservice Actores", 
description = "API que permite el mantenimiento de actores")
public class ActorResource {

	private ActorService srv;

	
	public ActorResource(ActorService srv) {
		super();
		this.srv = srv;
	}
	
	@GetMapping("")
	@Operation(summary="Buscar todos los actores", 
	description = "Devuelve una lista de actores")
	public List getAll(@RequestParam(required=false,defaultValue="largo")String modo){
		if("short".equals(modo))
		return srv.getByProjection(ActorShort.class);
		else
			return srv.getByProjection(ActorDTO.class);
	}
	
	@GetMapping(params="page")
	@Operation(summary="Buscar todos los actores", 
	description = "Devuelve un pageable de actores")
	public Page<ActorShort>getAll(Pageable page){
		return srv.getByProjection(page,ActorShort.class);
	}
	
	@GetMapping(path="/{id}")
	@Operation(summary="Buscar un actor", 
	description = "Devuelve un actor por su identificador")
	@ApiResponse(responseCode = "200", description = "Actor encontrado")
	@ApiResponse(responseCode = "404", description = "Actor no encontrado")
	public ActorDTO getOne(@Parameter(description = "Identificador del actor",
	required = true)
	@PathVariable int id) throws NotFoundException{
		var item= srv.getOne(id);
		if(item.isEmpty()) 
			throw new NotFoundException();
		
		return  ActorDTO .from(item.get());
	}
	
	
	record Peli(int id, String titulo) {}
	@GetMapping(path="/{id}/pelis")
	@Transactional
	@Operation(summary="Buscar las pelis de un actor", 
	description = "Devuelve la lista de pelis de un actor")
	public List<Peli> getPelis(@Parameter(description = "Identificador del actor",
	required = true)
	@PathVariable int id) throws NotFoundException{
		var item= srv.getOne(id);
		if(item.isEmpty()) 
			throw new NotFoundException();
		
		return  item.get().getFilmActors().stream()
				.map(o -> new Peli(o.getFilm().getFilmId(),o.getFilm().getTitle()))
				.toList();
	}
	
	@PutMapping(path="/{id}/jubilacion")
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ApiResponse(responseCode = "202", description = 
	"Aceptada petición y realizada jubilación")
	@Operation(summary="Jubila a un actor")
	public void jubilar(@Parameter(description = 
	"Identificador del actor", required = true)
	@PathVariable int id) throws NotFoundException{
		var item= srv.getOne(id);
		if(item.isEmpty()) 
			throw new NotFoundException();
		
		 item.get().jubilate();
	}
	
	@PostMapping
	@ApiResponse(responseCode = "201", description = "Actor creado")
	@ApiResponse(responseCode = "400", description = 
	"Petición erronea, id duplicada o"
			+ " algún dato es erroneo en el cuerpo")
	public ResponseEntity<Object>create(@Valid @RequestBody ActorDTO item)
			throws BadRequestException,
	DuplicateKeyException, InvalidDataException{
		var newItem= srv.add(ActorDTO.from(item));
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiResponse(responseCode = "204", description = "Actor modificado")
	@ApiResponse(responseCode = "404", description = "Actor no existente")
	@ApiResponse(responseCode = "400", description =
	"Petición erronea, id duplicada o"
			+ " algún dato es erroneo en el cuerpo")
	public void update(@Parameter(description = 
			"Identificador del actor", required = true)
	@PathVariable int id, @Valid @RequestBody ActorDTO item) 
			throws BadRequestException,
	NotFoundException, InvalidDataException {
		if(id != item.getActorId()) 
			
		throw new BadRequestException("No coinciden los identificadores");
		
		srv.modify(ActorDTO.from(item));
	}
	
	@DeleteMapping(path="/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiResponse(responseCode = "204", description = "Actor eliminado o no existe")
	//@ApiResponse(responseCode = "404", description = "Actor no existente")
	public void delete(@Parameter(description = "Identificador del actor", 
	required = true)
	@PathVariable int id, @Valid @RequestBody ActorDTO item) {
		srv.deleteById(id);
	}
	
}
