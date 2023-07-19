package com.springtest.servicema.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.servicema.dominio.ItemEstoque;
import com.springtest.servicema.dominio.Lote;
import com.springtest.servicema.repositorio.ItemEstoqueRepository;
import com.springtest.servicema.repositorio.LoteRepository;

@RestController
@RequestMapping("/api")
public class ItemEstoqueController {
       
    @Autowired
    private ItemEstoqueRepository itemEstoqueRepository;
    @Autowired
    private LoteRepository loteRepository;

    @GetMapping("/itemestoque/{id}")
    public ResponseEntity<ItemEstoque> getById(@PathVariable("id") Long id) {
        var item = itemEstoqueRepository.findById(id);

        if (!item.isEmpty()) {
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/itemestoques")
    public ResponseEntity<List<ItemEstoque>> getAll() {
        
        List<ItemEstoque> items = new ArrayList<ItemEstoque>();
        
            itemEstoqueRepository.findAll().forEach(items::add);
        
        if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(items, HttpStatus.OK);

    }
   
    @PostMapping("/itemestoques")
     public ResponseEntity<String> createOrUpdate(@RequestBody ItemEstoque item) {
        
            ItemEstoque newItem = new ItemEstoque();
            
            newItem.setId(item.getId());
            newItem.setDescricao(item.getDescricao());
            newItem.setValor(item.getValor());
            
            var queryLote = loteRepository.findById( item.getLoteFk());
            if(queryLote.isEmpty())
                return new ResponseEntity<>(String.format("Lote Id=%s não existe. Informe um lote existente!", item.getLoteFk()),
                    HttpStatus.INTERNAL_SERVER_ERROR);

            newItem.setLoteFk(item.getLoteFk());

            itemEstoqueRepository.save(newItem);

            return new ResponseEntity<>(String.format("Item ID=%s salvo com sucesso.", newItem.getId().toString()),
             HttpStatus.CREATED);

       
    }

}
