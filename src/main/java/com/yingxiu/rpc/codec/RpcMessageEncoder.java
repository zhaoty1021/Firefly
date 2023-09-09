package com.yingxiu.rpc.codec;

import com.yingxiu.rpc.codec.compress.Compress;
import com.yingxiu.rpc.common.enums.CompressEnum;
import com.yingxiu.rpc.common.enums.SerializeEnum;
import com.yingxiu.rpc.extensions.ExtensionLoader;
import com.yingxiu.rpc.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

import static com.yingxiu.rpc.common.consts.RpcConsts.MAGIC_NUMBER;

/**
 * <p>
 * custom protocol decoder
 * <p>
 * <pre>
 *   0     1     2     3     4             5          6      7    8    9    10   11   12   13   14   15
 *   +-----+-----+-----+-----+-------------+----------+-------+----+----+----+----+----+----+----+----+
 *   |   magic   code        | messageType | compress | codec |    messageId      |      size         |
 *   +-----------------------+-------------+----------+-------+----+----+----+----+----+----+----+----+
 *   |                                                                                                |
 *   |                                         body                                                   |
 *   |                                                                                                |
 *   |                                        ... ...                                                 |
 *   +------------------------------------------------------------------------------------------------+
 * 4B  magic code（魔数）   1B messageType（消息类型） 1B compress（压缩类型） 1B codec（序列化类型）
 * 4B  messageId（消息Id） 4B size（数据长度）
 * body（object类型数据）
 * </pre>
 * @author yingxiu.zty
 * @createTime on 2023/9/4
 */
@Slf4j
public class RpcMessageEncoder extends MessageToByteEncoder<RpcMessage> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcMessage rpcMessage, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(MAGIC_NUMBER);
        byte messageType = rpcMessage.getMessageType();
        byteBuf.writeByte(messageType);
        byteBuf.writeByte(rpcMessage.getCompress());
        byteBuf.writeByte(rpcMessage.getCodec());
        byteBuf.writeInt(rpcMessage.getMessageId());
        String codecName = SerializeEnum.getName(rpcMessage.getCodec());
        log.info("codec name: [{}] ", codecName);
        //解析序列化类
        Serializer serializer = ExtensionLoader.getExtensionLoader(Serializer.class)
                .getExtension(codecName);
        byte[] bodyBytes = serializer.serialize(rpcMessage.getData());
        String compressName = CompressEnum.getName(rpcMessage.getCompress());
        //解析压缩类
        Compress compress = ExtensionLoader.getExtensionLoader(Compress.class)
                .getExtension(compressName);
        bodyBytes = compress.compress(bodyBytes);
        byteBuf.writeInt(bodyBytes.length);
        byteBuf.writeBytes(bodyBytes);
    }
}
