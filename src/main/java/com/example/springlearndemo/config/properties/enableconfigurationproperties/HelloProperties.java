package com.example.springlearndemo.config.properties.enableconfigurationproperties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author clark
 * @Description:
 * @date 2020/5/21 17:35
 *  如果一个配置类只配置@ConfigurationProperties注解，而没有使用@Component，那么在IOC容器中是获取不到properties 配置文件转化的bean。
 *  说白了 @EnableConfigurationProperties 相当于把使用 @ConfigurationProperties 的类进行了一次注入。
 *
 */
@ConfigurationProperties(prefix = "hello")
@Data
public class HelloProperties {
    private String message;
}
