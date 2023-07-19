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

import com.springtest.servicema.dominio.Lote;
import com.springtest.servicema.dto.ItemEstoqueDTO;
import com.springtest.servicema.dto.LoteDTO;
import com.springtest.servicema.repositorio.ItemEstoqueRepository;
import com.springtest.servicema.repositorio.LoteRepository;

@RestController
@RequestMapping("/api")
public class LoteController {
       
    @Autowired
    private LoteRepository loteRepository;
    @Autowired
    private ItemEstoqueRepository itemEstoqueRepository;

    @GetMapping("/lotes/{id}")
    public ResponseEntity<LoteDTO> getLoteById(@PathVariable("id") Long id) {
        var queryLote = loteRepository.findById(id);

        if (!queryLote.isEmpty()) {
            var lote = queryLote.get();
            LoteDTO loteDTO = new LoteDTO();
            loteDTO.setId(lote.getId());
            loteDTO.setNumero(lote.getNumero());
            
            var itensDTO = new ArrayList<ItemEstoqueDTO>();
            itemEstoqueRepository.findByLote(lote.getId()).forEach ((i) -> {
                var obj = new ItemEstoqueDTO();
                obj.setId(i.getId());
                obj.setDescricao(i.getDescricao());
                obj.setValor(i.getValor());
                obj.setLote_fk(i.getLoteFk());
                itensDTO.add(obj);
            });

            loteDTO.setItens(itensDTO);

            return new ResponseEntity<>(loteDTO, HttpStatus.OK);
        } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lotes")
    public ResponseEntity<List<Lote>> getAllLotes() {
            List<Lote> lotes = new ArrayList<Lote>();
            
            loteRepository.findAll().forEach(lotes::add);
            
            if (lotes.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lotes, HttpStatus.OK);

    }
   
    @PostMapping("/lotes")
     public ResponseEntity<String> createOrUpdateLote(@RequestBody Lote lote) {
        
            Lote newLote = new Lote();
            //Se setar o id, o objeto será atualizado.
            newLote.setId(lote.getId()); 
            newLote.setNumero(lote.getNumero());

            loteRepository.save(newLote);

            return new ResponseEntity<>(String.format("Lote ID=%s salvo com sucesso.", newLote.getId().toString()),
             HttpStatus.CREATED);

    }

}
