package com.code.utils;

import android.os.Handler;

import androidx.annotation.NonNull;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolUtils {
    private static BlockingQueue workQueue = new ArrayBlockingQueue(10);
    private static Handler mDelayHandler = new Handler();
    private static ThreadPoolExecutor threadPoolExecutor;

    public ThreadPoolUtils() {
    }
    private static ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();
        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "myThreadPool thread:" + integer.getAndIncrement());
        }
    };

    static {
        int CORE_POOL_SIZE = 50;
        int MAX_POOL_SIZE = 500;
        int KEEP_ALIVE_TIME = 10000;
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue, threadFactory);
    }
    public static void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public static void delayExecute(final Runnable runnable, long delay){

        mDelayHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                execute(runnable);
            }
        }, delay);

    }

    public static void execute(FutureTask futureTask) {
        threadPoolExecutor.execute(futureTask);
    }

    public static void cancel(FutureTask futureTask) {
        futureTask.cancel(true);
    }
}
