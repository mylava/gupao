package com.gupao.edu.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 枚举转换器
 */
public class IntegerToEnumConverter implements ConverterFactory<Integer, IEnum> {

    @Override
    public <T extends IEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new IntegerToEnum(targetType);
    }


    private class IntegerToEnum<T extends IEnum> implements Converter<Integer, T> {

        private final T[] values;

        public IntegerToEnum(Class<T> targetType) {
            values = targetType.getEnumConstants();
        }

        @Override
        public T convert(Integer source) {
            for (T t : values) {
                if (t.getCode() == source) {
                    return t;
                }
            }
            throw new IllegalArgumentException("无法匹配对应的枚举类型");
        }
    }
}
