package com.jovf.mediappbackend.service;

import com.jovf.mediappbackend.model.Consulta;
import com.jovf.mediappbackend.model.Examen;

import java.util.List;

public interface IConsultaService extends ICRUD<Consulta, Integer> {

    Consulta registrarTransaccional(Consulta consulta, List<Examen> examenes) throws Exception;


}
