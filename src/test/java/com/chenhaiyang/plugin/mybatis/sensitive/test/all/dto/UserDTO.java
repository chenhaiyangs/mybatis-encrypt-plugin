package com.chenhaiyang.plugin.mybatis.sensitive.test.all.dto;

import com.chenhaiyang.plugin.mybatis.sensitive.annotation.*;
import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;
import lombok.Data;

@SensitiveEncryptEnabled
@Data
public class UserDTO {

    private static final int vid=33;

    private Integer id;
    /**
     * 用户名
     */
    @EncryptField
    private String userName;
    /**
     * 脱敏的用户名
     */
    @SensitiveField(SensitiveType.CHINESE_NAME)
    private String userNameSensitive;
    /**
     * 值的赋值不从数据库取，而是从userName字段获得。
     */
    @SensitiveBinded(bindField = "userName",value = SensitiveType.CHINESE_NAME)
    private String userNameOnlyDTO;
    /**
     * 身份证号
     */
    @EncryptField
    private String idcard;
    /**
     * 脱敏的身份证号
     */
    @SensitiveField(SensitiveType.ID_CARD)
    private String idcardSensitive;
    /**
     * 一个json串，需要脱敏
     * SensitiveJSONField标记json中需要脱敏的字段
     */
    @SensitiveJSONField(sensitivelist = {
            @SensitiveJSONFieldKey(key = "idcard",type = SensitiveType.ID_CARD),
            @SensitiveJSONFieldKey(key = "username",type = SensitiveType.CHINESE_NAME),
    })
    private String jsonStr;

    private int age;

    @SensitiveField(SensitiveType.EMAIL)
    private String email;
}
