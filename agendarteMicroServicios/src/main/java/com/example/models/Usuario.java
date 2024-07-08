package com.example.models;

import com.example.domains.core.entities.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="usuarios")
@Data @AllArgsConstructor @NoArgsConstructor
public class Usuario extends EntityBase<Usuario> implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="usuario_id", unique=true, nullable=false)
    private int usuarioId;
    @Column(name="nombre", nullable=false, length=45)
    @NotBlank
    @Size(max=45, min=2)
    private String nombre;
    @Column(name="apellido", nullable=false, length=45)
    @NotBlank
    @Size(max=45, min=2)
    private String apellidos;
    @Column(name="alias", nullable=false, length=45)
    @NotBlank
    @Size(max=10, min=2)
    private String alias;
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


    @ManyToMany
    @JoinTable(
            name = "amigos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "amigo_id")
    )
    private Set<Usuario> amigos = new HashSet<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Evento> eventos = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "usuario_categoria",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categoriasInteres = new HashSet<>();


}
