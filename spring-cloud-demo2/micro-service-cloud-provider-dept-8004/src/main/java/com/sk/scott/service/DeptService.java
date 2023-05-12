package com.sk.scott.service;

import com.sk.scott.entity.Dept;

import java.util.List;

public interface DeptService {
    Dept get(Integer depNo);
    List<Dept> selectAll();
}
