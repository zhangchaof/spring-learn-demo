package com.example.spring.learn.demo.designpattern.prototype;

/**
 * @author clark
 * @Description:
 * @date 2020/6/8 11:33
 */
public class PrototypePatternDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        AbstractShape clonedShape = (AbstractShape) ShapeCache.getAbstractShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        AbstractShape clonedShape2 = (AbstractShape) ShapeCache.getAbstractShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        AbstractShape clonedShape3 = (AbstractShape) ShapeCache.getAbstractShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
