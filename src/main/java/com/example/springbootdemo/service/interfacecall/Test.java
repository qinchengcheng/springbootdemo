package com.example.springbootdemo.service.interfacecall;

public class Test extends EventCallback implements IEventCallback {
    @Override
    public void onReadMsg() {

    }
    @Override
    public void onClose(){
        super.onClose();
    }
}
