package com.sk.scott.mapper;

import com.sk.scott.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {
   Dept selectByPrimaryKey(Integer depNo);
   List<Dept> GetAll();
}
