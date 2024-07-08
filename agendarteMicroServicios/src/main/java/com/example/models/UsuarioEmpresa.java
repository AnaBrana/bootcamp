package com.example.models;

import com.example.domains.core.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
@Entity
@Table(name="usuarios_empresa")
public class UsuarioEmpresa extends EntityBase<UsuarioEmpresa> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="empresa_id", unique=true, nullable=false)
    private int empresaId;
    @Column(name="nombre", nullable=false, length=45)
    @NotBlank
    @Size(max=45, min=2)
    private String nombre;
    @Column(name="direccion", nullable=false, length=45)
    @NotBlank
    @Size(max=45, min=2)
    private String direccion;
    @Column(name="telefono", nullable=false, length=45)
    @NotBlank
    @Size(max=12)
    private String tlf;
    @Column(name="email", nullable=false, length=45)
    @NotBlank
    @Email
    private String email;
    @Column(name="contrasenia", nullable=false, length=45)
    @NotBlank
    @Size(max=10, min=8)
    private String contrasenia;
    @Column(name="url_foto")
    @Size(max=255)
    //@URL(protocol=, host=, port=, regexp=, flags=)
    private String urlFoto;

    //lista eventos
}
