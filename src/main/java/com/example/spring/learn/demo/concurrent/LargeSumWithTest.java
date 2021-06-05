package com.example.spring.learn.demo.concurrent;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName LargeSumWithCallables
 * @Description: 1、初版：虽然使用了多线程，但由于Future.get()方法是阻塞方法，同时又在for循环中获取，所以每次线程池内只有一个线程执行。相比不使用线程，只算是异步执行。
 * 需解决问题：多个任务同时执行
 * 2、更新版： 解决了线程池内只有一个任务执行的问题。但由于在获取结果时使用while，造成了CPU高速运转。
 * 需解决问题： 高CPU使用率
 * 3、最终版：使用JDK8新接口CompletableFuture，支持多个任务同时执行，支持源生API，支持异常处理，完美！！！
 * <p>
 * 原文链接：https://blog.csdn.net/qq_39815207/article/details/105682136
 * @Author clark
 * @Date 2021/5/22 18:26
 **/
public class LargeSumWithTest {
    /**
     * 自定义线程工厂,可以设置线程名前缀、是否守护线程、优先级等属性
     */
    private static ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("king-%d").build();

    /**
     * 在开发过程中最好自定义线程池 根据实际情况设置核心线程、最大线程、存活时间、工作队列、线程工厂、拒绝策略
     * 线程池执行流程
     * 核心线程--》工作队列--》最大线程--》拒绝策略
     */
    private static ExecutorService executor = new ThreadPoolExecutor(3, 5, 1,
            TimeUnit.MINUTES, new ArrayBlockingQueue<>(5), factory);

    private static List<String> names = Lists.newArrayList("鲁班", "虞姬", "蒙犽", "后裔");

    public static List<String> result = Lists.newArrayList();

    public static void main(String[] args) {

        // 初版
        System.out.println("======================初版=======================");
        firstVersion();

        // 更新版
        System.out.println("======================更新版=======================");
        updateVersion();

        // 最终版
        System.out.println("======================最终版=======================");
        finalVersion();
        // 关闭线程池
        executor.shutdown();
    }

    /**
     * 初版
     */
    private static void firstVersion() {
        names.forEach(n -> {
            Future<String> future = executor.submit(new BusinessThread(n));
            try {
                // future.get()方法是一个阻塞方法。等待计算完成才能处理结果
                addAndPrint(future.get());
            } catch (Exception e) {
                System.out.println(n + "处理失败");
            }
        });
    }

    /**
     * 更新版
     */
    @SneakyThrows
    private static void updateVersion() {

        List<Future<String>> futures = new ArrayList<>();
        // 将所有任务提交给线程池处理
        names.forEach(n -> futures.add(executor.submit(new BusinessThread(n))));

        while (futures.size() > 0) {
            Iterator<Future<String>> iterator = futures.iterator();
            while (iterator.hasNext()) {
                Future<String> future = iterator.next();
                // 当任务执行完成后取出
                if (future.isDone() && !future.isCancelled()) {
                    addAndPrint(future.get());
                    iterator.remove();
                } else {
                    // 避免CPU高速运转
                    Thread.sleep(10);
                }
            }
        }
    }

    /**
     * 最终版
     */
    private static void finalVersion() {

        // JDK8 CompletableFuture
        // 满足并发执行,顺序完成并按先后顺序获取的目标
        CompletableFuture[] cfs = names.stream().map(n ->
                // 申请一个异步任务,执行自定义方法,放入自定义线程池内
                CompletableFuture.supplyAsync(() -> handle(n), executor)
                        // 当任务完成时可对任务进行处理,支持任务执行中的异常处理
                        .whenComplete((r, e) -> addAndPrint(r))).toArray(CompletableFuture[]::new);
        // 任务开始执行
        CompletableFuture.allOf(cfs).join();
    }

    /**
     * 线程类的实现逻辑转为方法
     */
    @SneakyThrows
    private static String handle(String name) {
        // 模拟处理业务逻辑
        System.out.println(name + "正在游戏中--" + Thread.currentThread().getName());
        Thread.sleep(1000);
        return name;
    }

    /**
     * 添加并打印
     */
    private static void addAndPrint(String name) {
        result.add(name);
        System.out.println(name + "已经超神啦");

    }
}
