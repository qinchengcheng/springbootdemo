package com.example.springbootdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qin
 * @description 分批执行任务
 * @date 2019/12/2
 */
public class LatchTask {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private static final int nThreads = Runtime.getRuntime().availableProcessors();
    private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool(nThreads + 1);

    /**
     * 1. 使用CountDownLatch
     */
    public void testCountDownLatch() {
        //模拟查询到数据库中待处理数据
        List batchList = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            batchList.add(new java.lang.Object());
        }
        if (CollectionUtils.isEmpty(batchList)) {
            return;
        }
        log.info("开始本次批量处理，数据个数：{}，时间：{}", batchList.size(), LocalDateTime.now());
        System.out.println("当前线程休眠完成,数据个数" + batchList.size() + "时间：" + LocalDateTime.now());
        final CountDownLatch countDownLatch = new CountDownLatch(batchList.size());
        int h = 0;
        batchList.forEach(Object -> FORK_JOIN_POOL.execute(() -> {
            try {
//                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                log.info("当前线程休眠完成");
                countDownLatch.countDown();
            } catch (Exception e) {
                log.error("异常", e);
            }
        }));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("完成本次批量处理，数据个数：{}，时间：{}", batchList.size(), LocalDateTime.now());
    }

    /**
     * 2. 使用CyclicBarrier
     */
    public void testCyclicBarrier() {
        //模拟查询到数据库中待处理数据
        List<Object> batchList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            batchList.add(new Object());
        }
        if (CollectionUtils.isEmpty(batchList)) {
            return;
        }
        log.info("开始本次批量处理，数据个数：{}，时间：{}", batchList.size(), LocalDateTime.now());
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(batchList.size(), () -> {
            log.info("完成本次批量处理，数据个数：{}，时间：{}", batchList.size(), LocalDateTime.now());
            testCyclicBarrier();
        });
        batchList.forEach(Object -> FORK_JOIN_POOL.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                log.info("当前线程休眠完成");
                cyclicBarrier.await();
            } catch (Throwable e) {
                log.error("异常", e);
            }
        }));
    }

    /**
     * 3. 使用CompletionService
     */
    public void testCompletionService() {

        for (int j = 0; j < 3; j++) {
            List<Object> batchList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                batchList.add(new Object());
            }
            if (CollectionUtils.isEmpty(batchList)) {
                return;
            }
            log.info("开始本次批量处理，数据个数：{}，时间：{}", batchList.size(), LocalDateTime.now());
            CompletionService completionService = new ExecutorCompletionService(FORK_JOIN_POOL);
            batchList.forEach(Object -> {
                completionService.submit(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                        log.info("当前线程休眠完成");
                    } catch (Throwable e) {
                        log.error("异常", e);
                    }
                    return null;
                });
            });
            batchList.forEach(imgRecord -> {
                try {
                    completionService.take().get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            log.info("完成本次批量处理，数据个数：{}，时间：{}", batchList.size(), LocalDateTime.now());
        }
    }


    /**
     * 4. 使用CompletableFuture
     */
    public void testCompletableFuture() {
        for (int j = 0; j < 3; j++) {
            List<Object> batchList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                batchList.add(new Object());
            }
            if (CollectionUtils.isEmpty(batchList)) {
                return;
            }
            log.info("开始本次批量处理，数据个数：{}，时间：{}", batchList.size(), LocalDateTime.now());
            ArrayList<CompletableFuture<?>> futureList = new ArrayList<>();
            batchList.forEach(Object -> {
                final CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                        log.info("当前线程休眠完成");
                    } catch (Throwable e) {
                        log.error("异常", e);
                    }
                }, FORK_JOIN_POOL);
                futureList.add(future);
            });
            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
            log.info("完成本次批量处理，数据个数：{}，时间：{}", batchList.size(), LocalDateTime.now());
        }
    }

    public static void main(String[] args) {
        LatchTask test = new LatchTask();
//        test.testCountDownLatch();
        test.testCyclicBarrier();
        ReentrantLock reentrantLock=new ReentrantLock();
    }

    private void incre(int i) {
        i = i + 1;
    }
}
