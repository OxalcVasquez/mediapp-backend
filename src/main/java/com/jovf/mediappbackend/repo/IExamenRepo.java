package com.jovf.mediappbackend.repo;

import com.jovf.mediappbackend.model.Examen;

//@Repository - No es necesario debido que se redundaría por que Spring entiende que se extiende de JPA
public interface IExamenRepo extends IGenericRepo<Examen,Integer> {

}
