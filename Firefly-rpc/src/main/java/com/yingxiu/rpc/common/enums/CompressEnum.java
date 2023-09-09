package com.yingxiu.rpc.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author yingxiu.zty
 * @createTime on 2023/8/31
 */
@AllArgsConstructor
@Getter
public enum CompressEnum {
    NONE((byte) 0x00, "none"),
    GZIP((byte) 0x01, "gzip"),
    BZIP((byte) 0x02, "bzip"),
    DEFLATER((byte) 0x03, "deflater"),
    LZ4((byte) 0x04, "lz4"),
    SNAPPY((byte) 0x05,"Snappy");
    private final byte code;
    private final String name;

    public static String getName(byte code) {
        for (CompressEnum c : CompressEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }
}
