package com.chenhaiyang.plugin.mybatis.sensitive.type.handler;

import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;
import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveTypeHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * 姓名脱敏的解析类
 * 中文姓名只显示第一个汉字，其他隐藏为2个星号
 * 例子：李**
 * 张三丰 ：张**
 * @author ;
 */
public class NameSensitiveHandler implements SensitiveTypeHandler {
    @Override
    public SensitiveType getSensitiveType() {
        return SensitiveType.CHINESE_NAME;
    }

    @Override
    public String handle(Object src) {
        if (src==null) {
            return "";
        }
        String fullName = src.toString();
        String name = StringUtils.left(fullName, 1);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }
}
