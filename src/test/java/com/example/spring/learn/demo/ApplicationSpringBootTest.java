package com.example.spring.learn.demo;

import com.example.spring.learn.ioc.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @ClassName ApplicationSpringBootTest
 * @Description: TODO
 * @Author clark
 * @Date 2021/3/10 15:38
 **/
public class ApplicationSpringBootTest extends BaseTest{
    @Autowired
    private Map<String, TestService> map;

    @Test
    public void testMap() {
        map.entrySet().forEach(vo -> {
            System.out.println("vo.getKey() + \":\" + vo.getValue() = " + vo.getKey() + ":" + vo.getValue());
        });
    }
}
