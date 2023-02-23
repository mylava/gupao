package com.gupao.edu.common.fallback;

import feign.Target;
import feign.hystrix.FallbackFactory;
import lombok.AllArgsConstructor;
import org.springframework.cglib.proxy.Enhancer;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 15/03/2020
 */
@AllArgsConstructor
public class CommonFeignFallbackFactory<T> implements FallbackFactory<T> {
    private final Target<T> target;

    @Override
    @SuppressWarnings("unchecked")
    public T create(Throwable cause) {
        final Class<T> targetType = target.type();
        final String targetName = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetType);
        enhancer.setUseCache(true);
        enhancer.setCallback(new CommonFeignFallback<>(targetType, targetName, cause));
        return (T) enhancer.create();
    }

}
