package com.example.rabbitmq.java_work;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class WorkCustomer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        assert connection != null;
        Channel channel = connection.createChannel();
        // 每次只消费一个消息
        channel.basicQos(1);
        channel.queueDeclare("work", true,false,false,null);
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2: " + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }

        });

    }
}
