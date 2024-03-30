package com.jovf.mediappbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedico;

    @Column(name = "nombres", nullable = false, length = 70)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 70)
    private String apellidos;

    @Column(name = "cmp", nullable = false, length = 12, unique = true)
    private String cmp;

    @Column(name = "foto_url", nullable = true)
    private String fotoUrl;

}
