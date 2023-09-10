package com.yingxiu.rpc.common.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
public class SingletonFactory {
    private static final Map<String, Object> OBJECT_MAP = new ConcurrentHashMap<>();
    private SingletonFactory() {
    }

    public static <T> T getInstance(Class<T> c) {
        if(c==null){
            throw new IllegalArgumentException();
        }
        Object instance = OBJECT_MAP.get(c);
        synchronized (c) {
            if(instance == null) {
                try {
                    instance = c.newInstance();
                    OBJECT_MAP.put(c.toString(), instance);
                } catch (IllegalAccessException | InstantiationException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
        return c.cast(instance);
    }
}
