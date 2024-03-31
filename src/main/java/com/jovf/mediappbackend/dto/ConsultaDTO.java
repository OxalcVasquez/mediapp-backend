package com.jovf.mediappbackend.dto;

import com.jovf.mediappbackend.model.DetalleConsulta;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ConsultaDTO {

    private Integer idConsulta;
    @NotNull
    private PacienteDTO paciente;
    @NotNull
    private MedicoDTO medico;
    @NotNull
    private EspecialidadDTO especialidad;
    @NotNull
    private String numConsultorio;
    @NotNull
    private LocalDateTime fecha;
    @NotNull
    private List<DetalleConsultaDTO> detalleConsulta;



}
