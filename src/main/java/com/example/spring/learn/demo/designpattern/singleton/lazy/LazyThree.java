package com.example.spring.learn.demo.designpattern.singleton.lazy;

/**
 * @author clark
 * @Description:
 * @date 2020/6/5 10:12
 */
public class LazyThree {
    private volatile static LazyThree singleton;

    private LazyThree() {
    }

    /**
     * 双重检查模式 （DCL）
     * 这种写法在getSingleton方法中对singleton进行了两次判空，第一次是为了不必要的同步，第二次是在singleton等于null的情况下才创建实例。
     * 在这里用到了volatile关键字，不了解volatile关键字的可以查看Java多线程（三）volatile域这篇文章，在这篇文章提到了双重检查模式是正确使用volatile关键字的场景之一。
     * 在这里使用volatile会或多或少的影响性能，但考虑到程序的正确性，牺牲这点性能还是值得的。
     * DCL优点是资源利用率高，第一次执行getInstance时单例对象才被实例化，效率高。缺点是第一次加载时反应稍慢一些，在高并发环境下也有一定的缺陷，虽然发生的概率很小。
     * DCL虽然在一定程度解决了资源的消耗和多余的同步，线程安全等问题，但是他还是在某些情况会出现失效的问题，也就是DCL失效，在《java并发编程实践》一书建议用静态内部类单例模式来替代DCL。
     *
     * @return
     */
    public static LazyThree getInstance() {
        if (singleton == null) {
            synchronized (LazyThree.class) {
                singleton = new LazyThree();
            }
        }
        return singleton;
    }
}
