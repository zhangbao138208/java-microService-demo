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
public class ProviderMysql8003
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProviderMysql8003.class,args);
    }
}
