package com.example.springbootdemo.pojo;

import static javafx.scene.input.KeyCode.L;

/**
 * @author qin
 * @description
 * @date 2019/6/15
 */
public class Man implements IPerson {
    @Override
    public void say() {
        System.out.println("man say");
    }
}
