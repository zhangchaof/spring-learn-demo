package com.example.spring.learn.demo.cache;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;

/**
 * @author clark
 * @Description:
 * @date 2020/6/6 16:52
 */

/**
 * 开启缓存
 * 默认使用ConcurrentMapCacheManager = ConcurrentMapCache,将数据保存在ConcurrentMap<Object,Cache>
 * 开发中使用缓存组件redis.ehcache、memcached
 * <p>
 * 整合redis作为中间件
 * 搭建redis环境
 * 引入redis-start
 *
 * @author clark
 */
@EnableCaching
@Configuration
public class CacheConfig {

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //序列化 值时使用此序列化方法
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

//    @Bean
//    @Primary
//    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
//        //缓存配置对象
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//        //设置key序列化器
//        //设置value序列化器
//        redisCacheConfiguration = redisCacheConfiguration.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((valueSerializer())));
//
//        return RedisCacheManager
//                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
//                .cacheDefaults(redisCacheConfiguration).build();
//    }

    @Bean
    public EhCacheCacheManager ehCacheManager() {
        EhCacheCacheManager ehCacheManager = new EhCacheCacheManager();
        return ehCacheManager;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }
}
