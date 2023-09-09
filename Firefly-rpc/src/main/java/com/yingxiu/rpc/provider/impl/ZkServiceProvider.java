package com.yingxiu.rpc.provider.impl;

import com.yingxiu.rpc.provider.ServiceProvider;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
public class ZkServiceProvider implements ServiceProvider {
    @Override
    public <T> void addService(T service, String serviceName) {

    }

    @Override
    public Object getService(String serviceName) {
        return null;
    }

    @Override
    public <T> void publishService(T service, String serviceName) {

    }
}
