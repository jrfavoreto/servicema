package com.springtest.servicema.repositorio;


import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.springtest.servicema.dominio.ItemEstoque;

@Repository
public interface ItemEstoqueRepository extends CrudRepository<ItemEstoque, Long> {
 
     @Query("""
            SELECT i.*
            FROM item_estoque i 
                INNER JOIN lote l ON i.lote_fk = l.id
            WHERE i.lote_fk = :loteId
            """)
    Iterable<ItemEstoque> findByLote(Long loteId);
}
