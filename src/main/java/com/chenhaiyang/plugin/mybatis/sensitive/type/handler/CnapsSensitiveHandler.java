package com.chenhaiyang.plugin.mybatis.sensitive.type.handler;

import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;
import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveTypeHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * 公司开户银行联号
 * 前四位明文，后面脱敏
 * @author chenhaiyang
 */
public class CnapsSensitiveHandler implements SensitiveTypeHandler {
    @Override
    public SensitiveType getSensitiveType() {
        return SensitiveType.CNAPS_CODE;
    }

    @Override
    public String handle(Object src) {
        if(src==null){
            return null;

        }
        String snapCard =src.toString();

        return StringUtils.rightPad(StringUtils.left(snapCard, 4), StringUtils.length(snapCard), "*");
    }
}
