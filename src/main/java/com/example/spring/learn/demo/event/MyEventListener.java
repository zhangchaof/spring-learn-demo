package com.example.spring.learn.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName EventListener
 * @Description: event事件
 * @Author clark
 * @Date 2021/6/22 17:27
 **/
@Component
@Slf4j
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("myEvent = " + myEvent);
        log.info("myevent:{}",myEvent);
    }
}
