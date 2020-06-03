package com.example.spring.learn.demo.design.singleton.hungry;

/**
 * @author clark
 * @Description:
 * @date 2020/6/2 15:38
 */
public class Hungry {
    private Hungry() {

    }

    private static final Hungry hungry = new Hungry();

    public static Hungry getInstance() {
        return hungry;
    }
}
