package com.sk.scott.service;

import org.springframework.stereotype.Component;

@Component
public class DeptHystrixFallBackService implements DeptHystrixService{
    @Override
    public String deptInfo_Ok(Integer id) {
       return "------------解耦方法ok回退-----------";
    }

    @Override
    public String deptInfo_Timeout(Integer id) {
        return "----------------解耦方法timeout 回退-------------------";
    }
}
