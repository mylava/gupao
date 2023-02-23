package com.gupao.edu.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 枚举转换器
 */
public class StringToEnumConverter implements ConverterFactory<String, IEnum> {

    @Override
    public <T extends IEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnum<>(targetType);
    }


    private class StringToEnum<T extends IEnum> implements Converter<String, T> {

        private final T[] values;

        public StringToEnum(Class<T> targetType) {
            values = targetType.getEnumConstants();
        }

        @Override
        public T convert(String source) {
            for (T t : values) {
                if (t.getCode() == Integer.valueOf(source)) {
                    return t;
                }
            }
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
    }
}
