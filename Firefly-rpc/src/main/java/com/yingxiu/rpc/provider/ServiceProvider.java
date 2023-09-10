package com.yingxiu.rpc.provider;

import com.yingxiu.rpc.extensions.SPI;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
@SPI
public interface ServiceProvider {
    /**
     * 添加服务
     *
     * @param service     服务
     * @param serviceName 服务名称
     */
    <T> void addService(T service, String serviceName);

    /**
     * 获取服务
     *
     * @param serviceName 服务名称
     * @return {@link Object}
     */
    Object getService(String serviceName);

    /**
     * 发布服务
     *
     * @param service     服务
     * @param serviceName 服务名称
     */
    <T> void publishService(T service, String serviceName);

}
