package com.example.spring.learn.demo.config.properties;

import com.example.spring.learn.demo.BaseTest;
import com.example.spring.learn.demo.config.properties.enableconfigurationproperties.HelloAutoConfiguration;
import com.example.spring.learn.demo.config.properties.enableconfigurationproperties.HelloProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author clark
 * @Description:
 * @date 2020/5/21 17:50
 */
@Slf4j
public class HelloPropertiesTest extends BaseTest {
    @Autowired
    private com.example.spring.learn.demo.config.properties.configurationproperties.HelloProperties helloProperties;

    @Autowired
    private HelloProperties enableHelloProperties;

    @Autowired
    private HelloAutoConfiguration helloAutoConfiguration;

    @Test
    public void printMessage() {
        log.info("configurationproperties message:{}",helloProperties.getMessage());
        log.info("enableconfigurationproperties message:{}",enableHelloProperties.getMessage());
        helloAutoConfiguration.print();
    }
}
