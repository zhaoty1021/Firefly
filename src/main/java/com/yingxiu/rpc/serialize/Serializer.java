package com.yingxiu.rpc.serialize;

import java.io.IOException;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/9
 */
public interface Serializer {
    byte[] serialize(Object obj) throws IOException;

    <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException, ClassNotFoundException;
}
