package com.example.spring.learn.demo.designpattern.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author clark
 * @Description:
 * @date 2020/6/8 19:27
 */
public class JdkProxy<T> implements InvocationHandler {

    /**
     * 目标类，也就是被代理对象
     */
    private T target;


    public JdkProxy(T target) {
        this.target = target;
    }

    /**
     * 生成代理类
     *
     * @param target
     * @return
     */
    public Object getInstance(T target) {
        // 取得代理对象
        Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return proxyInstance;
    }

    /**
     * proxy:代表动态代理对象
     * method：代表正在执行的方法
     * args：代表调用目标方法时传入的实参
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用前");
        // 方法调用
        Object result = method.invoke(target, args);
        System.out.println("调用后");
        return result;
    }
}
