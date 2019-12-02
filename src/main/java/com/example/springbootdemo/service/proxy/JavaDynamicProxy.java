package com.example.springbootdemo.service.proxy;

import com.example.springbootdemo.service.Developer;
import com.example.springbootdemo.service.JavaDeveloper;

import java.lang.reflect.Proxy;

/**
 * @author qin
 * @description
 * @date 2019/6/15
 */
public class JavaDynamicProxy {
    public static void main(String[] args) {
        JavaDeveloper zack = new JavaDeveloper("Zack");
        Developer developer = (Developer) Proxy.newProxyInstance(zack.getClass().getClassLoader(),
                zack.getClass().getInterfaces(), ((proxy, method, args1) -> {
                    if (method.getName().equals("code")) {
                        System.out.println("code");
                        return method.invoke(zack, args);
                    }
                    if (method.getName().equals("debug")) {
                        System.out.println("debug");
                        return null;
                    }
                    return null;
                })
        );
        developer.code();
        developer.debug();
    }
}
