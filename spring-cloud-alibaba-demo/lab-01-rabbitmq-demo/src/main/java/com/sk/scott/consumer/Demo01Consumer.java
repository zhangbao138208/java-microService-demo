package com.sk.scott.consumer;

import com.sk.scott.message.Demo01Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Component
@RabbitListener(queues = Demo01Message.QUEUE)
public class Demo01Consumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void onMessage(Demo01Message message){
        logger.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        try {
            //模仿数据库插入阻塞
            Thread.sleep(200);
             throw new InvalidParameterException("test err ");

        } catch (InterruptedException e) {
           // throw new RuntimeException(e);
            e.printStackTrace();
        }
    }

}
