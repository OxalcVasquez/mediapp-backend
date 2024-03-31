package com.jovf.mediappbackend.dto;

import com.jovf.mediappbackend.model.Consulta;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ConsultaListaExamenDTO {

    @NotNull
    private ConsultaDTO consulta;

    @NotNull
    private List<ExamenDTO> listExamen;

}
