package com.example.rabbitmq.java_fanout;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class FanoutCustomer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnectionFactory();
        assert connection != null;
        Channel channel = connection.createChannel();

        /**
         * 交换机定义
         * 参1：交换机名称，和provider定义的一样
         * 参2：交换机类型
         * */
        channel.exchangeDeclare("logs", "fanout");

        // 生产临时队列，减轻负担
        String queueName = channel.queueDeclare().getQueue();
        // 绑定交换机和队列
        /**
         * 绑定交换机
         * 参1：队列名称，获取到的名称
         * 参2：交换机名称，和provider定义的一样
         * 参3：fanout用不到
         * */
        channel.queueBind(queueName,"logs","");

        // 消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                System.out.println("消费者1: " + new String(body));
            }
        });

    }
}