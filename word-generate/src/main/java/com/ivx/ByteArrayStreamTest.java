package com.ivx;


import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author skyler&lt;kl142857h@163.com&gt;
 * @apiNote
 * @since 2023/1/12 10:54
 */
@Slf4j
public class ByteArrayStreamTest {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024 * 1024 * 10);
        byteArrayOutputStream.write(new byte[]{1, 2, 3, 4});
        byte[] bytes = byteArrayOutputStream.toByteArray();
        log.info("hello");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
    }
}
