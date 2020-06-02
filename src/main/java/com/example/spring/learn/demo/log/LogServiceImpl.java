package com.example.spring.learn.demo.log;

import com.example.spring.learn.demo.log.aspect.SystemLog;
import org.springframework.stereotype.Service;

/**
 * @author clark
 * @Description:
 * @date 2020/6/2 9:41
 */
@Service
public class LogServiceImpl implements LogService {

    @Override
    @SystemLog
    public String printLog(String print) {
        return "printLog";
    }
}
