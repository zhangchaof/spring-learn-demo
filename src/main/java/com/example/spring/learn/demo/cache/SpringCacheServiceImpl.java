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
     * @param id
     * @return
     */
    @Override
    @Cacheable(cacheNames = {"emp", "temp"}, keyGenerator = "springCacheKeyGenerator", cacheManager = "cacheManager")
    public Employee getById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

}
