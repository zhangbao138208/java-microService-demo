package com.sk.scott.service;

public interface DeptService {
    // hystrix 熔断器示例 ok
    public String deptInfo_Ok(Integer id);
    //hystrix 熔断器超时案例
    public String deptInfo_Timeout(Integer id);

    public String deptCircuitBreaker(Integer id);
}
