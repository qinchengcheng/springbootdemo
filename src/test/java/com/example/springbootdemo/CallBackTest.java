package com.example.springbootdemo;

import com.example.springbootdemo.service.Receiver;
import com.example.springbootdemo.service.Sender;
import com.example.springbootdemo.service.callback.Ricky;
import com.example.springbootdemo.service.callback.Student;
import com.example.springbootdemo.service.callback.Teacher;
import com.example.springbootdemo.utils.LatchTask;
import com.example.springbootdemo.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallBackTest {
    @Autowired
    Sender sender;

    @Autowired
    Receiver receiver;
    @Autowired
    RedisUtil redisUtil;

    @Test
    public void contextLoads() {
        Student student = new Ricky();
        Teacher teacher = new Teacher(student);
        teacher.askQuestion();
    }
}
