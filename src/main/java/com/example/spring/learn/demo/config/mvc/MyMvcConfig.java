package com.example.spring.learn.demo.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author clark
 * @Description:
 * @date 2020/5/22 20:53
 */

/**
 * ps:
 * SpringBoot对SpringMVC的自动配置不需要了，所有都是我们自己配置；所有的SpringMVC的自动配置都失效了,我们需要在配置类中添加@EnableWebMvc即可；
 * 原理：WebMvcAutoConfiguration里面@ConditionalOnMissingBean({WebMvcConfigurationSupport.class})才生效，
 *      而@EnableWebMvc导入了 @Import({DelegatingWebMvcConfiguration.class}) ->DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport
 *
 * 解决方式为：
 * （一）注释或删除掉@EnableWebMvc注解
 * （二）配置静态资源访问代码（以下任意其一即可）
 *  使用配置文件方式
 * spring.mvc.static-path-pattern=/static/**
 * spring.resources.static-locations=classpath:/static/

 */
@Configuration
//@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}
