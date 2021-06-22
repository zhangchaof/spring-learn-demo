package com.example.spring.learn.demo.controller;

import com.example.spring.learn.demo.event.MyEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ApplicationEventListenerController
 * @Description: 测试event与listener
 * @Author clark
 * @Date 2021/6/22 16:26
 **/
@RestController
@RequestMapping("/eventListener")
@Api("event测试")
public class EventListenerController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/addEvent")
    @ApiOperation(value = "测试event")
    public void addEvent(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        MyEvent myEvent = new MyEvent(this);
        myEvent.setAge(age);
        myEvent.setName(name);
        context.publishEvent(myEvent);
    }
}
