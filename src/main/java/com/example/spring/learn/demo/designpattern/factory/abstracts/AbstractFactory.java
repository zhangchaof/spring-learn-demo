package com.example.spring.learn.demo.designpattern.factory.abstracts;


import com.example.spring.learn.demo.designpattern.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:45
 */
public abstract class AbstractFactory {
    public abstract Fruit getBanana();

    public abstract Fruit getApple();
}
