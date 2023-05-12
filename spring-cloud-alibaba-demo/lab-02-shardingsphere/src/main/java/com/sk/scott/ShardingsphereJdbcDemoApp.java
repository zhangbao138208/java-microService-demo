package com.sk.scott;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.sk.scott.Mapper"})
public class ShardingsphereJdbcDemoApp 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ShardingsphereJdbcDemoApp.class,args);
    }
}
