package com.example.models;

import com.example.domains.core.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Table(name="lugares")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lugar extends EntityBase<Lugar> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="lugar_id", unique=true, nullable=false)
    private int id;
    @Column(name="nombre", nullable=false, length=45)
    @NotBlank
    @Size(max=45, min=2)
    private String nombreEmpresa;
    @Column(name="direccion", nullable=false, length=45)
    @NotBlank
    @Size(max=45, min=2)
    private String direccion;
    @Column(name="telefono", nullable=false, length=45)
    @NotBlank
    @Size(max=12, min=9)
    private String telefono;
    @Column(name="web")
    @NotBlank
    @Size(max=45, min=2)
    private String urlEmpresa;
    @ManyToOne
    @JoinColumn(name="evento_id")
    private Evento evento;
}
