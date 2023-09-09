package com.yingxiu.rpc.codec.compress;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/9
 */
public class NoCompress implements Compress {

    @Override
    public byte[] compress(byte[] bytes){
        return bytes;
    }

    @Override
    public byte[] decompress(byte[] bytes){
        return bytes;
    }
}
