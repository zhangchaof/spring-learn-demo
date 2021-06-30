package com.example.spring.learn.demo.beancopy.orika.differ;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName User
 * @Description:
 * @Author clark
 * @Date 2021/6/30 17:06
 **/
@Data
@Accessors(chain = true)
public class User {
    private String id;
    private String name;
    private int age;
}
