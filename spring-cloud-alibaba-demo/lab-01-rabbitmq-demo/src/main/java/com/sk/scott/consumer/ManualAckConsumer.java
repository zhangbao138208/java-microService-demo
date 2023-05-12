package com.sk.scott.consumer;

import com.rabbitmq.client.Channel;
import com.sk.scott.message.ManualAckMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;


@Component
public class ManualAckConsumer {
    private Logger logger = LoggerFactory.getLogger(getClass());

    enum Action {
        ACCEPT,
        RETRY,
        REJECT
    }

    private Map<String,Long> retryMap = new HashMap<>();

    @RabbitHandler
    @RabbitListener(queues = ManualAckMessage.QUEUE)
    @Async
    public void onMessage1(String content, Message message, Channel channel){
        logger.info("[sync onMessage1][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        Action action = Action.ACCEPT;
        long tag = message.getMessageProperties().getDeliveryTag();

        try {
           var val = retryMap.get(content);
           if (val==null) {
               val = 0L;
           }
           logger.info("处理器-1接受处理队列A中的消息："+content);
           Thread.sleep(1000);
            if (content.contains("-")&&val<10){
               retryMap.put(content,++val);
                throw new InvalidParameterException(content);
            }
        } catch (Exception e) {
            action = Action.RETRY;
            e.printStackTrace();
        }finally {
            try {
                if (action==Action.ACCEPT) {
                    logger.info("---------accept--------------------");
                    //ack remove message from queue ;false 只确认当前consumer一个消息收到，true确认所有consumer获得者
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                } else if (action==Action.RETRY){
                    logger.info("---------retry--------------------");
                    //确认否定消息，第一个boolean 表示一个consumer还是所有，第二个boolean表示requeue是否重新回到队列，true重新入队
                   channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
                }else {
                    logger.info("---------reject--------------------");
                    //拒绝消息，requeue=false 表示不在重新入队，如果配置死信队列则进入死信队列
                    channel.basicNack(tag,false,false);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}