package com.example.springbootdemo.service;

import com.example.springbootdemo.utils.ThreadPoolUtil;

/**
 * @author qin
 * @description
 * @date 2019/6/14
 */
public class SynchronizedTest {
    Integer i=0;
    public synchronized void test(){
        i=i++;
        System.out.println(i);
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new SynchronizedTest().test();
        }
    }
}
