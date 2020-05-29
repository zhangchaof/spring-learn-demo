package com.example.spring.learn.demo.cache;

import com.example.spring.learn.demo.mybatis.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clark
 * @Description:
 * @date 2020/5/28 14:19
 */
@RestController
@RequestMapping("/springCache")
@Slf4j
public class SpringCacheController {
    private final SpringCacheService cacheService;

    private final CacheManager cacheManager;

    public SpringCacheController(SpringCacheService cacheService, CacheManager cacheManager) {
        this.cacheService = cacheService;
        this.cacheManager = cacheManager;
    }

    @RequestMapping("/getEmployee/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
       // Cache emp = cacheManager.getCache("emp");
       // Employee employee = emp.get(id, Employee.class);
      //  log.info("{}", employee);
        return cacheService.getById(id);
    }
}
