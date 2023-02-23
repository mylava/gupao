package com.gupao.edu.account.server.test;

import com.gupao.edu.account.server.facade.ConsumerFacade;
import com.gupao.edu.account.server.AccountApplication;
import com.gupao.edu.account.server.mapper.UserMapper;
import com.gupao.edu.account.server.utils.ThirdPartyLoginHelper;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 13/03/2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApplication.class, TestDemo.class})
@Slf4j
// 单元测试时基于的配置文件
@TestPropertySource(locations = "classpath:application-test.yml")
public class TestDemo {

    @Autowired
    private ConsumerFacade consumerFacade;
    @Autowired
    private ThirdPartyLoginHelper helper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 使用断言
     */
    @Test
    public void test() {
        log.info("hello test");
        TestCase.assertEquals(1, 1);
    }

    /**
     * 测试调用第三方接口
     */
    @Test
    public void test2() {
        userMapper.selectByUniqueCode("47070bb1be5146e2b94c265fc23832a4");
    }
    /**
     * 测试feign调用
     */
    @Test
    public void testFeign() {
        log.info("consumerFacade.getRemote() = {}", consumerFacade.getRemote());
    }

    @BeforeClass
    public static void beforeClass() {
        log.info("初始化操作 ");
    }

    @Before
    public void testBefore() {
        log.info("before");
    }

    @After
    public void testAfter() {
        log.info("after");
    }

    @AfterClass
    public static void afterClass() {
        log.info("释放资源 ");
    }


}
