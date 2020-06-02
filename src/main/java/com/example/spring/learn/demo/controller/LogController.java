package com.example.spring.learn.demo.controller;

import com.example.spring.learn.demo.log.LogService;
import com.example.spring.learn.demo.log.LogService2;
import com.example.spring.learn.demo.log.aspect.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.spring.learn.demo.log.genrics.GenericService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author clark
 * @Description:
 * @date 2020/6/2 9:46
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

    @Autowired
    private LogService2 logService2;

    @Autowired
    private GenericService genricService;

    @RequestMapping("/printLog")
    public void printLog(String name) {
        //logService.printLog(name);
        //logService2.printLog(name);
        genricService.printLog(name);
    }

    @RequestMapping("/print")
    @SystemLog
    public void print(HttpServletRequest request, @RequestBody Map name) {
        //logService.printLog(name);
        //logService2.printLog(name);
        // genricService.printLog("name");
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("parameterMap = " + parameterMap);
    }
}
