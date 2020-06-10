package com.example.spring.learn.demo.designpattern.singleton.lazy;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author clark
 * @Description:
 * @date 2020/6/4 12:38
 */
@Slf4j
public class ThreadSafeTest {
    public static void main(String[] args) {
        testLazyOne(1000000);
        testLazyTwo(1000000);
    }

    /**
     * 测试线程安全和性能，线程安全性能下降
     *
     * @param count
     */
    public static void testLazyThree(int count) {
        log.info("主线程开begin ");
        long startTime = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(
                    () -> {
                        LazyFour lazyThree = LazyFour.getInstance();
                        log.info(" time:{},lazyThree:{}", System.currentTimeMillis(), lazyThree);
                        countDownLatch.countDown();
                    }).start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(" 主线程结束 costTime:{}", (System.currentTimeMillis() - startTime));
    }

    /**
     * 测试线程安全和性能，线程安全性能下降
     *
     * @param count
     */
    public static void testLazyTwo(int count) {
        log.info("主线程开begin ");
        long startTime = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(
                    () -> {
                        LazyTwo lazyTwo = LazyTwo.getInstance();
                        log.info(" time:{},lazyTwo:{}", System.currentTimeMillis(), lazyTwo);
                        countDownLatch.countDown();
                    }).start();

        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(" 主线程结束 costTime:{}", (System.currentTimeMillis() - startTime));
    }

    /**
     * 测试现成安全问题，不安全
     *
     * @param count
     */
    public static void testLazyOne(int count) {
        log.info("主线程开begin");
        long startTime = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                LazyOne lazyOne = LazyOne.getInstance();
                log.info(" time:{},lazyOne:{}", System.currentTimeMillis(), lazyOne);
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(" 主线程结束 costTime:{}", (System.currentTimeMillis() - startTime));
    }
}
