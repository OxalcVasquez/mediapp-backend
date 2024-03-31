package com.jovf.mediappbackend.repo;

import com.jovf.mediappbackend.model.Especialidad;
import com.jovf.mediappbackend.model.Medico;

//@Repository - No es necesario debido que se redundaría por que Spring entiende que se extiende de JPA
public interface IEspecialidadRepo extends IGenericRepo<Especialidad,Integer> {

}
