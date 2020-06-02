package com.example.spring.learn.demo.log;

import com.example.spring.learn.demo.log.aspect.SystemLog;
import org.springframework.stereotype.Service;

/**
 * @author clark
 * @Description:
 * @date 2020/6/2 9:42
 */
@Service
public class LogService2 {
    @SystemLog
    public String printLog(String print) {
        return "printLog";
    }
}
