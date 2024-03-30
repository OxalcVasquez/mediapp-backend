package com.jovf.mediappbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table( name = "rol")
public class Rol {

    @Id
    private Integer idRol;

    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 70)
    private String descripcion;

}
