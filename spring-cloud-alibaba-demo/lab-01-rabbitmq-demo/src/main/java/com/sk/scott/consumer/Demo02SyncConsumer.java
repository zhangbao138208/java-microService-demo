package com.sk.scott.consumer;

import com.sk.scott.message.Demo02Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component

public class Demo02SyncConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    @RabbitListener(queues = Demo02Message.QUEUE1)
    @Async
     public void onMessage1(Demo02Message message){
        logger.info("[sync onMessage1][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        try {
            //模仿数据库插入阻塞
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitHandler
    @RabbitListener(queues = Demo02Message.QUEUE2)
    @Async
    public void onMessage2(Demo02Message message){
        logger.info("[sync onMessage2][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        try {
            //模仿数据库插入阻塞
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}