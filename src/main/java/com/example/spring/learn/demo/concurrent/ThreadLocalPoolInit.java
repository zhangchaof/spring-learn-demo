package com.example.spring.learn.demo.concurrent;

import java.util.concurrent.*;

/**
 * @author clark
 * @Description:
 * @date 2020/6/4 20:15
 */
public class ThreadLocalPoolInit {
    public static ExecutorService pool = null;

    static {
        pool = new ThreadPoolExecutor(10, 2000000, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }


    //https://www.cnblogs.com/dennyzhangdd/p/7010972.html
}
