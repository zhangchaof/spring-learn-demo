package com.example.spring.learn.demo.config.aspect.log;

import java.util.UUID;

/**
 * @author clark
 */
public class ThreadUtil {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getThreadId() {
        String threadId = threadLocal.get();
        if (null == threadId) {
            threadId = UUID.randomUUID().toString();
            threadLocal.set(threadId);
        }
        return threadId;
    }

}
