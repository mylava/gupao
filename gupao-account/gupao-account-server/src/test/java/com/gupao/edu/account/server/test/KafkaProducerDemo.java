package com.gupao.edu.account.server.test;

import com.gupao.edu.account.server.AccountApplication;
import com.gupao.edu.common.message.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 27/03/2020
 */
@Slf4j
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApplication.class, MybatisDemo.class})
@ComponentScan(basePackages={"com.gupao.edu.account.server.test"})
@TestPropertySource(locations = "classpath:bootstrap.yml")
public class KafkaProducerDemo {

    /**
     * 测试kafka producer
     */
    @Test
    public void testProducer() throws InterruptedException {
        String message = "hello";
        MessageSender.sendMessage("testTopic",message);
        log.info("send message: topic=[testTopic],data=[{}]", message);
        //等待消息接收
        Thread.sleep(10000);
    }

}
