package com.yingxiu.rpc.compress;

import com.yingxiu.rpc.extensions.SPI;

/**
 * @author yingxiu.zty
 * @createTime on 2023/8/31
 */
@SPI
public interface Compress {
    byte[] compress(byte[] bytes);
    byte[] decompress(byte[] bytes);
}
