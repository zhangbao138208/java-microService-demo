package com.sk.scott.service.impl;

import com.sk.scott.entity.Dept;
import com.sk.scott.mapper.DeptMapper;
import com.sk.scott.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Dept get(Integer deptNo) {
       return deptMapper.selectByPrimaryKey(deptNo);
    }

    @Override
    public List<Dept> selectAll() {
        return deptMapper.GetAll();
    }
}
