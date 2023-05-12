package com.sk.scott.controller;

import com.sk.scott.producer.ManualAckProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class ManualAckController {
//    @Autowired
    private ManualAckProducer manualAckProducer;
    @GetMapping("/ack/{id}")
    public String MenuAck(@PathVariable("id")Integer id){
        manualAckProducer.asyncSend(id);
        return "ok"+id.toString();
    }
}
