package com.jovf.mediappbackend.service.impl;

import com.jovf.mediappbackend.model.Medico;
import com.jovf.mediappbackend.model.Paciente;
import com.jovf.mediappbackend.repo.IGenericRepo;
import com.jovf.mediappbackend.repo.IMedicoRepo;
import com.jovf.mediappbackend.repo.IPacienteRepo;
import com.jovf.mediappbackend.service.IMedicoService;
import com.jovf.mediappbackend.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MedicoServiceImpl extends CRUDImpl<Medico,Integer> implements IMedicoService {

    @Autowired
    private IMedicoRepo repo;


    @Override
    protected IGenericRepo<Medico,Integer> getRepo() {
        return repo;
    }
}
