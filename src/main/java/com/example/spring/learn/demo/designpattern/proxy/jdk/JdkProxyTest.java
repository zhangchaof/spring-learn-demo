package com.example.spring.learn.demo.designpattern.proxy.jdk;

import com.example.spring.learn.demo.designpattern.proxy.jdk.testclass.Animal;
import com.example.spring.learn.demo.designpattern.proxy.jdk.testclass.Dog;

/**
 * @author clark
 * @Description:
 * @date 2020/6/9 15:32
 *
 * JDK动态代理源码级认识：
 * 1).JDK会帮我们在运行时生成一个代理类,这个代理类实际上就是我们需要代理的接口的实现类。
 * 2).实现的方法里面会调用InvocationHandler类中的invoke方法,并且同时传入自身被调用的方法的的Method对象和参数列表方便我们编码实现方法的调用。
 * 比如我们调用eat方法，那么我们就可以通过Method.Invoke(Object obj, Object... args)调用我们具体的实现类,再在周围做一些代理做的事儿。就实现了动态代理。我们对JDK的特性做一些简单的认识：
 *
 * JDK动态代理只能代理有接口的类,并且是能代理接口方法,不能代理一般的类中的方法
 * 提供了一个使用InvocationHandler作为参数的构造方法。在代理类中做一层包装,业务逻辑在invoke方法中实现
 * 重写了Object类的equals、hashCode、toString，它们都只是简单的调用了InvocationHandler的invoke方法，即可以对其进行特殊的操作，也就是说JDK的动态代理还可以代理上述三个方法
 * 在invoke方法中我们甚至可以不用Method.invoke方法调用实现类就返回。这种方式常常用在RPC框架中,在invoke方法中发起通信调用远端的接口等
 *
 *
 *
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
