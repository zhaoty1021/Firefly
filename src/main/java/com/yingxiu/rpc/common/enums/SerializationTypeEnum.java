package com.yingxiu.rpc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yingxiu.zty
 * @createTime on 2023/8/28
 */
@AllArgsConstructor
@Getter
public enum SerializationTypeEnum {
    KYRO((byte) 0x01, "kyro"),
    PROTOSTUFF((byte) 0x02, "protostuff"),
    HESSIAN((byte) 0X03, "hessian"),
    FURY((byte) 0X04, "fury");

    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (SerializationTypeEnum c : SerializationTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

}
