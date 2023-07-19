package com.springtest.servicema.dominio;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Lote {
    
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = 'USER_SEQ')
    //@SequenceGenerator(name = 'USER_SEQ', sequenceName = 'USER_SEQ', allocationSize = 1)
    private Long id;
     
    private String numero;

    //private List<ItemEstoque> itens = new ArrayList<>();
}
