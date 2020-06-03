package com.example.spring.learn.demo.design.singleton.hungry;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author clark
 * @Description:
 * @date 2020/6/2 15:39
 */
@Slf4j
public class HungryTest {
    public static void main(String[] args) {
        System.out.println("主线程开begin ");
        int count = 10;
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                Hungry hungry = Hungry.getInstance();
                System.out.println(" time:" + System.currentTimeMillis() + ",hungry:" + hungry);
                try {
                    Thread.sleep(30000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" 主线程结束" );
    }
}
