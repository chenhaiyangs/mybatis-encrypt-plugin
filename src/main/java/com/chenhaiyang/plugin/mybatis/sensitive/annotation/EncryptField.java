package com.chenhaiyang.plugin.mybatis.sensitive.annotation;

import java.lang.annotation.*;

/**
 * 标记了注解的字段会在写请求时对数据进行加密，在写请求时进行解密
 * @author chenhaiyang
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EncryptField {
}
