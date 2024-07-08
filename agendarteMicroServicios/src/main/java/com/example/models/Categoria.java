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
@Table(name="categorias")
@Data @AllArgsConstructor @NoArgsConstructor
public class Categoria extends EntityBase<UsuarioEmpresa> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="categoria_id", unique=true, nullable=false)
    private int id;
    @Column(name="nombre", nullable=false, length=45)
    @NotBlank
    @Size(max=45, min=2)
    private String nombre;
    @Column(name="subtipo", nullable=false, length=45)
    @NotBlank
    @Size(max=45, min=2)
    private String subtipo;


}
