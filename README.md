1. 两种不同注解注入
  1.1:使用 @EnableConfigurationProperties + @ConfigurationProperties 获取配置，见 com.example.springlearndemo.config.properties.enableconfigurationproperties
  1.2:使用 @ConfigurationProperties + @Component 获取配置，见com.example.springlearndemo.config.properties.configurationproperties
   * 注意这里不能混合使用
2.
 * 在Spring Boot 2.0之前版本都是靠重写 WebMvcConfigurerAdapter 的方法来添加自定义拦截器，消息转换器等。
 * SpringBoot 2.0 后，该类被标记为@Deprecated。现在，我们只能靠实现 WebMvcConfigurer 接口来实现了
 *
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
 
 3.查找数值中重复数据
