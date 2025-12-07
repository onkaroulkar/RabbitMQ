package com.onkar.HeaderExchange;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersPublisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for mobile and TV";
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("item1","mobile");
        headerMap.put("item2","television");

        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().headers(headerMap).build();
        channel.basicPublish("Headers-Exchage","",props,message.getBytes());
        channel.close();
        connection.close();
    }
}
