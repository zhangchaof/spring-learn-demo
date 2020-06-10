package com.example.spring.learn.demo.designpattern.singleton.hungry;

/**
 * @author clark
 * @Description:
 * @date 2020/6/5 10:05
 */
public class HungryStatic {
    private final static HungryStatic SINGLETON;

    /**
     * 饿汉模式：线程安全
     * 这种实现方式优缺点和方式一是一样的，也是利用了类加载，唯一不同的就是将实例化的过程放在了静态代码块中。
     */

    static {
        SINGLETON = new HungryStatic();
    }

    private HungryStatic() {
    }

    public static HungryStatic getInstance() {
        return SINGLETON;
    }

}
