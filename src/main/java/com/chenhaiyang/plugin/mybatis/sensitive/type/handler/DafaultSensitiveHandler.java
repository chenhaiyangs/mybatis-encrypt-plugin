package com.chenhaiyang.plugin.mybatis.sensitive.type.handler;

import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveType;
import com.chenhaiyang.plugin.mybatis.sensitive.type.SensitiveTypeHandler;

/**
 * 默认脱敏处理类
 * @author chenhaiyang
 */
public class DafaultSensitiveHandler implements SensitiveTypeHandler {

    private static final int SIZE = 6;
    private static final int TWO =2;
    private static final String SYMBOL = "*";

    @Override
    public SensitiveType getSensitiveType() {
        return SensitiveType.DEFAUL;
    }

    @Override
    public String handle(Object src) {
        if (null == src || "".equals(src)) {
            return null;
        }
        String value =src.toString();

        int len = value.length();
        int pamaone = len / TWO;
        int pamatwo = pamaone - 1;
        int pamathree = len % TWO;
        StringBuilder stringBuilder = new StringBuilder();
        if (len <= TWO) {
            if (pamathree == 1) {
                return SYMBOL;
            }
            stringBuilder.append(SYMBOL);
            stringBuilder.append(value.charAt(len - 1));
        } else {
            if (pamatwo <= 0) {
                stringBuilder.append(value.substring(0, 1));
                stringBuilder.append(SYMBOL);
                stringBuilder.append(value.substring(len - 1, len));

            } else if (pamatwo >= SIZE / TWO && SIZE + 1 != len) {
                int pamafive = (len - SIZE) / 2;
                stringBuilder.append(value.substring(0, pamafive));
                for (int i = 0; i < SIZE; i++) {
                    stringBuilder.append(SYMBOL);
                }
                if (ispamaThree(pamathree)) {
                    stringBuilder.append(value.substring(len - pamafive, len));
                } else {
                    stringBuilder.append(value.substring(len - (pamafive + 1), len));
                }
            } else {
                int pamafour = len - 2;
                stringBuilder.append(value.substring(0, 1));
                for (int i = 0; i < pamafour; i++) {
                    stringBuilder.append(SYMBOL);
                }
                stringBuilder.append(value.substring(len - 1, len));
            }
        }
        return stringBuilder.toString();
    }

    @SuppressWarnings("all")
    private boolean ispamaThree(int pamathree){
        return (pamathree == 0 && SIZE / 2 == 0) || (pamathree != 0 && SIZE % 2 != 0);
    }
}
