package com.sk.scott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAsync
public class Lab01RabbitMqDemo
{
    public static void main( String[] args )
    {
        SpringApplication.run(Lab01RabbitMqDemo.class,args);
    }
}
