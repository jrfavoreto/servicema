package com.springtest.servicema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueSender sender;

    @GetMapping("/send")
    public String send(@RequestParam String msg){
        sender.send("Estoque msg: "+ msg + " consumido!");
        return "ok. done";
    }

}
