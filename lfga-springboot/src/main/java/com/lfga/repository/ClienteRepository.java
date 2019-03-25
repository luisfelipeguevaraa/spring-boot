package com.lfga.repository;

import org.springframework.data.repository.CrudRepository;

import com.lfga.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
