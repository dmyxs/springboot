package com.example.rabbitmq.springboot_fanout;


import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class FanoutCustomer {
    @RabbitListener(bindings = {
            @QueueBinding(
                    // @Queue不命名是临时队列
                    value = @Queue,
                    // 交换机绑定
                    exchange = @Exchange(value = "logs", type = "fanout")
            )
    })
    public void consumer1(String message){
        System.out.println("消费者1：" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs", type = "fanout")
            )
    })
    public void consumer2(String message){
        System.out.println("消费者2：" + message);
    }
}