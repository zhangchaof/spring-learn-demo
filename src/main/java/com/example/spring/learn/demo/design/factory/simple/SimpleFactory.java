package com.example.spring.learn.demo.design.factory.simple;


import com.example.spring.learn.demo.design.factory.Apple;
import com.example.spring.learn.demo.design.factory.Banana;
import com.example.spring.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:19
 */
public class SimpleFactory {
    static String APPLE = "apple";
    static String BANANA = "banana";

    public Fruit getFruit(String name) {
        if (APPLE.equals(name)) {
            return new Apple();
        } else if (BANANA.equals(name)) {
            return new Banana();
        } else {
            System.out.println("无法生产");
            return null;
        }
    }

}
