package com.sk.scott;

import com.sk.scott.model.User;
import com.sk.scott.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class LockEfficiency {

    @Autowired
    UserService userService;

    @Test
    public void testLock() throws InterruptedException {
        List<User> users = new ArrayList<>();
        userService.initCounter(100);
        for (int i = 0; i < 100; i++) {
            var user = new User((long) i,"1");

            users.add(user);

            var user2 = new User((long) i,"2");

            users.add(user2);
        }

        System.out.println((int) users.stream().count());

        var latch = new CountDownLatch((int) users.stream().count());
        long start=System.currentTimeMillis();   //获取开始时间

        users.forEach(user -> {
            new Thread(() -> {
                userService.method1(user);
                latch.countDown();
            }).start();
        });

        latch.await();
        long end=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(end-start)+"ms");


        System.out.println("-----------------------------");
        var   latch2 = new CountDownLatch((int) users.stream().count());
        long start2=System.currentTimeMillis();   //获取开始时间

        users.forEach(user -> {
            new Thread(() -> {
                userService.method1Atomic(user);
                latch2.countDown();
            }).start();
        });

        latch2.await();
        long end2=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序2运行时间： "+(end2-start2)+"ms");



        System.out.println("-----------------------------");
        var   latch3 = new CountDownLatch((int) users.stream().count());
        long start3=System.currentTimeMillis();   //获取开始时间

        users.forEach(user -> {
            new Thread(() -> {
                userService.method1Unsafe(user);
                latch3.countDown();
            }).start();
        });

        latch3.await();
        long end3=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序3运行时间： "+(end3-start3)+"ms");

        userService.printCounter();

    }




}
