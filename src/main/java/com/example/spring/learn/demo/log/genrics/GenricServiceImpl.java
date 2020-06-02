package com.example.spring.learn.demo.log.genrics;

import com.example.spring.learn.demo.log.aspect.SystemLog;
import org.springframework.stereotype.Service;

/**
 * @author clark
 * @Description:
 * @date 2020/6/2 10:09
 */
@Service
public class GenricServiceImpl implements GenericService<String> {

    @Override
    @SystemLog
    public String printLog(String s) {
        return "printLog";
    }
}
