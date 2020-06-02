package com.example.spring.learn.demo.design.factory.details;


import com.example.spring.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:43
 */
public class FactoryTest {
    static String APPLE = "apple";
    public static void main(String[] args) {
        //不同工厂，要不同部件要指定特定工厂
        Factory factory = new BananaFactory();
        Fruit apple = factory.getFruit(APPLE);
        System.out.println("apple = " + apple);
    }
}
