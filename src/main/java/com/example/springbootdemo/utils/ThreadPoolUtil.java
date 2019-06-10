package com.example.springbootdemo.utils;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author qin
 * @description 线程池工具类
 * @date 2019/6/6
 */
public class ThreadPoolUtil {
    private static Logger logger = LoggerFactory.getLogger(ThreadPoolUtil.class);
    private static final long WAIT_TIME = 3L;
    private static final ExecutorService SEND_TEST_EXECUTOR;
    private static CompletionService<String> completionService;
    private static final ListeningExecutorService CALLBACK_EXECUTOR;

    public ThreadPoolUtil() {
    }

    public static void executeTask(Runnable runnable) {
        SEND_TEST_EXECUTOR.execute(runnable);
    }

    public static <T> Future<T> submitTasks(Callable<T> callable) {
        return SEND_TEST_EXECUTOR.submit(callable);
    }

    public static <T> Future<String> submitTasksByCompletion(Callable<String> callable) {
        return completionService.submit(callable);
    }

    public static <T> ListenableFuture<T> submitTask(Callable<T> callable) {
        return CALLBACK_EXECUTOR.submit(callable);
    }

    public static void shutdown() {
        SEND_TEST_EXECUTOR.shutdown();

        try {
            if (!SEND_TEST_EXECUTOR.awaitTermination(3L, TimeUnit.SECONDS)) {
                shutdownNow();
            }
        } catch (InterruptedException var1) {
            logger.error("InterruptedException====>:", var1);
            shutdownNow();
        }

    }

    public static void shutdownNow() {
        SEND_TEST_EXECUTOR.shutdownNow();
    }

    static {
        SEND_TEST_EXECUTOR = new ThreadPoolExecutor(0, 2147483647, 30L, TimeUnit.SECONDS, new SynchronousQueue(), (new ThreadFactoryBuilder()).setNameFormat("-send-report-to-internetCentre-%d").setUncaughtExceptionHandler(new ThreadPoolUtil.RepeatableExecutionHandler()).build());
        completionService = new ExecutorCompletionService(SEND_TEST_EXECUTOR);
        CALLBACK_EXECUTOR = MoreExecutors.listeningDecorator(SEND_TEST_EXECUTOR);
    }

    private static class RepeatableExecutionHandler implements Thread.UncaughtExceptionHandler {
        private RepeatableExecutionHandler() {
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            ThreadPoolUtil.logger.info("线程id: {} ,name: {} 意外终止！,终止原因: {}", new Object[]{t.getName(), t.getName(), Throwables.getStackTraceAsString(e)});
        }
    }
}
