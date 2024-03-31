package com.jovf.mediappbackend.service.impl;

import com.jovf.mediappbackend.model.Consulta;
import com.jovf.mediappbackend.model.Examen;
import com.jovf.mediappbackend.repo.IConsultaExamenRepo;
import com.jovf.mediappbackend.repo.IConsultaRepo;
import com.jovf.mediappbackend.repo.IGenericRepo;
import com.jovf.mediappbackend.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ConsultaServiceImpl extends CRUDImpl<Consulta,Integer> implements IConsultaService {

    @Autowired
    private IConsultaRepo repo;

    @Autowired
    private IConsultaExamenRepo ceRepo;

    @Override
    protected IGenericRepo<Consulta,Integer> getRepo() {
        return repo;
    }

    @Transactional //Una sola unidad transaccional
    @Override
    public Consulta registrarTransaccional(Consulta consulta, List<Examen> examenes) throws Exception {
        //Por referencia de memoria sabe a que consulta se refiere
        consulta.getDetalleConsulta().forEach(det-> det.setConsulta(consulta));
        repo.save(consulta);

        examenes.forEach(e -> ceRepo.registrar(consulta.getIdConsulta(),e.getIdExamen()));

        return consulta;

    }
}
