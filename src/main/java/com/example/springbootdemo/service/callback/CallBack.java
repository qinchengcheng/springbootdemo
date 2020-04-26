package com.example.springbootdemo.service.callback;
/**
* @Description:
* @Param:
* @return:
* @Author: qin
* @Date: 2020/4/26
*/
// * 接下来看一下回调的代码示例，代码模拟的是这样一种场景：老师问学生问题，学生思考完毕回答老师。
// * 首先定义一个回调接口，只有一个方法tellAnswer(int answer)，即学生思考完毕告诉老师答案：
public interface CallBack {
    public void tellAnswer(int answerr);
}
