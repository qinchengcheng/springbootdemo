package com.example.springbootdemo;

import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.utils.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    private int store = 50;

    @RequestMapping("/listUser")
    public String listUser(Model model) {
        setVal();
//        if (store > 0) {
//            store = store - 1;
//            System.out.println("剩余" + store);
//        } else {
//            System.out.println("库存不足!");
//        }
        List<User> users = userMapper.selectAll();
        model.addAttribute("users", users);
        return "listUser";
    }

    @RequestMapping("/User")
    public String User(Model model) {
        User user = userMapper.findUserById(2);
        model.addAttribute("name", user.getUser_name());
        model.addAttribute("id", user.getUser_id());
        return "User";
    }

    private void setVal() {
        String key = "test";
        boolean lock = redisUtil.lock(key);
        if (lock) {
            if (store > 0) {
                store = store - 1;
                System.out.println("剩余" + store);
            } else {
                System.out.println("库存不足!");
            }
            // 执行逻辑操作
            redisUtil.delete(key);
        } else {
            // 设置失败次数计数器, 当到达5次时, 返回失败
            int failCount = 1;
            while (failCount <= 5) {
                // 等待100ms重试
                try {
                    Thread.sleep(100l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (redisUtil.lock(key)) {
                    // 执行逻辑操作
                    redisUtil.delete(key);
                } else {
                    failCount++;
                }
            }
            throw new RuntimeException("现在创建的人太多了, 请稍等再试");
        }

    }
}
