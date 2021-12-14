package com.example.rabbitmq.java_direct;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DirectProvider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        assert connection != null;
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";

        // 使用direct
        channel.exchangeDeclare(exchangeName,"direct");
        String routingKey = "xxx";

        channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,  ("hello direct " + routingKey).getBytes(StandardCharsets.UTF_8));

        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
        System.out.println("执行完毕");
    }
}
