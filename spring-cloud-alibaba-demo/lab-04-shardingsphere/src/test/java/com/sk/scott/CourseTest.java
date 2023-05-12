package com.sk.scott;

import com.sk.scott.mapper.CourseMapper;
import com.sk.scott.model.course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class CourseTest {
    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void testInsertOne(){
        course course = new course();
       // course.setCid(66666L);
        course.setCname("nam1");
        course.setUserId(33L);
        courseMapper.insert(course);
    }

    @Test
    public void testInsert(){
        for (int i = 0; i < 100; i++) {
            course course = new course();
            // course.setCid(66666L);
            course.setCname("name"+i);
            course.setUserId((long) i);
            courseMapper.insert(course);
        }
    }

    @Test
    public void testQuery(){
       List<course> courseList = courseMapper.selectList(null);
       courseList.forEach(System.out::println);
    }
}
