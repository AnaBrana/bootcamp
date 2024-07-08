package com.example.DTO;

import com.example.models.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.rest.core.config.Projection;

import java.io.Serializable;
@Data @AllArgsConstructor
@Projection(name = "categoria_DTO", types = {CategoriaDTO.class})
@Schema(name = "Categoria DTO", description = "Datos para crear y modificar categorias")
public class CategoriaDTO implements Serializable {
  @JsonProperty("id")
    private int categoriaId;

    @Size(max=45)
    private String nombre;

    @Size(max=45)
    private String subtipo;

    

    public static CategoriaDTO from(Categoria source) {
        return new CategoriaDTO(
              source.getId(),
              source.getNombre(),
               source.getSubtipo()
        );
    }
    public static Categoria from(CategoriaDTO source) {
        return new Categoria(
                 source.getCategoriaId(),
              source.getNombre(),
               source.getSubtipo()

        );
    }
}
