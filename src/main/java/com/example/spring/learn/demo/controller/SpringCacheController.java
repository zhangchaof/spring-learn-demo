package com.example.spring.learn.demo.controller;

import com.example.spring.learn.demo.cache.SpringCacheService;
import com.example.spring.learn.demo.mybatis.entity.Employee;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    @GetMapping("/getCache/{id}")
    public Employee getCache(@PathVariable Integer id) {
        Collection<String> cacheNames = cacheManager.getCacheNames();
       for(String temp : cacheNames) {
           System.out.println("cacheManager = " + cacheManager.getCache(temp));
       }
        Cache emp = cacheManager.getCache("emp");
        Employee employee = emp.get(id, Employee.class);
        return employee;
    }

    @GetMapping("/getEmployee/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        return cacheService.getById(id);
    }

    @PostMapping("/updateEmployee")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return cacheService.updateEmployee(employee);
    }
}
