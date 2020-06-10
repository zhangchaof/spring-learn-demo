package com.example.spring.learn.demo.designpattern.singleton.issue.broke;

import java.io.Serializable;

/**
 * @author clark
 * @Description:
 * @date 2020/6/5 10:51
 */
public class BrokenSingleton implements Serializable, Cloneable {
    private static final long serialVersionUID = 6125990676610180062L;
    private static BrokenSingleton singleton;

    private BrokenSingleton() {
    }


    public static BrokenSingleton getInstance() {
        if (singleton == null) {
            synchronized (BrokenSingleton.class) {
                if (singleton == null) {
                    singleton = new BrokenSingleton();
                }
            }
        }
        return singleton;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
