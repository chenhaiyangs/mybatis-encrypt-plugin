package com.chenhaiyang.plugin.mybatis.sensitive.utils;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 插件工具类，获取到对象的真实对象，可能存在多层代理
 * @author ;
 */
public final class PluginUtils {

    private PluginUtils() {
        // to do nothing
    }

    /**
     * 获得真正的处理对象,可能多层代理.
     */
    @SuppressWarnings("unchecked")
    public static <T> T realTarget(Object target) {
        if (Proxy.isProxyClass(target.getClass())) {
            MetaObject metaObject = SystemMetaObject.forObject(target);
            return realTarget(metaObject.getValue("h.target"));
        }
        return (T) target;
    }

    /**
     * 获取子类及其父类所有字段属性
     */
    public static List<Field> getFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fields;
    }
}
