package com.example.springbootdemo;

import com.example.springbootdemo.service.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {
    @Autowired
    Sender sender;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mqTest() {
        sender.send();
    }
}
