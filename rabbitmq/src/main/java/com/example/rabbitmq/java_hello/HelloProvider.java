package com.example.rabbitmq.java_hello;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HelloProvider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        assert connection != null;
        Channel channel = connection.createChannel();

        /**
         * 消息队列声明：
         * 参1：队列名称，如果不存在则自动创建
         * 参2：用来定义队列是否持久化(非消息)，持久化：MQ服务停止后，队列写到磁盘，消息消失
         * 参3：是否独占队列，一般使用false
         * 参4：是否在消费完成后自动删除队列
         * 参5：额外附加参数
         * */
        channel.queueDeclare("hello", true,false,false,null);

        /**
         * 消息发布：
         * 参1：交换机名称
         * 参2：队列名称
         * 参3：传递额外属性 null / MessageProperties.PERSISTENT_TEXT_PLAIN(消息持久化)
         * 参4：消息的具体内容
         * */
        channel.basicPublish("", "hello", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes(StandardCharsets.UTF_8));

        RabbitMQUtils.closeConnectionAndChanel(channel, connection);
        System.out.println("执行完毕");
    }
}
