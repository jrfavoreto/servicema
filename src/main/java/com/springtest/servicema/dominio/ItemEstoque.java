package com.springtest.servicema.dominio;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Table("item_estoque")
public class ItemEstoque {
    
    @Id
    private Long id;
     
    private String descricao;
 
    private Double valor;

    //@Column("lote_fk")
    private Long loteFk;
 
}
