package com.yingxiu.rpc.extensions;

/**
 * @author yingxiu.zty
 * @createTime on 2023/8/28
 */
public class Holder<T> {

    private volatile T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}