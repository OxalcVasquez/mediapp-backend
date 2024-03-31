package com.jovf.mediappbackend.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DetalleConsultaDTO {

    private Integer idDetalleConsulta;
    @JsonIgnore
    private ConsultaDTO consulta;
    @NotNull
    private String diagnostico;
    @NotNull
    private String tratamiento;
}
