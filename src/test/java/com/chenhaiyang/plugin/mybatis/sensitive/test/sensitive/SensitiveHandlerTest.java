package com.chenhaiyang.plugin.mybatis.sensitive.test.sensitive;

import com.chenhaiyang.plugin.mybatis.sensitive.type.handler.*;
import org.junit.Test;

public class SensitiveHandlerTest {

    @Test
    public void test(){
        MobilePhoneSensitiveHandler mobilePhoneSensitiveHandler = new MobilePhoneSensitiveHandler();
        String result = mobilePhoneSensitiveHandler.handle("18233583070");
        System.out.println(result);
    }

    @Test
    public void test2(){
        NameSensitiveHandler nameSensitiveHandler = new NameSensitiveHandler();
        String result = nameSensitiveHandler.handle("克扽儿阿里巴斯");
        System.out.println(result);
    }

    @Test
    public void test3(){
        EmailSensitiveHandler nameSensitiveHandler = new EmailSensitiveHandler();
        String result = nameSensitiveHandler.handle("er@ruubddfdfdfdypay.com");
        System.out.println(result);
    }
    @Test
    public void test4(){
        AddressSensitiveHandler nameSensitiveHandler = new AddressSensitiveHandler();
        String result = nameSensitiveHandler.handle("啊事实上是sds ds dsd sds d ");
        System.out.println(result);
    }

    @Test
    public void test5(){
        BandCardSensitiveHandler nameSensitiveHandler = new BandCardSensitiveHandler();
        String result = nameSensitiveHandler.handle("622000");
        System.out.println(result);
    }
    @Test
    public void test6(){
        IDCardSensitiveHandler nameSensitiveHandler = new IDCardSensitiveHandler();
        String result = nameSensitiveHandler.handle("130722199102323232");
        System.out.println(result);
    }

    @Test
    public void test7(){
        CnapsSensitiveHandler nameSensitiveHandler = new CnapsSensitiveHandler();
        String result = nameSensitiveHandler.handle("130722199102323232");
        System.out.println(result);
    }
    @Test
    public void test8(){
        FixedPhoneSensitiveHandler nameSensitiveHandler = new FixedPhoneSensitiveHandler();
        String result = nameSensitiveHandler.handle("86-10-66778899");
        System.out.println(result);
    }

    @Test
    public void test9(){
        PaySignNoSensitiveHandler nameSensitiveHandler = new PaySignNoSensitiveHandler();
        String result = nameSensitiveHandler.handle("19031317273364059018");
        System.out.println(result);
    }
}
