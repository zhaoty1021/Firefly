package com.yingxiu.rpc.serialize.protostuff;

import com.yingxiu.rpc.serialize.Serializer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.LinkedBuffer;
import io.protostuff.runtime.RuntimeSchema;

import java.io.IOException;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
public class ProtostuffSerializer implements Serializer {
    /**
     * 避免每次序列化都重新申请Buffer空间
     */
    private static final LinkedBuffer BUFFER = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    @Override
    public byte[] serialize(Object obj) throws IOException {
        Class<?> clazz = obj.getClass();
        Schema schema = RuntimeSchema.getSchema(clazz);
        byte[] bytes;
        try {
            bytes = ProtostuffIOUtil.toByteArray(obj, schema, BUFFER);
        } finally {
            BUFFER.clear();
        }
        return bytes;
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException, ClassNotFoundException {
        Schema<T> schema = RuntimeSchema.getSchema(clz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
        return obj;
    }
}
