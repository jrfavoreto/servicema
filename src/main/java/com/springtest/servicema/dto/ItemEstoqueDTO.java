package com.springtest.servicema.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemEstoqueDTO {
    
    private Long id;
     
    private String descricao;
 
    private Double valor;

    private Long lote_fk;
}
