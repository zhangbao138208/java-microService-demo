package com.sk.scott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerNacos8801
{
    public static void main( String[] args )
    {
        SpringApplication.run(ConsumerNacos8801.class,args);
    }
}
