package com.jovf.mediappbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PacienteDTO {


    private Integer idPaciente;

    @NotNull
    @Size(min = 3)
    private String nombres;

    @NotNull
    @Size(min = 3)
    private String apellidos;

    @NotNull
    @Size(min = 8)
    private String dni;

    @NotNull
    @Size(min = 3, max = 150)
    private String direccion;

    @Size(min = 9, max = 9)
    private String telefono;

    @Email
    private String email;

}
