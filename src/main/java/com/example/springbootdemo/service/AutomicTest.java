package com.example.springbootdemo.service;

import com.example.springbootdemo.utils.ThreadPoolUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qin
 * @description automic
 * @date 2019/6/6
 */
public class AutomicTest {
    public void Test() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        final int i = 0;
        ThreadPoolUtil.executeTask(new Runnable() {
            @Override
            public void run() {
                incre(i);
                System.out.print(i);
            }
        });
        ThreadPoolUtil.executeTask(new Runnable() {
            @Override
            public void run() {
                incre(i);
                System.out.print(i);
            }
        });
        ThreadPoolUtil.executeTask(new Runnable() {
            @Override
            public void run() {
                incre(i);
                System.out.print(i);
            }
        });
        ThreadPoolUtil.executeTask(new Runnable() {
            @Override
            public void run() {
                incre(i);
                System.out.print(i);
            }
        });
        ThreadPoolUtil.executeTask(new Runnable() {
            @Override
            public void run() {
                incre(i);
                System.out.print(i);
            }
        });
    }

    private void doSomeThing(int i, AtomicInteger atomicInteger) {

        ThreadPoolUtil.executeTask(new Runnable() {
            @Override
            public void run() {
                System.out.print("线程" + atomicInteger.addAndGet(1) + ":" + i);
            }
        });
    }


    private void doSomeThing2(int i) {

        ThreadPoolUtil.executeTask(new Runnable() {
            @Override
            public void run() {
                incre(i);
                System.out.print(i);
            }
        });
    }

    private void incre(int i) {
        i = i + 1;
    }

    public static void main(String[] args) {
        new AutomicTest().Test();
    }
}
