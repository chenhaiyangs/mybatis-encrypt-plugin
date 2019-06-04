package com.chenhaiyang.plugin.mybatis.sensitive.annotation;

import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;

import java.lang.annotation.*;

/**
 * json字段中需要脱敏的key字段以及key脱敏类型
 * @author chenhaiyang
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SensitiveJSONFieldKey {
    /**
     * json中的key的类型
     * @return key
     */
    String key();
    /**
     * 脱敏类型
     * 不同的脱敏类型置换*的方式不同
     * @return SensitiveType
     */
    SensitiveType type();
}
