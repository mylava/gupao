package com.gupao.edu.account.server.facade.fallback;

import com.gupao.edu.account.server.facade.ConsumerFacade;
import org.springframework.stereotype.Component;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 15/03/2020
 */
@Component
public class ConsumerFacadeFallback implements ConsumerFacade {
    @Override
    public String getRemote() {
        return "getRemote fallback ...";
    }

    @Override
    public String fallbackDemo() {
        return "fallbackDemo fallback ...";
    }
}
