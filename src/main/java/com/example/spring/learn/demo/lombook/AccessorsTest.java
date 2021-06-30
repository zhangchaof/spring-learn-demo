package com.example.spring.learn.demo.lombook;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName AccessorsTest
 * @Description: Accessors翻译是存取器。通过该注解可以控制getter和setter方法的形式。
 * @Author clark
 * @Date 2021/6/30 17:12
 **/
public class AccessorsTest {


    /**
     * @Accessors(fluent = true)
     * 使用fluent属性，getter和setter方法的方法名都是属性名，且setter方法返回当前对象
     */
    @Data
    @Accessors(fluent = true)
    static class UserFluent {
        private Integer id;
        private String name;

        // 生成的getter和setter方法如下，方法体略
        // public Integer id(){}  getter
        // public UserFluent id(Integer id){}  setter
        // public String name(){} getter
        // public UserFluent name(String name){} setter
    }

    /**
     * @Accessors(chain = true)
     * 使用chain属性，setter方法返回当前对象
     */
    @Data
    @Accessors(chain = true)
    class UserChain {
        private Integer id;
        private String name;

        // 生成的setter方法如下，方法体略
        //  public UserChain setId(Integer id){}
        //  public UserChain setName(String name){}
    }

    /**
     * @Accessors(prefix = “f”)
     * 使用prefix属性，getter和setter方法会忽视属性名的指定前缀（遵守驼峰命名）
     */
    @Data
    @Accessors(prefix = "f")
    class UserPrefix {
        private Integer fId;
        private String fName;

        // 生成的getter和setter方法如下，方法体略
        // public Integer id(){}
        // public void id(Integer id){}
        // public String name(){}
        // public void name(String name){}
    }
}
