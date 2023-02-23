package com.gupao.edu.account.server.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.server.AccountApplication;
import com.gupao.edu.account.server.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 21/03/2020
 */
@Slf4j
@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountApplication.class, MybatisDemo.class})
@TestPropertySource(locations = "classpath:bootstrap.yml")
public class MybatisDemo {

    @Autowired
    UserMapper gpUserMapper;

    /**
     * 测试分页
     */
    @Test
    public void testPagenation() {
        //分页条件 当前页数为1，每页数量为2
        Page<User> page = new Page<>(1,2);
        //查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        User user = new User();
        user.setUserName("test");
        wrapper.setEntity(user);
//        IPage<GpUser> gpUserIPage = gpUserMapper.selectPage(page, null);
        IPage<User> gpUserIPage = gpUserMapper.selectPage(page, wrapper);

        log.info("total = {}, recored={}", gpUserIPage.getTotal(),gpUserIPage.getRecords());
    }

}