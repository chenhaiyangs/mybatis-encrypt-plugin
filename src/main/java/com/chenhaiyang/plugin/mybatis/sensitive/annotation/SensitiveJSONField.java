package com.chenhaiyang.plugin.mybatis.sensitive.annotation;

import java.lang.annotation.*;

/**
 * 对json内的key_value进行脱敏
 * @author chenhaiyang
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SensitiveJSONField {
    /**
     * 需要脱敏的字段的数组
     * @return 返回结果
     */
    SensitiveJSONFieldKey[] sensitivelist();
}
