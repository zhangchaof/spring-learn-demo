package com.example.spring.learn.demo.cache;

import com.example.spring.learn.demo.mybatis.dao.EmployeeMapper;
import com.example.spring.learn.demo.mybatis.entity.Employee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author clark
 * @Description:
 * @date 2020/5/28 14:16
 */
@Service
public class SpringCacheServiceImpl implements SpringCacheService {
    private EmployeeMapper employeeMapper;

    public SpringCacheServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    /**
     * 将方法的运行结果进行缓存，以后要相同的数据，直接从缓存中获取，不用调用方法
     * <p>
     * CacheManager管理多个Cache组件的，对缓存的真正CRUD操作在Cache组件中，每一个缓存组有自己唯一一个名字；
     * 几个属性：
     * cacheNames/value:指定缓存组件的名称
     * key:缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
     * keyGenerator: key的生成器，可以自己指定key的生成器的组件id,
     * implements KeyGenerator接口，见SpringCacheKeyGenerator 使用:keyGenerator = "springCacheKeyGenerator"
     * key/keyGenerator 二选一
     * cacheManager: 指定缓存管理器；或者cacheResolver指定获取解析器,例:cacheManager = "cacheManager"
     * condition: 指定符合条件的情况才缓存
     * unless: 否定缓存；当unless指定的条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
     * unless= "#result==null"
     * sync:是否使用异步模式
     *
     * 原理：
     *  1.自动配置类：CacheAutoConfiguration
     *  2.缓存的配置类：见CacheConfigurationImportSelector -> selectImports 返回值
     *    org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
     *    org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
     *    org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
     *    org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
     *    ...
     *   3.打开debug=true调试模式，找到默认生效:SimpleCacheConfiguration
     *   4.给容器中注册了一个CacheManager，ConcurrentMapCacheManager
     *   5.可以获取和创建ConcurrentMapCache类型的缓存组件，他的作用讲数据保存在ConcurrentMap中
     *
     *   运行流程：
     *   @Cacheable:
     *   1.方法运行前，先去查询Cache(缓存组件),按照cacheName指定的名称获取，
     *     （CacheManager先获取相应的缓存），第一次获取缓存如果没有Cache组件会自动创建。
     *   2.去Cache中查找缓存的内容（ConcurrentMapCache->lookup,查看调用链CacheAspectSupport->findCachedItem找到产生key的地方)，使用一个key，默认就是方法的参数
     *     key是按照某种策略生成的，默认是使用keyGenerator生成的，默认使用SimpleKeyGenerator生成key
     *   3.如果没有查到缓存就调用目标方法
     *   4.将目标方法返回结果，放入缓存
     *
     *   @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数的值作为key去查询缓存，
     *   如果没有就运行目标方法并将结果放入缓存，以后来调用就可以直接使用缓存中的数据
     *
     *   核心：
     *     1).使用CacheManager[ConcurrentMapCacheManager]按照名字得到Cache[ConcurrentMapCache]组件
     *     2).key使用keyGenerator生成的，默认是SimpleKeyGenerator
     * @param id
     * @return
     */
    @Override
    @Cacheable(cacheNames = {"emp", "temp"}, keyGenerator = "springCacheKeyGenerator", cacheManager = "cacheManager")
    public Employee getById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

}
