package com.example.spring.learn.elasticsearch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ESController
 * @Description: TODO
 * @Author clark
 * @Date 2021/3/2 17:13
 **/
@RequestMapping("/elasticSearch")
@RestController
public class ElasticSearchController {

    @RequestMapping("/get")
    public String get() {
        return null;
    }
}
