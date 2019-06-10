package com.example.springbootdemo.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author qin
 * @description 生产者
 * @date 2019/6/9
 */
@Component
public class Sender {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void send(){
        String context="hello"+new Date();
        System.out.println("Sender :"+context);
        this.amqpTemplate.convertAndSend("hello",context);
    }
}
