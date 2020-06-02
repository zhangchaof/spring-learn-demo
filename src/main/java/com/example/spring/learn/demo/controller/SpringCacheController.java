package com.example.spring.learn.demo.controller;

import com.example.spring.learn.demo.cache.SpringCacheService;
import com.example.spring.learn.demo.mybatis.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author clark
 * @Description:
 * @date 2020/5/28 14:19
 */
@RestController
@RequestMapping("/springCache")
public class SpringCacheController {
    private final SpringCacheService cacheService;

    private final CacheManager cacheManager;

    public SpringCacheController(SpringCacheService cacheService, CacheManager cacheManager) {
        this.cacheService = cacheService;
        this.cacheManager = cacheManager;
    }

    /**
     * 测试内部工作
     * @param id
     * @return
     */
    @RequestMapping("/test/getCache/{id}")
    public Employee getCache(@PathVariable Integer id) {
        Cache emp = cacheManager.getCache("emp");
        Employee employee = emp.get(id, Employee.class);
        return employee;
    }

    @RequestMapping("/getEmployee/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return cacheService.getById(id);
    }

    @RequestMapping("/updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return cacheService.updateEmployee(employee);
    }
}
