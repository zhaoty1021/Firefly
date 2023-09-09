package com.yingxiu.rpc.codec.compress.snappy;

import com.yingxiu.rpc.codec.compress.Compress;
import org.xerial.snappy.Snappy;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/9
 */
public class SnappyCompress implements Compress {
    @Override
    public byte[] compress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return Snappy.compress(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] decompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return Snappy.uncompress(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
