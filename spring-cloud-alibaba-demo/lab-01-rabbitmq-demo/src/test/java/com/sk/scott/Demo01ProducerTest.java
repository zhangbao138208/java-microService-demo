package com.sk.scott;

import com.sk.scott.producer.Demo01Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Lab01RabbitMqDemo.class)
public class Demo01ProducerTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo01Producer demo01Producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int id = (int) (System.currentTimeMillis()/1000);
            demo01Producer.syncSend(id);
            logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            Thread.sleep(2000);
        }
        //阻塞
        new CountDownLatch(1).await();
    }
}
