package com.example.spring.learn.demo.design.singleton.issue.protect;

import java.io.Serializable;

/**
 * @author clark
 * @Description:
 * @date 2020/6/5 10:56
 */
public class ProtectSingleton implements Serializable, Cloneable {
    private static final long serialVersionUID = 6125990676610180062L;
    private static ProtectSingleton singleton;
    /**
     * 默认是第一次创建,防反射
     */
    private static boolean isFirstCreate = true;

    private ProtectSingleton() {
        if (isFirstCreate) {
            synchronized (ProtectSingleton.class) {
                if (isFirstCreate) {
                    isFirstCreate = false;
                }
            }
        } else {
            throw new RuntimeException("已然被实例化一次，不能在实例化");
        }
    }

    public static ProtectSingleton getInstance() {
        if (singleton == null) {
            synchronized (ProtectSingleton.class) {
                if (singleton == null) {
                    singleton = new ProtectSingleton();
                }
            }
        }
        return singleton;
    }

    /**
     * 防clone
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected ProtectSingleton clone() throws CloneNotSupportedException {
        return singleton;
    }

    /**
     * 防序列化
     * @return
     */
    private Object readResolve() {
        return singleton;
    }
}
