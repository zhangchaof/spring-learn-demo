package com.example.spring.learn.demo.designpattern.factory.abstracts;


import com.example.spring.learn.demo.designpattern.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 18:56
 */
public class FruitFactoryTest {
    /**
     * 一个工厂内多条流水生产不同部件，要啥有就给没有就没有
     *
     * @param args
     */
    public static void main(String[] args) {
        FruitFactory fruitFactory = new FruitFactory();
        Fruit apple = fruitFactory.getApple();
        System.out.println("apple = " + apple);
    }
}
