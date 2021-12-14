package com.example.rabbitmq.java_topic;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class TopicConsumer2 {
    public static void main(String[] args) throws IOException {
        Connection connectionFactory = RabbitMQUtils.getConnectionFactory();
        assert connectionFactory != null;
        Channel channel = connectionFactory.createChannel();
        String exchangeName = "topics";
        channel.exchangeDeclare(exchangeName, "topic");

        String queueName = channel.queueDeclare().getQueue();
        /**
         * 这里使用通配符
         * */
        channel.queueBind(queueName,exchangeName,"user.#");

        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                System.out.println("消费者2: " + new String(body));
            }
        });
    }
}
