package com.jovf.mediappbackend.service.impl;

import com.jovf.mediappbackend.model.Examen;
import com.jovf.mediappbackend.repo.IExamenRepo;
import com.jovf.mediappbackend.repo.IGenericRepo;
import com.jovf.mediappbackend.repo.IPacienteRepo;
import com.jovf.mediappbackend.service.IExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExamenServiceImpl extends CRUDImpl<Examen,Integer> implements IExamenService {

    @Autowired
    private IExamenRepo repo;

    @Override
    protected IGenericRepo<Examen,Integer> getRepo() {
        return repo;
    }

}
