package com.example.spring.learn.demo.designpattern.singleton.issue.protect;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * @author clark
 * @Description:
 * @date 2020/6/5 10:59
 */
public class ProtectSingletonTest {
    public static void main(String[] args) throws Exception {
        //通过getInstance()获取
        ProtectSingleton singleton = ProtectSingleton.getInstance();
        System.out.println("singleton的hashCode:" + singleton.hashCode());
        //通过克隆获取
        ProtectSingleton clob = ProtectSingleton.getInstance().clone();
        System.out.println("clob的hashCode:" + clob.hashCode());
        //通过序列化，反序列化获取
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(ProtectSingleton.getInstance());
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        ProtectSingleton serialize = (ProtectSingleton) ois.readObject();
        if (ois != null) ois.close();
        if (bis != null) bis.close();
        if (oos != null) oos.close();
        if (bos != null) bos.close();
        System.out.println("serialize的hashCode:" + serialize.hashCode());
        //通过反射获取
        Constructor<ProtectSingleton> constructor = ProtectSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        ProtectSingleton reflex = constructor.newInstance();
        System.out.println("reflex的hashCode:" + reflex.hashCode());
        //从运行结果上看重写clone()，添加readResolve()后通过克隆和序列化得到的对象的hashCode与从getInstance()得到的对象得而hashCode值相同，
        // ；
    }
}
