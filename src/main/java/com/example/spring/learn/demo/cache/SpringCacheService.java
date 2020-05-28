package com.example.spring.learn.demo.cache;

import com.example.spring.learn.demo.mybatis.entity.Employee;

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
}
