package com.example.spring.learn.demo.design.factory.abstracts;


import com.example.spring.learn.demo.design.factory.Apple;
import com.example.spring.learn.demo.design.factory.Banana;
import com.example.spring.learn.demo.design.factory.Fruit;

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
