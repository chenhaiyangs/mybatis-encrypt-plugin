package com.chenhaiyang.plugin.mybatis.sensitive.type.handler;

import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;
import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveTypeHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * 收货地址脱敏处理类
 * 地址只显示到地区，不显示详细地址；我们要对个人信息增强保护
 * 例子：北京市海淀区****
 * @author ;
 */
public class AddressSensitiveHandler implements SensitiveTypeHandler {

    private static final int RIGHT=10;
    private static final int LEFT=6;
    @Override
    public SensitiveType getSensitiveType() {
        return SensitiveType.ADDRESS;
    }

    @Override
    public String handle(Object src) {
        if(src==null){
            return null;
        }
        String address = src.toString();
        int length = StringUtils.length(address);
        if(length>RIGHT+LEFT){
            return StringUtils.rightPad(StringUtils.left(address, length-RIGHT), length, "*");
        }
        if(length<=LEFT){
            return address;
        }else{
            return address.substring(0,LEFT+1).concat("*****");
        }

    }
}
