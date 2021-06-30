package com.example.spring.learn.demo.beancopy.orika.differ;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName UserVo
 * @Description:
 * @Author clark
 * @Date 2021/6/30 17:39
 **/
@Data
@Accessors(chain = true)
public class UserVo {
    private String id;
    private String userName;
    private int ageOne;
}
