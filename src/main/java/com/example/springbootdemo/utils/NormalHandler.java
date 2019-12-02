package com.example.springbootdemo.utils;

import com.example.springbootdemo.pojo.IPerson;
import com.example.springbootdemo.pojo.Man;
import org.eclipse.jdt.internal.compiler.lookup.AnnotationHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author qin
 * @description
 * @date 2019/6/15
 */
public class NormalHandler implements InvocationHandler {
    private Object target;
    public NormalHandler(Object target){
        this.target=target;
    }
    @Override
    public Object invoke(Object proxy, Method method,Object[] args) throws Throwable{
        System.out.println(System.currentTimeMillis());
        method.invoke(target,args);
        return null;
    }
}
