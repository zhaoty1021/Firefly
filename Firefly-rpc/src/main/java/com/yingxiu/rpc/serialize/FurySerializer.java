package com.yingxiu.rpc.serialize;

import io.fury.Fury;
import io.fury.Language;
import io.fury.ThreadSafeFury;
import io.fury.serializer.CompatibleMode;

import java.io.IOException;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/10
 */
public class FurySerializer implements Serializer{
    private ThreadSafeFury fury = Fury.builder()
            .withLanguage(Language.JAVA)
            .withRefTracking(false)
            .withNumberCompressed(true)
            .withCompatibleMode(CompatibleMode.COMPATIBLE)
            .buildThreadSafeFury();
    @Override
    public byte[] serialize(Object obj) throws IOException {
        return fury.serialize(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException, ClassNotFoundException {
        return (T) fury.deserialize(bytes);
    }
}
