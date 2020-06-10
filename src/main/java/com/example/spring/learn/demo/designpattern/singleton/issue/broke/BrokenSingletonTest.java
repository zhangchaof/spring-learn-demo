package com.example.spring.learn.demo.designpattern.singleton.issue.broke;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @author clark
 * @Description:
 * @date 2020/6/5 10:50
 */
public class BrokenSingletonTest {
    /**
     * 以双重检测方式为例测试反射，序列化，克隆是否能破环单例模式:
     */
    public static void main(String[] args) throws Exception {
        //通过getInstance()获取
        BrokenSingleton singleton = BrokenSingleton.getInstance();
        System.out.println("singleton的hashCode:" + singleton.hashCode());
        //通过反射获取
        Constructor<BrokenSingleton> constructor = BrokenSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        BrokenSingleton reflex = constructor.newInstance();
        System.out.println("reflex的hashCode:" + reflex.hashCode());
        //通过克隆获取
        BrokenSingleton clob = (BrokenSingleton) BrokenSingleton.getInstance().clone();
        System.out.println("clob的hashCode:" + clob.hashCode());
        //通过序列化，反序列化获取
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(BrokenSingleton.getInstance());
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        BrokenSingleton serialize = (BrokenSingleton) ois.readObject();
        if (ois != null) {
            ois.close();
        }
        if (bis != null) {
            bis.close();
        }
        if (oos != null) {
            oos.close();
        }
        if (bos != null) {
            bos.close();
        }
        System.out.println("serialize的hashCode:" + serialize.hashCode());
    }
    //运行结果表明通过getInstance()、反射、克隆、序列化这四种方式得到的Singleton对象的hashCode是不一样的，此时单例模式已然被破环
}
