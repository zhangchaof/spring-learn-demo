package com.example.spring.learn.demo.designpattern.prototype;

/**
 * @author clark
 * @Description:
 * @date 2020/6/8 11:25
 */
public class Rectangle extends AbstractShape {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
