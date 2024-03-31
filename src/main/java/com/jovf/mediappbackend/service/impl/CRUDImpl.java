package com.jovf.mediappbackend.service.impl;

import com.jovf.mediappbackend.repo.IGenericRepo;
import com.jovf.mediappbackend.service.ICRUD;

import java.util.List;

public abstract class CRUDImpl<T,ID> implements ICRUD<T,ID> {


    protected abstract IGenericRepo getRepo();

    @Override
    public T registrar(T t) throws Exception {
        return (T) getRepo().save(t);
    }

    @Override
    public T modificar(T t) throws Exception {
        return (T) getRepo().save(t);
    }

    @Override
    public List<T> listar() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T listarPorId(ID id) throws Exception {
        return (T) getRepo().findById(id).orElse(null);
    }

    @Override
    public void eliminar(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
