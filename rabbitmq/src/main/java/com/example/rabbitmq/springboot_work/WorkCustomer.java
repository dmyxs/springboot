package com.example.rabbitmq.springboot_work;


import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkCustomer {
    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void customer1(String message){
        System.out.println("消费者1：" + message);
    }

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void customer2(String message){
        System.out.println("消费者2：" + message);
    }
}
