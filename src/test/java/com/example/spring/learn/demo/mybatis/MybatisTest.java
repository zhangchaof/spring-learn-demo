package com.example.spring.learn.demo.mybatis;

import com.example.spring.learn.demo.BaseTest;
import com.example.spring.learn.demo.mybatis.dao.DepartmentMapper;
import com.example.spring.learn.demo.mybatis.entity.Department;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author clark
 * @Description:
 * @date 2020/5/28 11:57
 */
public class MybatisTest extends BaseTest {

    @Autowired
    private DepartmentMapper mapper;

    @Test
    public void testConnection() {
        Department department = mapper.selectByPrimaryKey(1);
        System.out.println("department = " + department);
    }
}
