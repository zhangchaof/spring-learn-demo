package com.example.spring.learn.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author clark
 */
@SpringBootApplication
//开启缓存
@EnableCaching
@MapperScan(basePackages = "com.example.spring.learn.demo.mybatis.dao")
public class SpringLearnDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnDemoApplication.class, args);
    }

}
