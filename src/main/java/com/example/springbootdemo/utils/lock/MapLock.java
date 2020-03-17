package com.example.springbootdemo.utils.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qin
 * @description
 * @date 2020/3/17
 */
public class MapLock {
    private final static Object val = new Object();
    /**
     * @Description: 锁缓存
     */
    public static final ConcurrentHashMap<Object, Object> lockCatch = new ConcurrentHashMap<>();

    public static void doWork(Object lock) throws InterruptedException {
        doWork(lock, 50);
    }

    public static void doWork(Object lock, long sleepTime) throws InterruptedException {
        Object isNull = lockCatch.putIfAbsent(lock, val);
        if (isNull != null) {
            System.out.println(Thread.currentThread().getId() + "等待锁" + sleepTime + "ms");
            Thread.sleep(sleepTime);
            MapLock.doWork(lock, sleepTime);
        } else {
            System.out.println(Thread.currentThread().getId() + "获取锁，开始执行");
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getId() + "执行完毕，耗时100毫秒，释放锁");
            lockCatch.remove(lock);
        }
    }

    public static void main(String[] args) {
        //需要注意的是，如果lock对象是自定义对象，那么要重写它的equals方法和hashCode方法；
        String lock = "testLock";
        new TestThread(lock).start();
        new TestThread(lock).start();
        new TestThread(lock).start();
        new TestThread(lock).start();
    }

    static class TestThread extends Thread {
        private Object lock;

        TestThread(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                MapLock.doWork(lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
