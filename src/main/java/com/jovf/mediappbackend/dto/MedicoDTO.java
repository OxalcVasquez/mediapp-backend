package com.jovf.mediappbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MedicoDTO {


    private Integer idMedico;

    @NotNull
    @Size(min = 3,message = "{nombres.size}")
    private String nombres;

    @NotNull
    @Size(min = 3, message = "{apellidos.size}")
    private String apellidos;

    @NotNull
    @Size(min = 12)
    private String cmp;

    @NotNull
    private String fotoUrl;


}
