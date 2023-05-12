package com.sk.scott.controller;

import com.sk.scott.entity.CommonResult;
import com.sk.scott.entity.Dept;
import com.sk.scott.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;
@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Value("${server.port}")
    private String serverPort;
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public CommonResult<Dept> get(@PathVariable("id") int id) {
        log.info("端口：" + serverPort + "\t+ dept/get/");
        try {
            TimeUnit.SECONDS.sleep(1);
            log.info("休眠 1秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Dept dept = deptService.get(id);
        CommonResult<Dept> result = new CommonResult(200, "from mysql,serverPort:  " + serverPort, dept);
        return result;
    }
    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public CommonResult<List<Dept>> list() {
        log.info("端口：" + serverPort + "\t+ dept/list/");
        List<Dept> depts = deptService.selectAll();
        CommonResult<List<Dept>> result = new CommonResult(200, "from mysql,serverPort:  " + serverPort, depts);
        return result;
    }
}