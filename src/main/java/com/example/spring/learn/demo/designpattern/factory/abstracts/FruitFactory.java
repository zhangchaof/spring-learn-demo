package com.example.spring.learn.demo.designpattern.factory.abstracts;


import com.example.spring.learn.demo.designpattern.factory.Apple;
import com.example.spring.learn.demo.designpattern.factory.Banana;
import com.example.spring.learn.demo.designpattern.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 18:47
 */
public class FruitFactory extends AbstractFactory {

    @Override
    public Fruit getBanana() {
        return new Banana();
    }

    @Override
    public Fruit getApple() {
        return new Apple();
    }
}
