package com.example.spring.learn.demo.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author clark
 * @Description:
 *
 * 在Java里面有几种可以用于控制线程状态的方法，如CountDownLatch计数器、CyclicBarrier循环栅栏、Sempahore信号量。
 * 1.CountDownLatch 可以实现计数等待，主要用于某个线程等待其他几个线程
 * 2.CyclicBarrier 实现循环栅栏，主要用于多个线程同时等待其他线程
 * 3.Semaphore 信号量，主要强调只有某些个数量的线程能拿到资源执行
 *
 * @date 2020/6/4 19:36
 */
public class ThreadCountPattern {
    public static void main(String[] args) {
    }

    /**
     * CountDownLatch可以实现多线程之间的计数器，并实现阻塞功能。比如某个任务依赖于其他的两个任务，只有那两个任务执行结束后，它才能执行。
     * <p>
     * 子线程共同起到阻塞父线程的作用，相互之间没影响,可以同时执行，也可以先后执行
     */
    public static void countDownLatchTest() {
        // 创建计数器，初始化为2
        final CountDownLatch latch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();// 减一
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                Thread.sleep(3000);
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            System.out.println("等待2个子线程执行完毕...");
            // 阻塞
            latch.await();
            System.out.println("2个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 可以同时对多个线程状态进行管理,多个线程同时等待执行
     */
    public static void cyclicBarrierTest() {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }
        try {
            Thread.sleep(25000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("CyclicBarrier重用");

        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }
    }

    /**
     * 循环的意思就是当计数减到0时，还可以继续使用
     */
    public static void cyclicBarrierTest2() {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程" + Thread.currentThread().getName() + "写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }

    /**
     * 这个东西有点像连接池的感觉，某一时间只有几个线程能拿到资源，执行操作。
     */
    public static void semaphoreTest() {
        int N = 8;            //工人数
        Semaphore semaphore = new Semaphore(5); //机器数目
        for (int i = 0; i < N; i++) {
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("工人" + this.num + "占用一个机器在生产...");
                Thread.sleep(2000);
                System.out.println("工人" + this.num + "释放出机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
