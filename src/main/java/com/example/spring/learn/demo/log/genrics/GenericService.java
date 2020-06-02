package com.example.spring.learn.demo.log.genrics;

/**
 * @author clark
 * @Description:
 * @date 2020/6/2 9:59
 */
public interface GenericService<T> {
    String printLog(T t);
}
