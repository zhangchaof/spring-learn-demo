package com.example.spring.learn.demo.design.factory.simple;


import com.example.spring.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:29
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        SimpleFactory simpleFactory = new SimpleFactory();
        //小作坊，传入要的值，可能传错
        Fruit apple = simpleFactory.getFruit("apple");
        System.out.println("apple = " + apple);
    }
}
