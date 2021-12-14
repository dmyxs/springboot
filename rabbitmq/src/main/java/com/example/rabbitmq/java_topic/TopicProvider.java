package com.example.rabbitmq.java_topic;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TopicProvider {
    public static void main(String[] args) throws IOException {
        Connection connectionFactory = RabbitMQUtils.getConnectionFactory();
        Channel channel = connectionFactory.createChannel();
        String exchangeName = "topics";

        // topic模型
        channel.exchangeDeclare(exchangeName, "topic");

        // 发送消息
        String routeKey = "user.save.find";
        channel.basicPublish(exchangeName, routeKey, null, ("hello topic " + routeKey).getBytes(StandardCharsets.UTF_8));

        RabbitMQUtils.closeConnectionAndChanel(channel, connectionFactory);
        System.out.println("执行完毕");
    }
}
