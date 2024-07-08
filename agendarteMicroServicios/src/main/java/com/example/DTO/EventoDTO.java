package com.example.DTO;

import com.example.models.Categoria;
import com.example.models.Evento;
import com.example.models.Lugar;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.rest.core.config.Projection;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
@Data @AllArgsConstructor
@Projection(name = "evento_DTO", types = {EventoDTO.class})
@Schema(name = "Evento DTO", description = "Datos para crear y modificar eventos")
public class EventoDTO implements Serializable {
    @JsonProperty("id")
    private int eventoId;
    @Size(max=45)
    private String titulo;
    private LocalDate fecha;
    private LocalTime hora;
    private double precio;
    private String urlEntradas;
    private String urlEvento;
    private Categoria categoria;
    //private Set<Lugar> lugares;


    public static EventoDTO from(Evento source) {
        return new EventoDTO(
                source.getEventoId(),
                source.getTitulo(),
                source.getFecha(),
                source.getHora(),
                source.getPrecio(),
                source.getUrlEntradas(),
                source.getUrlEvento(),
                source.getCategoria()
        );
    }
    public static Evento from(EventoDTO source) {
        return new Evento(
                source.getEventoId(),
                source.getTitulo(),
                source.getFecha(),
                source.getHora(),
                source.getPrecio(),
                source.getUrlEntradas(),
                source.getUrlEvento(),
                source.getCategoria()

        );
    }
}
