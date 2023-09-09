# Firefly
基于Netty的手写rpc框架
# 特性
+ 支持多种压缩方式：gzip，lz4，snappy
+ 支持多种注册中心： Nacos，zookeeper
+ 支持服务发现服务注册
+ 实现了Socket 传输与 Netty 传输两种网络传输方式
+ 支持多种序列化方式：kyro，protostuff，hessian，fury，fastjson
+ 采用自定义的RPC消息协议
+ 对 SPI 机制的运用
# 项目模块
+ Firefly-api —— 通用接口
+ Firefly-rpc —— 框架核心
+ Firefly-client —— 客户端
+ Firefly-server —— 服务端
# 消息协议
```
0     1     2     3     4             5          6      7    8    9    10   11   12   13   14   15
+-----+-----+-----+-----+-------------+----------+-------+----+----+----+----+----+----+----+----+
|   magic   code        | messageType | compress | codec |    messageId      |      size         |
+-----------------------+-------------+----------+-------+----+----+----+----+----+----+----+----+
|                                                                                                |
|                                         body                                                   |
|                                                                                                |
|                                        ... ...                                                 |
+------------------------------------------------------------------------------------------------+
```
|   长度   |   code   |   名称   |
| ---- | ---- | ---- |
|   4B   |   magic code   |   魔数   |
|   1B   |   messageType   |  消息类型    |
|   1B   |   compress   |  压缩类型    |
|   1B   |   codec   |  序列化类型    |
|   4B   |   messageId   |  消息Id    |
|   4B   |   size   |   数据长度   |
> 4B  magic code（魔数）  1B messageType（消息类型） 1B compress（压缩类型）1B codec（序列化类型）
> 4B  messageId（消息Id） 4B size（数据长度）
# RPC架构
![image](https://github.com/zhaoty1021/Firefly/assets/45206134/c98fa882-594e-48ac-8970-a1c8e2f66468)
## 客户端架构
![image](https://github.com/zhaoty1021/Firefly/assets/45206134/ddc65589-f351-405a-85b9-ff9f2da4fdee)
## 服务端架构
![image](https://github.com/zhaoty1021/Firefly/assets/45206134/11f3e964-0f2c-4452-abb9-37a7d19ebdaa)
