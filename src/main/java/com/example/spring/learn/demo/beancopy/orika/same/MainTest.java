package com.example.spring.learn.demo.beancopy.orika.same;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName MainTest
 * @Description:
 * 使用场景（如果对象中属性太多，普通逻辑处理代码太多）
 * @Author clark
 * @Date 2021/6/30 17:03
 **/
public class MainTest {
    public static void main(String[] args) {
        first();
        second();
    }
    /**
     * 1.A对象复制到B对象（对象复制）
     */
    public static void first() {
        //业务场景，将A对象复制B对象中
        //1.普通逻辑处理
        User A = new User().setId("123").setName("1231");
        UserVo B = new UserVo().setId(A.getId()).setName(A.getName());
        System.out.println("普通方式将A对象复制B对象中：" + B);

        //使用orika复制工具将A复制到B对象中
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        UserVo map = mapperFactory.getMapperFacade().map(A, UserVo.class);
        System.out.println("orika复制对象:" + map);
    }

    /**
     * 2.A集合复制到B集合（集合复制）
     */
    public static void second() {
        //1.普通逻辑处
        //A对象
        List<User> A = Arrays.asList(new User().setId("123").setName("张三"));
        //B对象
        List<UserVo> B = new ArrayList<>();
        //将A集合数据复制到B集合中
        A.forEach(x -> {
            B.add(new UserVo().setId(x.getId()).setName(x.getName()));
        });
        System.out.println("将A集合中数据set到B集合中数据打印");

        //使用orika复制工具将A集合复制到B集合中
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        List<UserVo> userVo = mapperFactory.getMapperFacade().mapAsList(A, UserVo.class);
        System.out.println("orika直接复制对象集合打印结果:" + userVo);
    }
    /**
     * MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
     * mapperFactory.getMapperFacade().map(操作对象)
     * mapperFactory.getMapperFacade().mapAsList(操作集合对象)
     */
}
