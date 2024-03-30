package com.jovf.mediappbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

@Setter
@Getter
@Entity
//@Table(name = "tbl_paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    @Column(name = "nombres",nullable = false,length = 70)
    private String nombres;

    @Column(name = "apellidos",nullable = false,length = 70)
    private String apellidos;

    @Column(name = "dni",nullable = false,length = 8)
    private String dni;

    @Column(name = "direccion",nullable = true,length = 150)
    private String direccion;

    @Column(name = "telefono",nullable = false,length = 9)
    private String telefono;

    @Column(name = "email",nullable = false,length = 55)
    private String email;
}
