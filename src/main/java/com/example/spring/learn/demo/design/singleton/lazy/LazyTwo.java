package com.example.spring.learn.demo.design.singleton.lazy;

import java.util.Objects;

/**
 * @author clark
 * @Description:
 * @date 2020/6/4 12:42
 */
public class LazyTwo {
    private LazyTwo() {
    }

    private static LazyTwo instance = null;

    /**
     *
     * 懒汉模式（线程安全）
     * 这种写法能够在多线程中很好的工作，但是每次调用getInstance方法时都需要进行同步，
     * 造成不必要的同步开销，而且大部分时候我们是用不到同步的，所以不建议用这种模式。
     *
     * @return
     */
    public synchronized static LazyTwo getInstance() {
        if (instance == null) {
            instance = new LazyTwo();
        }
        return instance;
    }
}
