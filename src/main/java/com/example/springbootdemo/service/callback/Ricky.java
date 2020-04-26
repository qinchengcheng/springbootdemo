package com.example.springbootdemo.service.callback;

/**
 * @author qin
 * @description
 * @date 2020/4/26
 */
public class Ricky implements Student {
    @Override
    public void resolveQuestion(CallBack callBack) {
        // 模拟解决问题
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        callBack.tellAnswer(3);
    }
}
