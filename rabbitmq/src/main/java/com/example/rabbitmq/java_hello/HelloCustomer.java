package com.example.rabbitmq.java_hello;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class HelloCustomer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        assert connection != null;
        Channel channel = connection.createChannel();
        channel.queueDeclare("hello", true,false,false,null);

        /**
         * 消费队列：
         * 参1：队列名称
         * 参2：是否开启消息的自动确认机制
         * 参3：消费消息时的回调函数，需要接收channel
         * */
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            /**
             * 参1：
             * 参2：
             * 参3：
             * 参4：消息实体
             * */
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body){
                System.out.println("message: " + new String(body));
            }
        });

    }
}
