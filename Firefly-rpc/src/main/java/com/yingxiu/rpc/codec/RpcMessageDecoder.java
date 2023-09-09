package com.yingxiu.rpc.codec;

import com.yingxiu.rpc.codec.compress.Compress;
import com.yingxiu.rpc.common.consts.RpcConsts;
import com.yingxiu.rpc.common.entity.RpcRequest;
import com.yingxiu.rpc.common.entity.RpcResponse;
import com.yingxiu.rpc.common.enums.CompressEnum;
import com.yingxiu.rpc.common.enums.RpcCode;
import com.yingxiu.rpc.common.enums.SerializeEnum;
import com.yingxiu.rpc.common.exception.RpcServiceException;
import com.yingxiu.rpc.extensions.ExtensionLoader;
import com.yingxiu.rpc.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/4
 */
@Slf4j
public class RpcMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        checkMagicNumber(byteBuf);
        byte messageType = byteBuf.readByte();
        byte compressType = byteBuf.readByte();
        byte codecType = byteBuf.readByte();
        int messageId = byteBuf.readInt();
        int size=byteBuf.readInt();
        RpcMessage rpcMessage = RpcMessage.builder()
                .compress(compressType)
                .codec(codecType)
                .messageId(messageId)
                .messageType(messageType).build();
        Class<?> messageClass;
        if(size>0){
            byte[] payload = new byte[size];
            byteBuf.readBytes(payload);
            if (messageType == RpcConsts.REQUEST_TYPE) {
                messageClass = RpcRequest.class;
            } else if (messageType == RpcConsts.RESPONSE_TYPE) {
                messageClass = RpcResponse.class;
            } else {
                log.error("不识别的数据包: {}", messageType);
                throw new RpcServiceException(RpcCode.UNKNOWN_MESSAGE_TYPE);
            }
            String compressName = CompressEnum.getName(compressType);
            Compress compress = ExtensionLoader.getExtensionLoader(Compress.class)
                    .getExtension(compressName);
            payload = compress.decompress(payload);
            // deserialize the object
            String codecName = SerializeEnum.getName(rpcMessage.getCodec());
            log.info("codec name: [{}] ", codecName);
            Serializer serializer = ExtensionLoader.getExtensionLoader(Serializer.class)
                    .getExtension(codecName);
            if (serializer == null) {
                log.error("不识别的反序列化器: {}", codecType);
                throw new RpcServiceException(RpcCode.UNKNOWN_SERIALIZER);
            }
            Object obj = serializer.deserialize(payload, messageClass);
            list.add(obj);
        }


    }
    private void checkMagicNumber(ByteBuf in) {
        // read the first 4 bit, which is the magic number, and compare
        int len = RpcConsts.MAGIC_NUMBER.length;
        byte[] tmp = new byte[len];
        in.readBytes(tmp);
        for (int i = 0; i < len; i++) {
            if (tmp[i] != RpcConsts.MAGIC_NUMBER[i]) {
                throw new RpcServiceException(RpcCode.UNKNOWN_MAGIC_NUMBER, Arrays.toString(tmp));
            }
        }
    }
}
