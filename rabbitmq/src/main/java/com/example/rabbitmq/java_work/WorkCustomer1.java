package com.example.rabbitmq.java_work;

import com.example.rabbitmq.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

public class WorkCustomer1 {
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
                // 模拟慢任务
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("消费者1: " + new String(body));

                /**
                 * 任务确认：
                 * 参1：获取确认队列中的具体信息
                 * 参2：是否开启多个消息同时确实
                 * */
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

    }
}
