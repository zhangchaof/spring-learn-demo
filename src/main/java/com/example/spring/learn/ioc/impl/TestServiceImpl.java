package com.example.spring.learn.ioc.impl;

import com.example.spring.learn.ioc.TestService;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestServiceImpl
 * @Description: TODO
 * @Author clark
 * @Date 2021/3/10 15:36
 **/
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        System.out.println("true = " + true);
    }
}
