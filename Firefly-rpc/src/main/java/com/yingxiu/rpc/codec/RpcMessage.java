package com.yingxiu.rpc.codec;

import lombok.*;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/4
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RpcMessage {
    /**
     * rpc message type
     */
    private byte messageType;
    /**
     * serialization type
     */
    private byte codec;
    /**
     * compress type
     */
    private byte compress;
    /**
     * message id
     */
    private int messageId;
    /**
     * request data
     */
    private Object data;
}
