package com.example.spring.learn.demo.designpattern.proxy.jdk.proxysource;

import com.example.spring.learn.demo.designpattern.proxy.jdk.testclass.Animal;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author clark
 * @Description:
 * @date 2020/6/9 15:37
 */
public class JdkProxySourceClass {

    /**
     * 获取jdk代理类，拖到idea里面反编译下
     * @param path
     */
    public static void saveProxyClass(String path) {

        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Animal.class});
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            fos.write(classFile);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        saveProxyClass("D:/$Proxy0.class");
    }
}