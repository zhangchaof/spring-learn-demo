package com.example.springlearndemo.config.properties;

import com.example.springlearndemo.BaseTest;
import com.example.springlearndemo.config.properties.enableconfigurationproperties.HelloProperties;
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
    private com.example.springlearndemo.config.properties.configurationproperties.HelloProperties helloProperties;

    @Autowired
    private com.example.springlearndemo.config.properties.enableconfigurationproperties.HelloProperties enableHelloProperties;

    @Test
    public void printMessage() {
        log.info("configurationproperties message:{}",helloProperties.getMessage());
        log.info("enableconfigurationproperties message:{}",enableHelloProperties.getMessage());
    }
}
