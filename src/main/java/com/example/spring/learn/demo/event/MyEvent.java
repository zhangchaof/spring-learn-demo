package com.example.spring.learn.demo.event;

import org.springframework.context.ApplicationEvent;


/**
 * @ClassName MyEventListener
 * @Description: 事件监听
 * @Author clark
 * @Date 2021/6/22 17:23
 **/
public class MyEvent extends ApplicationEvent {
    private String name;
    private Integer age;

    public MyEvent(Object source) {
        super(source);
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
