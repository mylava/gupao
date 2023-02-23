package com.gupao.edu.account.server.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 27/03/2020
 */
@Slf4j
@Component
public class KafkaConsumerListener {
    @KafkaListener(topics = "testTopic")
    public void onMessage(String message) {
        log.info("received message: topic=[testTopic],data=[{}]", message);
    }
}
