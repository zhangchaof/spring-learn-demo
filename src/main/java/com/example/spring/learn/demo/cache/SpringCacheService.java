package com.example.spring.learn.demo.cache;

import com.example.spring.learn.demo.mybatis.entity.Employee;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

/**
 * @author clark
 * @Description:
 * @date 2020/5/27 18:47
 */
public interface SpringCacheService {

    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    Employee getById(Integer id);

    /**
     * 更新用户信息
     * @param employee
     * @return
     */
    Employee updateEmployee(Employee employee);

    /**
     * 删除学生信息
     * @param id
     */
    void deleteEmployee(Integer id);

    /**
     * 根据名称获取信息
     * @param lastName
     * @return
     */
    Employee getEmployeeByLastName(String lastName);
}
