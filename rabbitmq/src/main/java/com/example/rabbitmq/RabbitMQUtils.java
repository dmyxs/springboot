package com.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQUtils {
    private static final ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
        // connectionFactory.setHost("127.0.0.1");
        // connectionFactory.setPort(5672);
        // connectionFactory.setVirtualHost("dmyxs");
        // connectionFactory.setUsername("admin");
        // connectionFactory.setPassword("123456");
    }

    public static Connection getConnectionFactory(){
        try {
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnectionAndChanel(Channel channel, Connection connection){
        try {
            if(channel != null) channel.close();
            if(connection != null) connection.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
