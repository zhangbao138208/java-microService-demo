package com.sk.scott;

import com.baomidou.lock.annotation.Lock4j;
import com.sk.scott.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
@SpringBootTest(classes = App.class)
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {

        SpringApplication.run(App.class,args);
    }

    @SneakyThrows
    @Test
    public void simple1Test() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable task = () -> {
            try {
                userService.simple2("xxx_key");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        for (int i = 0; i < 1000; i++) {
            executorService.submit(task);
        }
        Thread.sleep(Long.MAX_VALUE);
    }

    @Lock4j(keys = "#myKey")
    public void simple2(String myKey) {
        System.out.println("执行简单方法2 , 当前线程:" + Thread.currentThread().getName() + " , counter：" );

    }

    @Autowired
    UserService userService;
    @Lock4j(keys = "#myKey")
    public void simple(String myKey) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //do something
        System.out.println("执行简单方法2 , 当前线程:" + Thread.currentThread().getName() + " , counter：");
    }
}
