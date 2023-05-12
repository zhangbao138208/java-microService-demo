package com.sk.scott.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sk.scott.DTO.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}
