package com.yingxiu.rpc.codec.compress;

import com.yingxiu.rpc.extensions.SPI;

/**
 * @author yingxiu.zty
 * @createTime on 2023/8/31
 */
@SPI
public interface Compress {
    /**
     * 压缩
     *
     * @param bytes 字节
     * @return {@link byte[]}
     */
    byte[] compress(byte[] bytes);

    /**
     * 解压缩
     *
     * @param bytes 字节
     * @return {@link byte[]}
     */
    byte[] decompress(byte[] bytes);
}
