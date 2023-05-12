package com.sk.scott.producer;

import com.sk.scott.message.Demo02Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Demo02Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        Demo02Message message = new Demo02Message();
        message.setId(id);

        //同步消息
        rabbitTemplate.convertAndSend(Demo02Message.EXCHANGE,Demo02Message.ROUTING_KEY,message);
    }

    public void syncSendDefault(Integer id){
        Demo02Message message = new Demo02Message();
        message.setId(id);
        rabbitTemplate.convertAndSend(Demo02Message.QUEUE1,message);
    }

    @Async
    public ListenableFuture<Void> asyncSend(Integer id){
        try {
            this.syncSend(id);
            return AsyncResult.forValue(null);
        } catch (Throwable ex){
            return AsyncResult.forExecutionException(ex);
        }
    }
}
