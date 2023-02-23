package com.gupao.test;

import com.gupao.edu.account.client.facade.member.UserAuthFacade;
import com.gupao.edu.account.client.resp.UserSimpleInfoDTO;
import com.gupao.edu.course.server.CourseApplication;
import com.gupao.edu.lotus.client.facade.lotus.Payfacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h3>app-backend</h3>
 * <p>UserSimpleInfoDTO feign 接口测试</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-04 19:41
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CourseApplication.class})
public class UserSimpleInfoDTOTest {

    @Autowired
    private UserAuthFacade userInfoFacade;

//    @Autowired
//    private Payfacade payfacade;

    @Test
    public void test() {
        UserSimpleInfoDTO userSimpleInfoDTO = userInfoFacade.getUserSimpleInfo("03bceedef2d5481488c886fee2541a25");
        System.out.println(userSimpleInfoDTO);
    }

    @Test
    public void test02() {
//        payfacade.pay();
    }
}
