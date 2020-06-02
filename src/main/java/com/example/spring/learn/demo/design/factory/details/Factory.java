package com.example.spring.learn.demo.design.factory.details;


import com.example.spring.learn.demo.design.factory.Fruit;

/**
 * @author clark
 * @Description:
 * @date 2020/5/29 15:39
 */
public interface Factory {
    Fruit getFruit(String name);
}
