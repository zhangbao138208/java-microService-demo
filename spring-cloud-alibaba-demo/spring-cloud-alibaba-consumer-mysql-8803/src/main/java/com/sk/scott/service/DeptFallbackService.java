package com.sk.scott.service;

import com.sk.scott.entity.CommonResult;
import com.sk.scott.entity.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DeptFallbackService implements DeptFeignService {
    @Override
    public CommonResult<Dept> get(int id) {
        log.info("get fallback"+id);
       return new CommonResult<>(500,"eee");
    }

    @Override
    public CommonResult<List<Dept>> list() {
        log.info("list fallback"+String.valueOf(System.currentTimeMillis()));
       return new CommonResult<>(500,"'dd");
    }
}
