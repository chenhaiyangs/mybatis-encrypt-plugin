package com.chenhaiyang.plugin.mybatis.sensitive.type.handler;

import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;
import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveTypeHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * 座机信息脱敏
 * 座机的前2位和后4位保留，其余的隐藏。
 * @author chenhaiyang
 */
public class FixedPhoneSensitiveHandler implements SensitiveTypeHandler {
    @Override
    public SensitiveType getSensitiveType() {
        return SensitiveType.FIXED_PHONE;
    }

    @Override
    public String handle(Object src) {
        if(src==null){
            return null;
        }
        String fixedPhone=src.toString();
        return StringUtils.left(fixedPhone, 2).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(fixedPhone, 4), StringUtils.length(fixedPhone), "*"), "***"));
    }
}
