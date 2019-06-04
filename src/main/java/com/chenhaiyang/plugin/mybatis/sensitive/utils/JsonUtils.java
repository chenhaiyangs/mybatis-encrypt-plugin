package com.chenhaiyang.plugin.mybatis.sensitive.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * json工具类
 * @author chenhaiyang
 */
public class JsonUtils {
    /**
     * 将json字符串转化为StringObject类型的map
     * @param jsonStr json字符串
     * @return map
     */
    public static Map<String,Object> parseToObjectMap(String jsonStr) {
        return JSON.parseObject(jsonStr,new TypeReference<LinkedHashMap<String,Object>>(){});
    }

    /**
     * 将map转化为json字符串
     * @param params 参数集合
     * @return json
     */
    public static String parseMaptoJSONString(Map<String,Object> params){
        return JSON.toJSONString(params, SerializerFeature.WriteMapNullValue);
    }

}
