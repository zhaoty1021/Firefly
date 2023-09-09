package com.yingxiu.rpc.common.exception;

import com.yingxiu.rpc.common.enums.RpcCode;

public class RpcServiceException extends RuntimeException{
    public RpcServiceException() {
        super();
    }
    public RpcServiceException(String message) {
        super(message);
    }
    public RpcServiceException(RpcCode code){
        super(code.getMsg());
    }
    public RpcServiceException(RpcCode code,String msg){
        super(code.getMsg()+":"+msg);
    }
}
