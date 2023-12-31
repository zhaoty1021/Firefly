package com.yingxiu.rpc.registry;

import com.yingxiu.rpc.extensions.SPI;
import com.yingxiu.rpc.registry.nacos.NacosServiceRegistry;

import java.net.InetSocketAddress;

/**
 * @author yingxiu.zty
 * @createTime on 2023/8/29
 */
@SPI
public interface ServiceRegistry {
    /**
     * 将一个服务注册进注册表
     *
     * @param serviceName 服务名称
     * @param inetSocketAddress 提供服务的地址
     */
    void register(String serviceName, InetSocketAddress inetSocketAddress);
    /**
     * 根据服务名称查找服务实体
     *
     * @param serviceName 服务名称
     * @return 服务实体
     */
    InetSocketAddress serviceDiscovery(String serviceName);

}
