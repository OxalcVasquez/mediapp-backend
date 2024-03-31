package com.jovf.mediappbackend.service.impl;

import com.jovf.mediappbackend.model.Especialidad;
import com.jovf.mediappbackend.repo.IEspecialidadRepo;
import com.jovf.mediappbackend.repo.IGenericRepo;
import com.jovf.mediappbackend.service.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EspecialidadServiceImpl extends CRUDImpl<Especialidad,Integer> implements IEspecialidadService {

    @Autowired
    private IEspecialidadRepo repo;

    @Override
    protected IGenericRepo<Especialidad,Integer> getRepo() {
        return repo;
    }

}
