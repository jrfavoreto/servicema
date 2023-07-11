package com.springtest.servicema;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.servicema.dominio.Lote;
import com.springtest.servicema.repositorio.LoteRepository;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueSender sender;
    
    @Autowired
    private LoteRepository loteRepository;

    @GetMapping("/send")
    public String send(@RequestParam String msg){
        sender.send("Estoque msg: "+ msg + " consumido!");
        return "ok. done";
    }

    @GetMapping("/lotes/{id}")
    public ResponseEntity<Lote> getLoteById(@PathVariable("id") Long id) {
        var lote = loteRepository.findById(id);

        if (!lote.isEmpty()) {
            return new ResponseEntity<>(lote.get(), HttpStatus.OK);
        } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lotes")
    public ResponseEntity<List<Lote>> getAllLotes() {
        try {
            List<Lote> lotes = new ArrayList<Lote>();
            
                loteRepository.findAll().forEach(lotes::add);
            
            if (lotes.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lotes, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage() );
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
    @PostMapping("/lotes")
     public ResponseEntity<String> createLote(@RequestBody Lote lote) {
        try {
            Lote newLote = new Lote();
            newLote.setId((lote.getId()));
            newLote.setNumero(lote.getNumero());
            
            loteRepository.save(newLote);
            return new ResponseEntity<>("Lote salvo com sucesso.", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
