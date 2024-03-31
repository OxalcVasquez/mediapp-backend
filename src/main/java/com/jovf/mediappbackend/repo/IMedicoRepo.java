package com.jovf.mediappbackend.repo;

import com.jovf.mediappbackend.model.Medico;
//@Repository - No es necesario debido que se redundaría por que Spring entiende que se extiende de JPA
public interface IMedicoRepo extends IGenericRepo<Medico,Integer> {

}
