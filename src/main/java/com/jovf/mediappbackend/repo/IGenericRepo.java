package com.jovf.mediappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean //Le dice a spring que JPA no genere la clase de generico, solo como una forma de configuracion
public interface IGenericRepo<T,ID> extends JpaRepository<T, ID> {

}
