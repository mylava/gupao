package com.gupao.edu.account.server.test.controller;

import com.gupao.edu.account.server.AccountApplication;
import com.gupao.edu.account.server.test.TestDemo;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/27 10:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApplication.class, TestDemo.class})
@Slf4j
// 单元测试时基于的配置文件
public class CommonControllerTest {
    /*@Autowired
    private CommonService commonService;
    @Test
    public void sendMessage(){
        commonService.sendMessage("13699173133",1);
    }*/
}
