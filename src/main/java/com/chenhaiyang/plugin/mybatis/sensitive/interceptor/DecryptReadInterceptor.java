package com.chenhaiyang.plugin.mybatis.sensitive.interceptor;

import com.chenhaiyang.plugin.mybatis.sensitive.Encrypt;
import com.chenhaiyang.plugin.mybatis.sensitive.annotation.EncryptField;
import com.chenhaiyang.plugin.mybatis.sensitive.annotation.SensitiveBinded;
import com.chenhaiyang.plugin.mybatis.sensitive.annotation.SensitiveEncryptEnabled;
import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;
import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveTypeRegisty;
import com.chenhaiyang.plugin.mybatis.sensitive.utils.PluginUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.util.*;


/**
 * 对响应结果进行拦截处理,对需要解密的字段进行解密
 * SQL样例：
 *  1. UPDATE tbl SET x=?, y =
 *  @author ;
 */
@Intercepts({
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {java.sql.Statement.class})
})
@Slf4j
public class DecryptReadInterceptor implements Interceptor {

    private static final String MAPPED_STATEMENT="mappedStatement";

    private Encrypt encrypt;
    public DecryptReadInterceptor(Encrypt encrypt) {
        Objects.requireNonNull(encrypt,"encrypt should not be null!");
        this.encrypt = encrypt;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final List<Object> results = (List<Object>)invocation.proceed();

        if (results.isEmpty()) {
            return results;
        }

        final ResultSetHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        final MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
        final MappedStatement mappedStatement = (MappedStatement)metaObject.getValue(MAPPED_STATEMENT);
        final ResultMap resultMap = mappedStatement.getResultMaps().isEmpty() ? null : mappedStatement.getResultMaps().get(0);

        Object result0 = results.get(0);
        SensitiveEncryptEnabled sensitiveEncryptEnabled = result0.getClass().getAnnotation(SensitiveEncryptEnabled.class);
        if(sensitiveEncryptEnabled == null || !sensitiveEncryptEnabled.value()){
            return results;
        }

        final Map<String, EncryptField> sensitiveFieldMap = getSensitiveByResultMap(resultMap);
        final Map<String, SensitiveBinded> sensitiveBindedMap = getSensitiveBindedByResultMap(resultMap);

        if (sensitiveBindedMap.isEmpty() && sensitiveFieldMap.isEmpty()) {
            return results;
        }

        for (Object obj: results) {
            final MetaObject objMetaObject = mappedStatement.getConfiguration().newMetaObject(obj);
            for (Map.Entry<String, EncryptField> entry : sensitiveFieldMap.entrySet()) {
                String property = entry.getKey();
                String value = (String) objMetaObject.getValue(property);
                if (value != null) {
                    String decryptValue = encrypt.decrypt(value);
                    objMetaObject.setValue(property, decryptValue);
                }
            }
            for (Map.Entry<String, SensitiveBinded> entry : sensitiveBindedMap.entrySet()) {

                String property = entry.getKey();

                SensitiveBinded sensitiveBinded = entry.getValue();
                String bindPropety = sensitiveBinded.bindField();
                SensitiveType sensitiveType = sensitiveBinded.value();
                try {
                    String value = (String) objMetaObject.getValue(bindPropety);
                    String resultValue =  SensitiveTypeRegisty.get(sensitiveType).handle(value);
                    objMetaObject.setValue(property,resultValue);
                }catch (Exception e){
                    //ignore it;
                }
            }
        }

        return results;
    }

    private Map<String,SensitiveBinded> getSensitiveBindedByResultMap(ResultMap resultMap) {
        if (resultMap == null) {
            return new HashMap<>(16);
        }
        Map<String, SensitiveBinded> sensitiveBindedMap = new HashMap<>(16);
        Class<?> clazz = resultMap.getType();
        List<Field> fields = PluginUtils.getFields(clazz);
        for (Field field : fields) {
            SensitiveBinded sensitiveBinded = field.getAnnotation(SensitiveBinded.class);
            if (sensitiveBinded != null) {
                sensitiveBindedMap.put(field.getName(), sensitiveBinded);
            }
        }
        return sensitiveBindedMap;
    }

    private Map<String, EncryptField> getSensitiveByResultMap(ResultMap resultMap) {
        if (resultMap == null) {
            return new HashMap<>(16);
        }

        return getSensitiveByType(resultMap.getType());
    }

    private Map<String, EncryptField> getSensitiveByType(Class<?> clazz) {
        Map<String, EncryptField> sensitiveFieldMap = new HashMap<>(16);
        List<Field> fields = PluginUtils.getFields(clazz);
        for (Field field : fields) {
            EncryptField sensitiveField = field.getAnnotation(EncryptField.class);
            if (sensitiveField != null) {
                sensitiveFieldMap.put(field.getName(), sensitiveField);
            }
        }
        return sensitiveFieldMap;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        // ignore
    }
}
