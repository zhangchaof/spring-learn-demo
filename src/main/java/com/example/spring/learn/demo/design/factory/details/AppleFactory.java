package com.example.spring.learn.demo.design.factory.details;


import com.example.spring.learn.demo.design.factory.Apple;
import com.example.spring.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:40
 */
public class AppleFactory implements Factory{

    @Override
    public Fruit getFruit(String name) {
        return new Apple();
    }
}
