package com.yingxiu.rpc.common.utils;

import com.yingxiu.rpc.common.enums.CompressEnum;
import com.yingxiu.rpc.common.enums.SerializeEnum;
import com.yingxiu.rpc.common.enums.ServiceRegistryEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "firefly.rpc", ignoreUnknownFields = true)
public class FireFlyServiceConfig {
    /**
     * 序列化类型
     */
    private String serializeType= "kyro";
    /**
     * 注册中心类型
     */
    private String registryType= "nacos";
    /**
     * 压缩类型
     */
    private String compressType= "gzip";
}
