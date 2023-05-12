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
public class Provider8001
{
    public static void main( String[] args ) {
        SpringApplication.run(Provider8001.class,args);
    }
}
