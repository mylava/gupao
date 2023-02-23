package com.gupao.edu.course.client.facade;

import com.gupao.edu.course.client.dto.CourseDTO;
import com.gupao.edu.course.client.dto.LecturerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <h3>app-backend</h3>
 * <p>课程 feign接口 门面</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-11 20:50
 **/
@FeignClient(name = "gupao-course", url = "localhost:18031")
public interface CourseFeignFacade {


    /**
     * 根据讲师 id 查询详情
     * 按需返回信息
     * @param id
     * @return
     */
    @GetMapping("/coursecenter/getLecturerDTO")
    LecturerDTO getLecturerDTO(@RequestParam("id") Integer id);

    /**
     * 根据课程 id 查询详情
     * 按需返回信息
     * @param courseId
     * @return
     */
    @GetMapping("/coursecenter/getCourseDTO")
    CourseDTO getCourseDTO(@RequestParam("courseId") Integer courseId);

}
