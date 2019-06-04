package com.chenhaiyang.plugin.mybatis.sensitive.type.handler;

import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;
import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveTypeHandler;

/**
 * 不脱敏
 * @author chenhaiyang
 */
public class NoneSensitiveHandler implements SensitiveTypeHandler {
    @Override
    public SensitiveType getSensitiveType() {
        return SensitiveType.NONE;
    }

    @Override
    public String handle(Object src) {
        if(src!=null){
            return src.toString();
        }
        return null;
    }
}
