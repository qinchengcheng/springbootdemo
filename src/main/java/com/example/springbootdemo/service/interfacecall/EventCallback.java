package com.example.springbootdemo.service.interfacecall;

public abstract class EventCallback implements IEventCallback {
    @Override
    public abstract void onReadMsg();

    @Override
    public void onSendMsg() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onClose() {

    }
}
