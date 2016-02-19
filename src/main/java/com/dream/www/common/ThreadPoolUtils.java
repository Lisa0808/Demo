package com.dream.www.common;

import lombok.Data;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: fengchuanbo
 * Date: 2015/11/27 16:54
 * Email: chuanbo.f@asou.com
 * 功能描述:
 */
@Data
public class ThreadPoolUtils {

    /**
     * 线程池原始大小
     */
    protected int corePoolSize = 30;
    /**
     * 线程池最大值
     */
    protected int maxPoolSize = 30;
    /**
     * 空闲线程存活时间
     */
    protected int keepAliveTime = 30;
    /**
     * 队列比率
     */
    protected int queueCapacity = 20000;
    /**
     * 线程等待最大时间
     */
    protected int threadWaitMaxTime = 20;
    /**
     * 线程池对象
     */
    private ThreadPoolExecutor threadPool;

    private synchronized void getThreadPoolInstance() {
        if (threadPool != null) {
            return;
        }
        threadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(queueCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy());

    }

    public void addProcess(Runnable runnable){
        try {
            if (threadPool == null) {
                getThreadPoolInstance();
            }
            threadPool.execute(runnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
