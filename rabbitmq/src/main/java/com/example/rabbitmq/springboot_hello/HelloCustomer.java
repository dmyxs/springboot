package com.example.rabbitmq.springboot_hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class HelloCustomer {

    @RabbitHandler
    public void sayHi(String message){
        System.out.println("消费者：" + message);
    }

}
