package com.example.DTO;

import com.example.models.Usuario;
import com.example.models.UsuarioEmpresa;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

import org.springframework.data.rest.core.config.Projection;
@Data @AllArgsConstructor
@Projection(name = "usuarioEmpresa_DTO", types = {UsuarioEmpresaDTO.class})
@Schema(name = "UsuarioEmpresa DTO", description = "Datos para crear y modificar usuarios de tipo empresa")
public class UsuarioEmpresaDTO implements Serializable {
    @JsonProperty("id")
    private int empresaId;
    
    @NotBlank
    @Size(max = 45)
    private String nombre;
    
    @NotBlank
    @Size(max = 45)
    private String direccion;
   
    @Size(max = 45)
    private String tlf;
    
    @NotBlank
    @Size(max = 45)
    private String email;
    
    @NotBlank
    @Size(max = 45)
    private String contrasenia;
    
    /*public static UsuarioEmpresaDTO from(UsuarioEmpresa source) {
        return new UsuarioEmpresaDTO(
              
        );
    }*/
    /*public static UsuarioEmpresa from(UsuarioEmpresaDTO source) {
        return new UsuarioEmpresa(
               

        );
    }*/
}
