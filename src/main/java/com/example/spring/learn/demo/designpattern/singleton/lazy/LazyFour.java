package com.example.spring.learn.demo.designpattern.singleton.lazy;

/**
 * @author clark
 * @Description:
 * @date 2020/6/5 10:15
 */
public class LazyFour {
    private static LazyFour singleton;

    private LazyFour() {
    }


    public static LazyFour getInstance() {
        return SingletonHolder.INNER_INSTANCE;
    }
    /**
     *
     * 静态内部类单例模式
     *
     * 第一次加载Singleton类时并不会初始化sInstance，只有第一次调用getInstance方法时虚拟机加载SingletonHolder并初始化sInstance，
     * 这样不仅能确保线程安全也能保证Singleton类的唯一性，所以推荐使用静态内部类单例模式。
     *
     */
    private static class SingletonHolder {
        private static final LazyFour INNER_INSTANCE = new LazyFour();
    }
}
