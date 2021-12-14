package com.example.rabbitmq.springboot_direct;


import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectConsumer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directs", type = "direct"),
                    key = {"info", "error", "warning"}
            )
    })
    public void consumer1(String message){
        System.out.println("消费者1：" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "directs", type = "direct"),
                    key = {"info"}
            )
    })
    public void consumer2(String message){
        System.out.println("消费者2：" + message);
    }
}
