package com.example.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class RabbitmqApplicationTests {

    /**
     * springboot为我们提供了操作rabbitmq的模板
     * */
    @Resource
    private  RabbitTemplate rabbitTemplate;


    @Test
    void helloProvider() {
        rabbitTemplate.convertAndSend("hello", "hello world");
    }

    @Test
    void workProvider() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work", "hello work");
        }
    }

    @Test
    void fanoutProvider() {
        rabbitTemplate.convertAndSend("logs", "","hello fanout");
    }

    @Test
    void directProvider() {
        rabbitTemplate.convertAndSend("directs", "warning","hello direct");
    }

    @Test
    void topicProvider() {
        rabbitTemplate.convertAndSend("topics", "product.save","hello topic");
    }
}
