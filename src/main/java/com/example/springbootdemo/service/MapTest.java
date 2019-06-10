package com.example.springbootdemo.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author qin
 * @description
 * @date 2019/6/10
 */
public class MapTest {
    public void Test() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String key = (String) entry.getKey();
            String value=(String) entry.getValue();
        }
        Iterator<Map.Entry<String,String>> iterator1=map.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<String,String> entry=iterator1.next();
            String key=entry.getKey();
            String value=entry.getValue();
        }
    }

    public static void main(String[] args) {

    }
}
