package com.dzx.util;


import java.util.concurrent.*;

/**
 * * ================================================
 * name:            ThreadPoolUtil
 * guide:
 * author：          kimonik
 * version：          1.0
 * date：            2019/3/21
 * description：线程池工具类
 * history：
 * ===================================================
 */
public class ThreadPoolUtil {
    //双重检查单例代码
//    private static volatile ThreadPoolUtil sExecutor = null;
    private ThreadPoolExecutor mPool = null;

    private static class SingleHolder {
        private static final ThreadPoolUtil INSTANCE = new ThreadPoolUtil();
    }

    private ThreadPoolUtil() {
        //核心线程设置为CPU的java虚拟机数量
        int coreSize = Runtime.getRuntime().availableProcessors();
        //最大线程数设置为核心线程的两倍
        int maxSize = coreSize * 5;
        //线程保持30s存活
        int aliveTime = 30;
        //队列设置为核心线程数,核心线程满载时允许核心线程数最大值数量的线程进入队列
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>(maxSize);

        mPool = new ThreadPoolExecutor(coreSize, maxSize, aliveTime, TimeUnit.SECONDS, taskQueue
                , new ExecutionHandler());
    }

    public static ThreadPoolUtil getInstance() {

        //dcl双重检查单例代码
//        if (sExecutor == null) {
//            synchronized (ThreadPoolUtil.class) {
//                if (sExecutor == null) {
//                    sExecutor = new ThreadPoolUtil();
//                }
//            }
//        }
//        return sExecutor;

        return SingleHolder.INSTANCE;
    }

    public void execute(Runnable runnable) {
        mPool.execute(runnable);
    }

    public int getCorePoolSize() {
        return mPool.getCorePoolSize();
    }

    public int getPoolSize() {
        return mPool.getPoolSize();
    }

    public int getActiveCount() {
        return mPool.getActiveCount();
    }

    public int getMaximumPoolSize() {
        return mPool.getMaximumPoolSize();
    }

    public long getTaskCount() {
        return mPool.getTaskCount();
    }

    public BlockingQueue<Runnable> getQueue() {
        return mPool.getQueue();
    }


    private static class ExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("------线程池已满,拒绝添加新线程-------");
        }
    }
}
