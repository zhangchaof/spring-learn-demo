package com.example.spring.learn.demo.designpattern.proxy.jdk.testclass;

public class Dog implements Animal {
    @Override
    public String eat(String food) {
        System.out.println("The dog is eating");
        return "dog";
    }
}
