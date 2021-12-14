package com.example.rabbitmq.java_direct;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class DirectConsumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        assert connection != null;
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";

        channel.exchangeDeclare(exchangeName, "direct");

        // 生产临时队列，减轻负担
        String queueName = channel.queueDeclare().getQueue();
        // 绑定交换机和队列
        channel.queueBind(queueName,exchangeName,"info");
        channel.queueBind(queueName,exchangeName,"error");
        channel.queueBind(queueName,exchangeName,"warning");

        // 消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                System.out.println("消费者2: " + new String(body));
            }
        });

    }
}
