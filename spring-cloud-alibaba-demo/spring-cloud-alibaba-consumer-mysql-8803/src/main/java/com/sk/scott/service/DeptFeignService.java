package com.sk.scott.service;

import com.sk.scott.entity.CommonResult;
import com.sk.scott.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
@Component
@FeignClient(value = "spring-cloud-alibaba-provider-mysql",fallback = DeptFallbackService.class)
public interface DeptFeignService {
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public CommonResult<Dept> get(@PathVariable("id") int id);
    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public CommonResult<List<Dept>> list();
}