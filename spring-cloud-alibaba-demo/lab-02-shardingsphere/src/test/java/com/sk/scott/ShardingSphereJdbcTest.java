package com.sk.scott;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sk.scott.DTO.Course;
import com.sk.scott.Mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingsphereJdbcDemoApp.class)


public class ShardingSphereJdbcTest {

    @Resource
    private CourseMapper courseMapper;

    @Test
    public void testCourse(){
        for (int i = 0; i < 100; i++) {
            Course c = new Course();
            c.setCName("java");
            c.setUserId(10001);
            c.setCStatus(1);
            courseMapper.insert(c);
        }
    }

    @Test
    public void queryCourse(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",860226806765064193L);

        List<Course> courses = courseMapper.selectList(wrapper);

        System.out.println("-----------------");
        courses.forEach(System.out::println);
    }

    @Test
    public void rangeQuery(){
       Long l = 860259981193121793L;
       int mask = 10;
        System.out.println(mask);
       // int l = 3;
        System.out.println("----------");
        System.out.println(l%mask);
        System.out.println(l&mask+1);
        System.out.println((l&mask+1)%4);
        System.out.println(((l&mask+1)%4)/2);
        System.out.println(((l&mask+1)%4)/2+1);
       // System.out.println(l.toString());
        System.out.println(l);
    }
    @Test
    public void BigInter(){
        Integer mask = (int) (Math.pow(2,3) -1);
        BigInteger shardingValuesB = new BigInteger("860259981193121793") ;
        BigInteger resB = shardingValuesB.mod(new BigInteger("10")).add(new BigInteger("1")).
                mod(new BigInteger("4"));
        var resC =  resB.intValue()/2 +1;
        var c = shardingValuesB.and(new BigInteger(mask.toString()));
        System.out.println(c.and(new BigInteger("1")));
        System.out.println(c.and(new BigInteger("1")).add(new BigInteger("1")));
        System.out.println(c.add(new BigInteger("1")).mod(new BigInteger("4")));
        System.out.println(c.add(new BigInteger("1")).mod(new BigInteger("4")).intValue()/2);
        System.out.println("----------");
        System.out.println(resC);
    }
}
