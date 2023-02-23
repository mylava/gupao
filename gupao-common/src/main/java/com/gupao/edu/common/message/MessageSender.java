package com.gupao.edu.common.message;

import com.gupao.edu.common.context.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * comment: 消息发送工具类
 *
 * @author: lipengfei
 * @date: 27/03/2020
 */
@Slf4j
public class MessageSender {
    private static KafkaTemplate<String,String> kafkaTemplate = (KafkaTemplate<String, String>) SpringContextUtil.getApplicationContext().getBean("kafkaTemplate");

    public static void sendMessage(String topic, String data) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", ex, topic, data);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("kafka sendMessage success topic = {}, data = {}",topic, data);
            }
        });
    }
}
