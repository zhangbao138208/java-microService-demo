package com.sk.scott;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApp
{
    public static void main( String[] args )
    {

        SpringApplication.run(HystrixDashboardApp.class,args);
    }
}
