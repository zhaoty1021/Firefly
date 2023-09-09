package com.yingxiu.rpc.serialize.kyro;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.yingxiu.rpc.common.entity.RpcRequest;
import com.yingxiu.rpc.common.entity.RpcResponse;
import com.yingxiu.rpc.common.enums.RpcCode;
import com.yingxiu.rpc.common.exception.RpcServiceException;
import com.yingxiu.rpc.serialize.Serializer;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
@Slf4j
public class KyroSerializer implements Serializer {
    // kryo非线程安全
    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(RpcResponse.class);
        kryo.register(RpcRequest.class);
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        return kryo;
    });
    @Override
    public byte[] serialize(Object obj) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             Output output = new Output(byteArrayOutputStream)) {
            Kryo kryo = kryoThreadLocal.get();
            kryo.writeObject(output, obj);
            kryoThreadLocal.remove();
            return output.toBytes();
        } catch (Exception e) {
            log.error("序列化时有错误发生:", e);
            throw new RpcServiceException(RpcCode.SERIALIZER_FAILURE);
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             Input input = new Input(byteArrayInputStream)) {
            Kryo kryo = kryoThreadLocal.get();
            Object o = kryo.readObject(input, clz);
            kryoThreadLocal.remove();
            return clz.cast(o);
        } catch (Exception e) {
            log.error("反序列化时有错误发生:", e);
            throw new RpcServiceException(RpcCode.SERIALIZER_FAILURE);
        }
    }
}
