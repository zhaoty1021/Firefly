package com.yingxiu.rpc.serialize.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yingxiu.rpc.common.enums.RpcCode;
import com.yingxiu.rpc.common.exception.RpcServiceException;
import com.yingxiu.rpc.serialize.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
@Slf4j
public class FastJsonSerializer implements Serializer {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public byte[] serialize(Object obj) throws IOException {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error("序列化时有错误发生:", e);
            throw new RpcServiceException(RpcCode.SERIALIZER_FAILURE);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException, ClassNotFoundException {
        return JSON.parseObject(new String(bytes), clz);
    }
}
