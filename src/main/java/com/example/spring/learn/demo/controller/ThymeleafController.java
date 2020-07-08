package com.example.spring.learn.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author clark
 * @Description:
 * @date 2020/6/11 15:47
 */
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    @RequestMapping(value = "/test")
    public String test(ModelMap modelMap) {
        modelMap.put("hello", "thymeleaf");
        return "thymeleaf";
    }
}
