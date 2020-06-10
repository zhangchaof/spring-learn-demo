package com.example.spring.learn.demo.designpattern.prototype;

/**
 * @author clark
 * @Description:
 * @date 2020/6/8 11:29
 */
public class Square extends AbstractShape {

    public Square(){
        type = "Square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
