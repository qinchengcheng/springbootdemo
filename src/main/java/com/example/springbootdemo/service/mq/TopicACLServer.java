package com.example.springbootdemo.service.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author qin
 * @description
 * @date 2019/6/10
 */
public class TopicACLServer {
    public static final String VHOST="vhost1";
    public static final String TOPIC_EXCHANGE="topicExchange";
    public static final String USER_NAME="qin";
    public static final String PWD="123456";
    public static void main(String[] args) throws IOException, TimeoutException{
        ConnectionFactory factory =new ConnectionFactory();
        factory.setVirtualHost(VHOST);
        factory.setUsername(USER_NAME);
        factory.setPassword(PWD);
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(TOPIC_EXCHANGE, "topic");
        channel.basicPublish(TOPIC_EXCHANGE, "log.controller", null,
                "this is controller log".getBytes("utf-8"));
        channel.basicPublish(TOPIC_EXCHANGE, "log.service", null,
                "this is service log".getBytes("utf-8"));
        channel.basicPublish(TOPIC_EXCHANGE, "log.model", null,
                "this is model log".getBytes("utf-8"));
        // 无法成功发布消息：reply-text=ACCESS_REFUSED - access to topic 'other\.*' in exchange，即没有该routing key的权限
        // channel.basicPublish(TOPIC_EXCHANGE, "other.key", null,
        //     "this is other log".getBytes("utf-8"));
        channel.close();
        connection.close();
    }
}
