package com.example.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.Query;

/**
 * @author qin
 * @description rabbit配置类
 * @date 2019/6/9
 */
@Configuration
public class RabbitConfig {
    @Bean
    public Query helloQueue(){
        return new Query();
    }
}
