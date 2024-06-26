package com.example.domains.core.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Value;

public class EntityBaseTest {

	@Value
	class Dummy extends EntityBase<Dummy>{
		@Positive
		int id;
		@NotBlank
		@Size(min=2)
		String nombre;
	}
}
