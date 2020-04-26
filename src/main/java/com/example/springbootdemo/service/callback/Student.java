package com.example.springbootdemo.service.callback;
/**
* @Description:
* @Param:
* @return:
* @Author: qin
* @Date: 2020/4/26
*/
//接着定义一个学生接口，学生当然是解决问题，但是接收一个Callback参数，这样学生就知道解决完毕问题向谁报告：
public interface Student {
    public void resolveQuestion(CallBack callBack);
}
