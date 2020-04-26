package com.example.springbootdemo.service.interfacecall;

public interface IEventCallback {
    void onReadMsg();
    void onSendMsg();
    void onError();
    void onClose();
}
