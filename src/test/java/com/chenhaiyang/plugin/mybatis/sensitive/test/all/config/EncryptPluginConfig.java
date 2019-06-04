package com.chenhaiyang.plugin.mybatis.sensitive.test.all.config;

import com.chenhaiyang.plugin.mybatis.sensitive.Encrypt;
import com.chenhaiyang.plugin.mybatis.sensitive.encrypt.AesSupport;
import com.chenhaiyang.plugin.mybatis.sensitive.interceptor.DecryptReadInterceptor;
import com.chenhaiyang.plugin.mybatis.sensitive.interceptor.SensitiveAndEncryptWriteInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 插件配置
 */
@Configuration
public class EncryptPluginConfig {


    @Bean
    Encrypt encryptor() throws Exception{ ;
       return new AesSupport("1870577f29b17d6787782f35998c4a79");
    }

    @Bean
    ConfigurationCustomizer configurationCustomizer() throws Exception{
        DecryptReadInterceptor decryptReadInterceptor = new DecryptReadInterceptor(encryptor());
        SensitiveAndEncryptWriteInterceptor sensitiveAndEncryptWriteInterceptor = new SensitiveAndEncryptWriteInterceptor(encryptor());

        return (configuration) -> {
            configuration.addInterceptor(decryptReadInterceptor);
            configuration.addInterceptor(sensitiveAndEncryptWriteInterceptor);
        };
    }
}
