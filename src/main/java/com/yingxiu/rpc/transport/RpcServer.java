package com.yingxiu.rpc.transport;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/1
 */
public interface RpcServer {
    void start();

    <T> void publishService(T service, String serviceName);
}
