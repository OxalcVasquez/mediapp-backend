package com.jovf.mediappbackend.repo;

import com.jovf.mediappbackend.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository - No es necesario debido que se redundaría por que Spring entiende que se extiende de JPA
public interface IPacienteRepo extends JpaRepository<Paciente,Integer> {

}
