package com.example.spring.learn.demo.designpattern.prototype;

import java.util.Hashtable;

/**
 * @author clark
 * @Description:
 * @date 2020/6/8 11:31
 */
public class ShapeCache {

    private static Hashtable<String, AbstractShape> abstractShapeMap = new Hashtable<>();

    public static AbstractShape getAbstractShape(String AbstractShapeId) {
        AbstractShape cachedAbstractShape = abstractShapeMap.get(AbstractShapeId);
        return (AbstractShape) cachedAbstractShape.clone();
    }


    /**
     *
     *  对每种形状都运行数据库查询，并创建该形状
     *  AbstractShapeMap.put(AbstractShapeKey, AbstractShape);
     *  例如，我们要添加三种形状
     *
     */
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        abstractShapeMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        abstractShapeMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        abstractShapeMap.put(rectangle.getId(), rectangle);
    }
}
