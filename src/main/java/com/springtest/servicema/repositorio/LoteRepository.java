package com.springtest.servicema.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springtest.servicema.dominio.Lote;

public interface LoteRepository extends CrudRepository<Lote, Long> {
 
    List<Lote> findByNumero(String numero);
}
