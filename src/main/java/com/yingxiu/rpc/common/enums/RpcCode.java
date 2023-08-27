package com.yingxiu.rpc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RpcCode {
    UNKNOWN_ERROR("500","出现未知错误"),
    SUCCESS("200","成功"),
    //服务错误
    CLIENT_CONNECT_FAILURE("100001","客户端连接服务端失败"),
    SERVICE_INVOCATION_FAILURE("100002","服务调用失败"),
    SERVICE_NOT_FOUND("100003","没有找到指定的服务"),
    SERVICE_INTERFACE_NOT_EXIST("100004","注册的服务没有实现任何接口"),
    SERVICE_SCAN_PACKAGE_NOT_FOUND("100005","启动类ServiceScan注解缺失"),
    //序列化错误
    UNKNOWN_SERIALIZER("200001","不识别的(反)序列化器"),
    UNKNOWN_PACKAGE_TYPE("200002","不识别的数据包类型"),
    SERIALIZER_NOT_FOUND("200003","找不到序列化器"),
    //注册问题
    RESPONSE_NOT_MATCH("300001","响应与请求号不匹配"),
    FAILED_TO_CONNECT_TO_SERVICE_REGISTRY("300002","连接注册中心失败"),
    REGISTER_SERVICE_FAILED("300003","注册服务失败");

    private final String code;
    private final String msg;

}
