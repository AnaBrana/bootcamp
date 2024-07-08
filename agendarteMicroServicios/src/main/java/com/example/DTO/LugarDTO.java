package com.example.DTO;

import com.example.models.Categoria;
import com.example.models.Lugar;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.rest.core.config.Projection;

import java.io.Serializable;
@Data @AllArgsConstructor
@Projection(name = "lugar_DTO", types = {LugarDTO.class})
@Schema(name = "Lugar DTO", description = "Datos para crear y modificar lugares")
public class LugarDTO implements Serializable {
    @JsonProperty("id")
    private int lugarId;
    @Size(max=45)
    private String nombreEmpresa;
    @Size(max=45)
    private String direccion;
    @Size(max=45)
    private String telefono;
    @Size(max=45)
    private String urlEmpresa;

    
    public static LugarDTO from(Lugar source) {
        return new LugarDTO(
        		source.getId(),
        		source.getNombreEmpresa(),
        		source.getDireccion(),
        		source.getTelefono(),
        		source.getUrlEmpresa()
        );
    }
   /* public static Lugar from(LugarDTO source) {
        return new Lugar(
        		source.getLugarId(),
        		source.getNombreEmpresa(),
        		source.getDireccion(),
        		source.getTelefono(),
        		source.getUrlEmpresa(),
        		source.getDireccion()

        );
    }*/
}
