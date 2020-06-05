package com.example.spring.learn.demo.design.singleton.register;

import java.util.HashMap;
import java.util.Map;

/**
 * @author clark
 * @Description:
 * @date 2020/6/5 10:40
 */
public class SingletonRegister {
    private static Map<String, Object> singletonMap = new HashMap<>();

    private SingletonRegister() {
    }

    /**
     * 用SSingletonRegister 将多种的单例类统一管理，在使用时根据key获取对象对应类型的对象。
     * 这种方式使得我们可以管理多种类型的单例，并且在使用时可以通过统一的接口进行获取操作，降低了用户的使用成本，也对用户隐藏了具体实现，降低了耦合度。
     *
     * @param key
     * @param instance
     */
    public static void registerService(String key, Object instance) {
        if (!singletonMap.containsKey(key)) {
            singletonMap.put(key, instance);
        }
    }

    public static Object getService(String key) {
        return singletonMap.get(key);
    }

}
