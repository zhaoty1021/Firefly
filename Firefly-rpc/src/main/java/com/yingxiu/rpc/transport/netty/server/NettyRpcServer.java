package com.yingxiu.rpc.transport.netty.server;

import com.yingxiu.rpc.codec.RpcMessageDecoder;
import com.yingxiu.rpc.codec.RpcMessageEncoder;
import com.yingxiu.rpc.common.enums.RpcCode;
import com.yingxiu.rpc.common.enums.ServiceRegistryEnum;
import com.yingxiu.rpc.common.exception.RpcServiceException;
import com.yingxiu.rpc.common.factory.SingletonFactory;
import com.yingxiu.rpc.common.utils.FireFlyServiceConfig;
import com.yingxiu.rpc.extensions.ExtensionLoader;
import com.yingxiu.rpc.provider.ServiceProvider;
import com.yingxiu.rpc.registry.ServiceRegistry;
import com.yingxiu.rpc.transport.RpcServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/1
 */
@Slf4j
public class NettyRpcServer implements RpcServer {
    @Value("${netty.port}")
    private String port;
    @Value("${netty.host}")
    private String host;
    protected ServiceRegistry serviceRegistry;
    protected ServiceProvider serviceProvider;
    @Resource
    private FireFlyServiceConfig fireFlyServiceConfig;
    @Override
    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // TCP默认开启了 Nagle 算法，该算法的作用是尽可能的发送大数据快，减少网络传输。TCP_NODELAY 参数的作用就是控制是否启用 Nagle 算法。
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 是否开启 TCP 底层心跳机制
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //表示系统用于临时存放已完成三次握手的请求的队列的最大长度,如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    // 当客户端第一次进行请求的时候才会进行初始化
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            // 30 秒之内没有收到客户端请求的话就关闭连接
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new IdleStateHandler(30, 0, 0, TimeUnit.SECONDS));
                            p.addLast(new RpcMessageEncoder());
                            p.addLast(new RpcMessageDecoder());
                            p.addLast(new NettyRpcServerHandler());
                        }
                    });
            // 绑定端口，同步等待绑定成功
            ChannelFuture f = b.bind(host, Integer.parseInt(port)).sync();
            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("启动服务器时有错误发生: ", e);
            throw new RpcServiceException(RpcCode.SERVER_START_FAILURE);
        } finally {
            log.error("关闭 bossGroup 和 workerGroup");
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    @Override
    public <T> void publishService(T service, String serviceName) {
        serviceRegistry= SingletonFactory.getInstance(ExtensionLoader.
                getExtensionLoader(ServiceRegistry.class).getExtension(fireFlyServiceConfig.getRegistryType()).getClass());
        serviceProvider= SingletonFactory.getInstance(ExtensionLoader.
                getExtensionLoader(ServiceProvider.class).getExtension(fireFlyServiceConfig.getRegistryType()).getClass());
        serviceProvider.addService(service, serviceName);
        serviceRegistry.register(serviceName, new InetSocketAddress(host, Integer.parseInt(port)));
    }
}
