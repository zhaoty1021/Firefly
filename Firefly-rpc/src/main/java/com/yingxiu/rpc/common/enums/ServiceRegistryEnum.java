package com.yingxiu.rpc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
@AllArgsConstructor
@Getter
public enum ServiceRegistryEnum {
    ZK("zk"),
    NACOS("nacos");

    private final String name;
}
