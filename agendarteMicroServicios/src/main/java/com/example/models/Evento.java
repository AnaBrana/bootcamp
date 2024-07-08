package com.example.models;

import com.example.domains.core.entities.EntityBase;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="eventos")
@Data @AllArgsConstructor @NoArgsConstructor
public class Evento extends EntityBase<Evento> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="evento_id", unique=true, nullable=false)
    private int eventoId;
    @Column(name="titulo", nullable=false, length=45)
    @NotBlank
    @Size(max=45, min=2)
    private String titulo;

    @PastOrPresent
    @FutureOrPresent
    @Column(name="fecha", nullable=false)
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    @PastOrPresent
    @FutureOrPresent
    @Column(name="hora", nullable=false)
    @NotBlank
    @JsonFormat(pattern = "hh:mm")
    private LocalTime hora;
    @DecimalMin(value="0.0", inclusive=false)
    @Positive
    @Column(name="precio")
    private double precio;
    //@URL(protocol=, host=, port=, regexp=, flags=)
    @Column(name="url_entrada")
    private String urlEntradas;
    //@URL(protocol=, host=, port=, regexp=, flags=)
    @Column(name="url_evento", nullable=false)
    private String urlEvento;

    //@URL(protocol=, host=, port=, regexp=, flags=)
    @Column(name="url_foto")
    private String urlFoto;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "evento")
    private List<Lugar> lugares;


    public Evento(int eventoId, String titulo, LocalDate fecha, LocalTime hora, double precio,
                  String urlEntradas, String urlEvento, Categoria categoria) {
        super();
    }
}
