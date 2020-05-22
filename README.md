1. 两种不同注解注入
  1.1:使用 @EnableConfigurationProperties + @ConfigurationProperties 获取配置，见 com.example.springlearndemo.config.properties.enableconfigurationproperties
  1.2:使用 @ConfigurationProperties + @Component 获取配置，见com.example.springlearndemo.config.properties.configurationproperties
   * 注意这里不能混合使用
2.
 * 在Spring Boot 2.0之前版本都是靠重写 WebMvcConfigurerAdapter 的方法来添加自定义拦截器，消息转换器等。
 * SpringBoot 2.0 后，该类被标记为@Deprecated。现在，我们只能靠实现 WebMvcConfigurer 接口来实现了
 *
 * 配置 @EnableWebMvc 注解并实现 WebMvcConfigurer 接口之后，启动项目发现 css, js 等这些静态资源404了，网上搜集到的引起原因如下：
 * 用户配置了@EnableWebMvc
 * Spring扫描所有的注解，再从注解上扫描到@Import，把这些@Import引入的bean信息都缓存起来
 * 在扫描到@EnableWebMvc时，通过@Import加入了 DelegatingWebMvcConfiguration，也就是WebMvcConfigurationSupport
 * spring在处理@Conditional相关的注解，判断发现已有WebMvcConfigurationSupport，就跳过了spring bootr的WebMvcAutoConfiguration
 * 所以spring boot自己的静态资源配置不生效。
 *
 * 解决方式为：
 * （一）注释或删除掉@EnableWebMvc注解
 * （二）配置静态资源访问代码（以下任意其一即可）
 *  使用配置文件方式
 * spring.mvc.static-path-pattern=/static/**
 * spring.resources.static-locations=classpath:/static/
