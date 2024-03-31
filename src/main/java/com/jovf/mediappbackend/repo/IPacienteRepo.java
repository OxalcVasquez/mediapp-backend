package com.jovf.mediappbackend.repo;

import com.jovf.mediappbackend.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IPacienteRepo extends IGenericRepo<Paciente,Integer> {

}
