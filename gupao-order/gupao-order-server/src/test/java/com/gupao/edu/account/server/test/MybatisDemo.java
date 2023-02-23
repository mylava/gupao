package com.gupao.edu.account.server.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.common.cache.CacheUtil;
import com.gupao.edu.common.cache.key.LockPrefix;
import com.gupao.edu.order.client.entity.Orders;
import com.gupao.edu.order.server.OrderApplication;
import com.gupao.edu.order.server.mapper.OrdersMapper;
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
@SpringBootTest(classes = {OrderApplication.class, MybatisDemo.class})
@TestPropertySource(locations = "classpath:bootstrap.yml")
public class MybatisDemo {

    @Autowired

    OrdersMapper gpOrdersMapper;

    /**
     * 测试分页
     */
    @Test
    public void testPagenation() {

        //redis的使用
        CacheUtil.set(LockPrefix.LOCK_TEST,"key","");
        //分页条件 当前页数为1，每页数量为2
        Page< Orders > page = new Page<>(1,2);
        //查询条件
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();

        Orders orders = new Orders();
        orders.setId(123);
        wrapper.setEntity(orders);
//        IPage<GpUser> gpUserIPage = gpUserMapper.selectPage(page, null);
        IPage<Orders> gpUserIPage = gpOrdersMapper.selectPage(page, wrapper);

        log.info("total = {}, recored={}", gpUserIPage.getTotal(),gpUserIPage.getRecords());
    }

}