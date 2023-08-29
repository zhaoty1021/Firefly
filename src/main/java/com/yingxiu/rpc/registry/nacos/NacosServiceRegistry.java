package com.yingxiu.rpc.registry.nacos;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.yingxiu.rpc.common.enums.RpcCode;
import com.yingxiu.rpc.common.exception.RpcServiceException;
import com.yingxiu.rpc.registry.ServiceRegistry;
import com.yingxiu.rpc.registry.utils.NacosUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author yingxiu.zty
 * @createTime on 2023/8/30
 */
@Slf4j
public class NacosServiceRegistry implements ServiceRegistry {
    @Override
    public void register(String serviceName, InetSocketAddress inetSocketAddress) {
        try {
            NacosUtil.registerService(serviceName, inetSocketAddress);
        } catch (NacosException e) {
            log.error("注册服务时有错误发生:", e);
            throw new RpcServiceException(RpcCode.REGISTER_SERVICE_FAILED);
        }
    }

    @Override
    public InetSocketAddress lookupService(String serviceName) {
        try {
            List<Instance> instances = NacosUtil.getAllInstance(serviceName);
            if(instances.size() == 0) {
                log.error("找不到对应的服务: " + serviceName);
                throw new RpcServiceException(RpcCode.SERVICE_NOT_FOUND);
            }
            Instance instance = instances.get(0);
            return new InetSocketAddress(instance.getIp(), instance.getPort());
        } catch (NacosException e) {
            log.error("获取服务时有错误发生:", e);
        }
        return null;
    }

}
