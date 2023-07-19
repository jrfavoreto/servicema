package com.springtest.servicema.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoteDTO {
   
    private Long id;
     
    private String numero;

    private List<ItemEstoqueDTO> Itens = new ArrayList<ItemEstoqueDTO>();
}
