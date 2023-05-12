package com.sk.scott;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class AtomicTest {

    Double amount = 100.00;
    @Test
    public void testAtomic() throws InterruptedException {
        System.out.println("start");
        var concurrentNum = 2;
        CountDownLatch latch = new CountDownLatch(concurrentNum);
        for (int i = 0; i < concurrentNum; i++) {
            new Thread(() -> {
                try {
                   if (amount >= 100 ) {
                       //模仿消息队列消费的问题
                       Thread.sleep(100);
                       amount = amount - 100;
                   }

                  //  System.out.println("dd");

                }catch (Exception e){

                }finally {
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
        assert amount>=0:"amount less than 0";
    }
}
