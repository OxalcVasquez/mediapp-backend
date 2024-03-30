package com.jovf.mediappbackend.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu {

    @Id
    private Integer idMenu;
    @Column(name = "icono", nullable = false, length = 20)
    private String icono;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "url", nullable = false, length = 50)
    private String url;
}
