package com.yingxiu.rpc.codec.compress.lz4;

import com.yingxiu.rpc.codec.compress.Compress;
import lombok.extern.slf4j.Slf4j;
import net.jpountz.lz4.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * @author yingxiu.zty
 * @createTime on 2023/9/9
 */
@Slf4j
public class Lz4Compress implements Compress {
    private static final int ARRAY_SIZE = 4096;
    private static LZ4Factory factory = LZ4Factory.fastestInstance();
    private static LZ4Compressor compressor = factory.fastCompressor();
    private static LZ4FastDecompressor decompressor = factory.fastDecompressor();

    @Override
    public byte[] compress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            LZ4BlockOutputStream lz4BlockOutputStream = new LZ4BlockOutputStream(outputStream, ARRAY_SIZE, compressor);
            lz4BlockOutputStream.write(bytes);
            lz4BlockOutputStream.finish();
            return outputStream.toByteArray();
        } catch (Exception e) {
            log.error("Lz4Util compress error", e);
            return null;
        }
    }

    @Override
    public byte[] decompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(ARRAY_SIZE);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            LZ4BlockInputStream decompressedInputStream
                    = new LZ4BlockInputStream(inputStream, decompressor);
            int count;
            byte[] buffer = new byte[ARRAY_SIZE];
            while ((count = decompressedInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            log.error("Lz4Util uncompress error", e);
            return null;
        }
    }
}
