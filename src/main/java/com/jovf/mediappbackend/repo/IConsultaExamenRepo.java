package com.jovf.mediappbackend.repo;

import com.jovf.mediappbackend.model.ConsultaExamen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IConsultaExamenRepo extends IGenericRepo<ConsultaExamen,Integer> {

    //SQL Nativo
    @Modifying //Cuando se hacen operaciones de insert update o delete, no espera ResultSet
    @Query(value = "INSERT INTO consulta_examen(id_consulta,id_examen) VALUES (:idConsulta, :idExamen)", nativeQuery = true)
    Integer registrar(@Param("idConsulta") Integer idConsulta,@Param("idExamen") Integer idExamen);
}
