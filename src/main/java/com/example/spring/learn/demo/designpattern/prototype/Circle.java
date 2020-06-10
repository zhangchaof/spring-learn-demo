package com.example.spring.learn.demo.designpattern.prototype;

/**
 * @author clark
 * @Description:
 * @date 2020/6/8 11:30
 */
public class Circle extends AbstractShape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
