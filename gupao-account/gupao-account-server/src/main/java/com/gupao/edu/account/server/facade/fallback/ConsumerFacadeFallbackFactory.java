package com.gupao.edu.account.server.facade.fallback;

import com.gupao.edu.account.server.facade.ConsumerFacade;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 15/03/2020
 */
@Slf4j
@Component
public class ConsumerFacadeFallbackFactory implements FallbackFactory<ConsumerFacade>{
    @Override
    public ConsumerFacade create(Throwable cause) {
        log.error("remote call error: {}",cause);
        return new ConsumerFacadeFallback();
    }
}
