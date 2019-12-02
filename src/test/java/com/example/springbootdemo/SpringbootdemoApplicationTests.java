package com.example.springbootdemo;

import com.example.springbootdemo.service.Receiver;
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

    @Autowired
    Receiver receiver;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mqTest() {
        for (int i = 0; i < 1000; i++) {
            sender.send();
            receiver.process("hello");
        }
    }

    @Test
    public void mqTest2() {
        for (int i = 0; i < 100000; i++) {
            final int a = i;
            com.njq.nongfadai.service.funds.RechargeThreadPool.executeTask(new Runnable() {
                @Override
                public void run() {
                    incre(a);
                    System.out.print(a);
                }
            });
        }
    }

    private void incre(int i) {
        i = i + 1;
    }
}
