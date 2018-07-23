package com.example.springbootdemo;

import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/listUser")
    public String listUser(Model model){
        List<User> users=userMapper.selectAll();
        model.addAttribute("users",users);
        return "listUser";
    }
    @RequestMapping("/User")
    public String User(Model model){
        User user=userMapper.findUserById(2);
        model.addAttribute("name",user.getUser_name());
        model.addAttribute("id",user.getUser_id());
        return "User";
    }
}
