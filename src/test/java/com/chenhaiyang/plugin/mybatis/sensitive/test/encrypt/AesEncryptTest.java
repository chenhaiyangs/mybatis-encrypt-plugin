package com.chenhaiyang.plugin.mybatis.sensitive.test.encrypt;

import com.chenhaiyang.plugin.mybatis.sensitive.encrypt.AesSupport;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

public class AesEncryptTest {

    @Test
    public void test() throws NoSuchAlgorithmException {
        String key="1870577f29b17d6787782f35998c4a79";
        String src ="测试原文";
        AesSupport aesSupport = new AesSupport(key);
        String result = aesSupport.encrypt(src);
        System.out.println(result);
        String src2 = aesSupport.decrypt(result);
        System.out.println(src2);
    }
}
