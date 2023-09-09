package com.yingxiu.rpc.provider.impl;

import com.yingxiu.rpc.common.enums.RpcCode;
import com.yingxiu.rpc.common.enums.ServiceRegistryEnum;
import com.yingxiu.rpc.common.exception.RpcServiceException;
import com.yingxiu.rpc.extensions.ExtensionLoader;
import com.yingxiu.rpc.provider.ServiceProvider;
import com.yingxiu.rpc.registry.ServiceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
@Slf4j
public class NacosServiceProvider implements ServiceProvider {
    @Value("${netty.port}")
    private String port;
    private static final Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    private static final Set<String> registeredService = ConcurrentHashMap.newKeySet();
    private static final ServiceRegistry serviceRegistry= ExtensionLoader.
            getExtensionLoader(ServiceRegistry.class).getExtension(ServiceRegistryEnum.NACOS.getName());
    @Override
    public <T> void addService(T service, String serviceName) {
        if (registeredService.contains(serviceName)) {
            return;
        }
        registeredService.add(serviceName);
        serviceMap.put(serviceName, service);
        log.info("向接口: {} 注册服务: {}", service.getClass().getInterfaces(), serviceName);
    }

    @Override
    public Object getService(String serviceName) {
        Object service = serviceMap.get(serviceName);
        if (service == null) {
            throw new RpcServiceException(RpcCode.SERVICE_NOT_FOUND);
        }
        return service;
    }

    @Override
    public <T> void publishService(T service,String serviceName) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            this.addService(service,serviceName);
            serviceRegistry.register(serviceName, new InetSocketAddress(host, Integer.parseInt(port)));
        } catch (UnknownHostException e) {
            log.error("occur exception when getHostAddress", e);
        }
    }
}
