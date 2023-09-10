package com.yingxiu.rpc.annotation;

import com.yingxiu.rpc.common.consts.RpcConsts;

import java.lang.annotation.*;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FireProvider {
    String serviceName() default "";
    int timeout() default RpcConsts.DEFAULT_TIMEOUT; // 客户端超时时间
}
