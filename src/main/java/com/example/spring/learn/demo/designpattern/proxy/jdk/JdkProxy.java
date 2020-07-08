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

    /**
     * /可以通过构造器动态设置被代理目标类，以便于调用指定方法
     * @param target
     */
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
        ///第一个参数是指定代理类的类加载器（我们传入当前测试类的类加载器）
        //第二个参数是代理类需要实现的接口（我们传入被代理类实现的接口，这样生成的代理类和被代理类就实现了相同的接口）
        //第三个参数是invocation handler，用来处理方法的调用。这里传入我们自己实现的handler
        Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return proxyInstance;
    }

    /**
     * proxy:代表动态代理对象
     * method：代表正在执行的方法
     * args：代表调用目标方法时传入的实参
     *
     * 方法调用句柄invoke方法内部就是代理类的扩展点
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
