package com.example.spring.learn.demo.designpattern.factory.details;


import com.example.spring.learn.demo.designpattern.factory.Banana;
import com.example.spring.learn.demo.designpattern.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:40
 */
public class BananaFactory implements Factory {
    @Override
    public Fruit getFruit(String name) {
        return new Banana();
    }
}
