package com.example.spring.learn.demo.designpattern.singleton.hungry;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author clark
 * @Description:
 * @date 2020/6/2 15:39
 */
@Slf4j
public class ThreadSafeTest {
    public static void main(String[] args) {
        log.info("主线程开begin ");
        int count = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                Hungry hungry = Hungry.getInstance();
                log.info(" time:{},hungry:{}", System.currentTimeMillis(), hungry);
                countDownLatch.countDown();

            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(" 主线程结束");
    }

    public void testHungry() {
        Hungry singleton1 = Hungry.getInstance();
        Hungry singleton2 = Hungry.getInstance();

        System.out.println("两个singleton对象是否是同一个对象:" + (singleton1 == singleton2));
        System.out.println("singleton1的hashCode:" + singleton1.hashCode());
        System.out.println("singleton2的hashCode:" + singleton2.hashCode());
    }

    public void testHungryTwo() {
        HungryStatic singleton1 = HungryStatic.getInstance();
        HungryStatic singleton2 = HungryStatic.getInstance();

        System.out.println("两个singleton对象是否是同一个对象:" + (singleton1 == singleton2));
        System.out.println("singleton1的hashCode:" + singleton1.hashCode());
        System.out.println("singleton2的hashCode:" + singleton2.hashCode());
    }
}
