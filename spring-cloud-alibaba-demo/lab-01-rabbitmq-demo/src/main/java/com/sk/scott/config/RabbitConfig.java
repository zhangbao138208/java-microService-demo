package com.sk.scott.config;

import com.sk.scott.message.Demo01Message;
import com.sk.scott.message.Demo02Message;
import com.sk.scott.message.ManualAckMessage;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    //Direct Exchange 示例配置类
    public static class DirectExchangeDemoConfiguration {
        @Bean
        public Queue demo01Queue(){
            return new Queue(Demo01Message.QUEUE, //Queue 名字
                    true,        //是否持久化
                    false,       //是否排他
                    false             //是否自动删除
            );
        }
        @Bean
        public DirectExchange demo01Exchange() {
            return new DirectExchange(
                    Demo01Message.EXCHANGE,
                    true, //是否持久话
                    false  //是否排他
            );
        }

        @Bean
        public Binding demo01Binding(){
            return BindingBuilder.bind(demo01Queue()).to(demo01Exchange()).with(Demo01Message.ROUTING_KEY);
        }

        @Bean
        public Queue demo2Queue1(){
            return new Queue(Demo02Message.QUEUE1, //Queue 名字
                    true,        //是否持久化
                    false,       //是否排他
                    false             //是否自动删除
            );
        }

        @Bean
        public Queue demo2Queue2(){
            return new Queue(Demo02Message.QUEUE2, //Queue 名字
                    true,        //是否持久化
                    false,       //是否排他
                    false             //是否自动删除
            );
        }

        @Bean
        public FanoutExchange demo2FanoutExchange(){
            return new FanoutExchange(Demo02Message.EXCHANGE,true,false);
        }

        @Bean
        public Binding demo2Binding1(){
            return BindingBuilder.bind(demo2Queue1()).to(demo2FanoutExchange());
        }

        @Bean
        public Binding demo2Binding2(){
            return BindingBuilder.bind(demo2Queue2()).to(demo2FanoutExchange());

        }



        @Bean
        public Queue menuAck01Queue(){
            return new Queue(ManualAckMessage.QUEUE, //Queue 名字
                    true,        //是否持久化
                    false,       //是否排他
                    false             //是否自动删除
            );
        }
        @Bean
        public DirectExchange menuAck01Exchange() {
            return new DirectExchange(
                    ManualAckMessage.EXCHANGE,
                    true, //是否持久话
                    false  //是否排他
            );
        }

        @Bean
        public Binding menuAck01Binding(){
            return BindingBuilder.bind(menuAck01Queue()).to(menuAck01Exchange()).with(ManualAckMessage.ROUTING_KEY);
        }
    }
}
