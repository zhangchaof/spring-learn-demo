package com.example.spring.learn.demo.designpattern.proxy.jdk.testclass;

public class Cat implements Animal {
    @Override
    public String eat(String food) {
        System.out.println("The cat is eating");
        return "cat";
    }
}
