package com.example.DTO;

import java.io.Serializable;


import com.example.models.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.rest.core.config.Projection;

@Data
@AllArgsConstructor
@Projection(name = "usuario_DTO", types = {UsuarioDTO.class})
@Schema(name = "Usuario DTO", description = "Datos para crear y modificar usuarios")
public class UsuarioDTO implements Serializable {
    @JsonProperty("id")
    private int usuarioId;
    @NotBlank
    @Size(max = 45)
    private String nombre;
    @NotBlank
    @Size(max = 45)
    private String apellidos;
    @NotBlank
    @Size(max = 45)
    private String alias;
    @NotBlank
    @Size(max = 45)
    private String email;
    @NotBlank
    @Size(max = 45)
    private String contrasenia;


    public static UsuarioDTO from(Usuario source) {
        return new UsuarioDTO(
              source.getUsuarioId(),
                source.getNombre(),
                source.getApellidos(),
                source.getAlias(),
                source.getEmail(),
                source.getContrasenia()
        );
    }
    /*public static Usuario from(UsuarioDTO source) {
        return new Usuario(
                source.getUsuarioId(),
                source.getNombre(),
                source.getApellidos(),
                source.getAlias(),
                source.getEmail(),
                source.getContrasenia(),
                source.getUrlfoto()

        );
    }*/
}
