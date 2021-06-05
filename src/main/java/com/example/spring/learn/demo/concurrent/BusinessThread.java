package com.example.spring.learn.demo.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;

/**
 * @ClassName BusinessThread
 * @Description: TODO
 * @Author clark
 * @Date 2021/5/22 18:28
 **/
public class BusinessThread implements Callable<String> {

    private String name;

    BusinessThread(String name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public String call() {
        // 模拟处理业务逻辑
        System.out.println(name + "正在游戏中--" + Thread.currentThread().getName());

        Thread.sleep(1000);

        return name;
    }
}
