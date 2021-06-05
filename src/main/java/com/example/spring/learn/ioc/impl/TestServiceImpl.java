package com.example.spring.learn.ioc.impl;

import com.example.spring.learn.ioc.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TestServiceImpl
 * @Description: TODO
 * @Author clark
 * @Date 2021/3/10 15:36
 **/
@Service
public class TestServiceImpl implements TestService {

    /**
     * list 注入
     */
    @Value("#{'${complain.report.email.receiver}'.split(',')}")
    private List<String> receivers;

    /**
     * map 注入
     */
    @Value("#{${complain.report.thirdReportType}}")
    private Map<String,String> thirdReportTypeConfig;

    @Override
    public void test() {
        System.out.println("true = " + true);
    }
}
