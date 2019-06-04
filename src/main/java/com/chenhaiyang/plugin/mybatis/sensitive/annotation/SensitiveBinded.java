package com.chenhaiyang.plugin.mybatis.sensitive.annotation;

import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;

import java.lang.annotation.*;

/**
 * 此注解适用于如下场景：
 * 例如，数据库只存了username字段的加密信息，没有脱敏冗余字段。
 *      我的响应类里希望将数据库的加密的某个字段映射到响应的两个属性上（一个解密的，一个脱敏的）就可以使用该注解。
 * 例如，dto里有如下字段
 *      EncryptField
 *      private String username
 *
 *      SensitiveBinded(bindField = "userName",value = SensitiveType.CHINESE_NAME)
 *      private String userNameOnlyDTO;
 *
 *
 * 则当查询出结果时，userNameOnlyDTO会赋值为username解密后再脱敏的值。
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface SensitiveBinded {
    /**
     * 该属性从哪个字段取得
     * @return 返回字段名
     */
    String bindField();
    /**
     * 脱敏类型
     * 不同的脱敏类型置换*的方式不同
     * @return SensitiveType
     */
    SensitiveType value();

}
