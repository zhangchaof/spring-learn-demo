package com.example.spring.learn.demo.beancopy.orika.differ;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName MainTest
 * @Description:
 * 使用场景（对象A与对象B中属性不一致时，如果对象中属性太多，普通逻辑处理代码太多）
 *
 * @Author clark
 * @Date 2021/6/30 17:39
 **/
public class MainTest {
    /**
     * 1.A对象复制到B对象，对象中属性不一样时
     */
    public static void first() {
        //1.普通逻辑处理
        User A = new User().setId("123").setName("张三").setAge(20);
        UserVo B = new UserVo().setId(A.getId()).setUserName(A.getName()).setAgeOne(A.getAge());
        System.out.println("普通方式将A对象处理B对象中：" + B);

        //使用orika复制工具将A复制到B对象中
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserVo.class)
                .field("name", "userName")
                .field("age", "ageOne")
                .byDefault().register();
        UserVo userVo = mapperFactory.getMapperFacade().map(A, UserVo.class);
        System.out.println("orika复制对象:" + userVo);
    }

    /**
     * 2.A集合对象复制到B集合对象，对象中属性不一样时
     */
    public static void second() {
        //A对象
        List<User> A = Arrays.asList(new User().setId("123").setName("张三").setAge(20));
        //B对象
        List<UserVo> B = new ArrayList<>();
        //将A集合数据复制到B集合中
        A.forEach(x -> {
            B.add(new UserVo().setId(x.getId()).setUserName(x.getName()).setAgeOne(x.getAge()));
        });
        System.out.println("将A集合中数据set到B集合中数据打印" + B);

        //使用orika复制工具将A集合复制到B集合中
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserVo.class)
                .field("name", "userName")
                .field("age", "ageOne")
                .byDefault().register();
        List<UserVo> userVos = mapperFactory.getMapperFacade().mapAsList(A, UserVo.class);
        System.out.println("orika复制对象:" + userVos);
    }

    /**
     * MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
     * mapperFactory.classMap(User.class, UserVo.class)
     * .field("name", "userName")
     * .field("age", "ageOne")
     * .byDefault().register();
     * //集合复制--使用mapAsList
     * List<UserVo> userVos = mapperFactory.getMapperFacade().mapAsList(A, UserVo.class);
     * //对象复制--使用map
     * UserVo userVos = mapperFactory.getMapperFacade().map(A, UserVo.class);
     */
}
