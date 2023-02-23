package com.gupao.test;

import com.gupao.edu.course.server.CourseApplication;
import com.gupao.edu.course.server.service.UserCourseProgressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <h3>app-backend</h3>
 * <p>UserCourseProgressService test</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-04 18:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CourseApplication.class})
public class UserCourseProgressServiceTest {

    @Autowired
    private UserCourseProgressService userCourseProgressService;

    @Test
    public void testComputerTotalStudyTime() {
        Long u1 = userCourseProgressService.computerTotalStudyTime("03bceedef2d5481488c886fee2541a25", false);
        System.out.println(u1);
        Long u2 = userCourseProgressService.computerTotalStudyTime("03bceedef2d5481488c886fee2541a25", true);
        System.out.println(u2);

    }

}
