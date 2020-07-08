package com.example.spring.learn.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author clark
 * @Description:
 * @date 2020/7/8 14:41
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
