package com.sk.scott.producer;

import com.sk.scott.message.ManualAckMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

//@Component
public class ManualAckProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id){
        ManualAckMessage message = new ManualAckMessage();
        message.setId(id);

        //同步消息
        rabbitTemplate.convertAndSend(ManualAckMessage.EXCHANGE,ManualAckMessage.ROUTING_KEY,id.toString());
    }

    public void syncSendDefault(Integer id){
        ManualAckMessage message = new ManualAckMessage();
        message.setId(id);
        rabbitTemplate.convertAndSend(ManualAckMessage.QUEUE,message);
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
