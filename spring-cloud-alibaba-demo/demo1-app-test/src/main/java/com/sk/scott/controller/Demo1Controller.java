package com.sk.scott.controller;

import com.sk.scott.config.scottAutoConfiguration;
import com.sk.scott.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo1Controller {
    @Autowired
    private scottAutoConfiguration _scottAutoConfiguration;
    @Autowired
    private Dept dept;

    @GetMapping("v1/demo1")
    public String demo1(){
        System.out.println(_scottAutoConfiguration.getNames().toString());
        return _scottAutoConfiguration.getNames().toString()+dept.getId();
    }
}
