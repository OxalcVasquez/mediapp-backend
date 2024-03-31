package com.jovf.mediappbackend.service.impl;

import com.jovf.mediappbackend.model.Consulta;
import com.jovf.mediappbackend.repo.IConsultaRepo;
import com.jovf.mediappbackend.repo.IGenericRepo;
import com.jovf.mediappbackend.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta,Integer> implements IConsultaService {

    @Autowired
    private IConsultaRepo repo;

    @Override
    protected IGenericRepo<Consulta,Integer> getRepo() {
        return repo;
    }

}
