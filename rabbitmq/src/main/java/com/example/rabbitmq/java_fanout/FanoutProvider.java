package com.example.rabbitmq.java_fanout;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FanoutProvider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        assert connection != null;
        Channel channel = connection.createChannel();

        /**
         * 指定交换机：
         * 参1：交换机名称，根据业务自定义
         * 参2：交换机类型，为fanout时，是广播类型
         * */
        channel.exchangeDeclare("logs","fanout");

        channel.queueDeclare("work", true,false,false,null);

        channel.basicPublish("logs", "", MessageProperties.PERSISTENT_TEXT_PLAIN,  " hello fanout".getBytes(StandardCharsets.UTF_8));

        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
        System.out.println("执行完毕");
    }
}
