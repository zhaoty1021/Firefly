package com.yingxiu.rpc.common.entity;

import com.yingxiu.rpc.common.enums.RpcCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/4
 */
@Data
public class RpcResponse<T> implements Serializable {
    private static final long serialVersionUID = -6272644247777230492L;
    /**
     * 响应对应的请求号
     */
    private String requestId;
    /**
     * 响应状态补充信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public static <T> RpcResponse<T> success(T data, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setRequestId(requestId);
        response.setMessage("SUCCESS");
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail(RpcCode code, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setRequestId(requestId);
        response.setMessage(code.getMsg());
        return response;
    }
}
