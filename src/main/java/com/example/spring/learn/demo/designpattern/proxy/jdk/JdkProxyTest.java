package com.example.spring.learn.demo.designpattern.proxy.jdk;

import com.example.spring.learn.demo.designpattern.proxy.jdk.testclass.Animal;
import com.example.spring.learn.demo.designpattern.proxy.jdk.testclass.Dog;

/**
 * @author clark
 * @Description:
 * @date 2020/6/9 15:32
 */
public class JdkProxyTest {
    /**
     *
     * @param args
     *
     * 原理
     *   1.拿到被代理对象的引用，反射获取到它所有的接口
     *   2.JDK proxy类重新生成一个新的类，同事新的类要实现被代理类所有实现
     *   3.动态生成Java代码，把新加的业务逻辑方法由一定的逻辑代码去调用
     *   4.编译生成的java代码.class
     *   5.再重新加载到JVM中
     *  以上过程叫字节码重组
     *
     *  JDK有个规范,只要是$开头的一般都是自动生成的
     *  通过反编译工具可以查看源码
     *
     */
    public static void main(String[] args) {
        //创建一个实例对象，这个对象是被代理的对象
        Animal animal = new Dog();
        //创建一个与代理对象相关联的InvocationHandler
        JdkProxy proxy = new JdkProxy<>(animal);
        //创建一个代理对象dogProxy来代理Dog，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        Animal dogProxy = (Animal) proxy.getInstance(animal);
        //代理执行吃东西的方法
        dogProxy.eat("test");
    }
}
