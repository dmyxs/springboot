package com.example.rabbitmq.java_work;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WorkProvider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        assert connection != null;
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", true,false,false,null);

        for (int i = 0; i < 20; i++){
            channel.basicPublish("", "work", MessageProperties.PERSISTENT_TEXT_PLAIN, (i + " hello work").getBytes(StandardCharsets.UTF_8));
        }

        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
        System.out.println("执行完毕");
    }
}
