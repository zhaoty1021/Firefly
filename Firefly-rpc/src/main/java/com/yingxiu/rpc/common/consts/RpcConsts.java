package com.yingxiu.rpc.common.consts;

import com.yingxiu.rpc.codec.RpcMessage;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/9
 */
public class RpcConsts {
    /**
     * 魔数
     * @see RpcMessage
     */
    public static final byte[] MAGIC_NUMBER = {(byte) 'f', (byte) 'i', (byte) 'r', (byte) 'e'};
    public static final byte REQUEST_TYPE = 1;
    public static final byte RESPONSE_TYPE = 2;
    public static final int DEFAULT_TIMEOUT=5000;

}
