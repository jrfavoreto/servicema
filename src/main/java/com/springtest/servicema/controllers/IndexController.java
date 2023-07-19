package com.springtest.servicema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springtest.servicema.rabbitmq.EstoqueSender;

@RestController
public class IndexController {
 
    @Autowired
    private EstoqueSender sender;
    
    @GetMapping("/")
    public String index(){
        return "Serviço ok - index()!";
    }

    @GetMapping("/send")
    public String send(@RequestParam String msg){
        sender.send("Estoque msg: "+ msg + " consumido!");
        return "ok. done";
    }

}