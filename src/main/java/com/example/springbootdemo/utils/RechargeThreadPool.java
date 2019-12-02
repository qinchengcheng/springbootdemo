package com.njq.nongfadai.service.funds;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.*;

/**
 * @Description: 转账充值线程池
 * @Param:
 * @return:
 * @Author: qin
 * @Date: 2019/7/30
 */
public class RechargeThreadPool {
    private static Logger logger = LoggerFactory.getLogger(RechargeThreadPool.class);

    private static final long WAIT_TIME = 3;

    private static final ExecutorService RECHARGE_EXECUTOR = new ThreadPoolExecutor(15, 15, 30L,
            TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactoryBuilder()
            .setNameFormat("-recharge-zzcz-%d").setUncaughtExceptionHandler(new RepeatableExecutionHandler())
            .build());

    private static CompletionService<String> completionService=new ExecutorCompletionService<>(RECHARGE_EXECUTOR);

    private static class RepeatableExecutionHandler implements UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            logger.info("线程id: {} ,name: {} 意外终止！,终止原因: {}", t.getName(), t.getName(),Throwables.getStackTraceAsString(e));
        }
    }

    private static final ListeningExecutorService CALLBACK_EXECUTOR = MoreExecutors
            .listeningDecorator(RECHARGE_EXECUTOR);

    public static void executeTask(Runnable runnable) {
        RECHARGE_EXECUTOR.execute(runnable);
    }

    public static <T> Future<T> submitTasks(Callable<T> callable) {
        return RECHARGE_EXECUTOR.submit(callable);
    }

    public static <T> Future<String> submitTasksByCompletion(Callable<String> callable) {
        return completionService.submit(callable);
    }

    public static <T> ListenableFuture<T> submitTask(Callable<T> callable) {
        return CALLBACK_EXECUTOR.submit(callable);
    }

    public static void shutdown(){
        RECHARGE_EXECUTOR.shutdown();
        try {
            if (!RECHARGE_EXECUTOR.awaitTermination(WAIT_TIME, TimeUnit.SECONDS)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                shutdownNow();
            }
        } catch (InterruptedException e) {
            logger.error("InterruptedException====>:", e);
            shutdownNow();
        }
    }
    public static void shutdownNow(){
        RECHARGE_EXECUTOR.shutdownNow();
    }

    public static void main(String[] args) {
            for (int i = 0; i < 100000000; i++) {
                final int a = i;
                com.njq.nongfadai.service.funds.RechargeThreadPool.executeTask(new Runnable() {
                    @Override
                    public void run() {
                        incre(a);
                        System.out.println(a);
                    }
                });
            }
        try {
            Thread.sleep(1000000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static void incre(int i) {
        i = i + 1;
    }
}
