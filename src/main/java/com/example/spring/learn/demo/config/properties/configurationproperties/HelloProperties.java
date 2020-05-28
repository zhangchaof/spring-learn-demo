package com.example.spring.learn.demo.config.properties.configurationproperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author clark
 * @Description:
 * @date 2020/5/21 17:35
 * @ConfigurationProperties + @Component
 */
@ConfigurationProperties(prefix = "hello")
@Component
@Data
public class HelloProperties {
    private String message;
}
