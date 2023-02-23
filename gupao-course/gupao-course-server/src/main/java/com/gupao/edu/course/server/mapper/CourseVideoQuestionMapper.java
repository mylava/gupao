package com.gupao.edu.course.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.course.client.entity.CourseVideoQuestion;
import com.gupao.edu.course.client.resp.center.CourseQuestionResp;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 视频答疑问题表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseVideoQuestionMapper extends BaseMapper<CourseVideoQuestion> {

    List<CourseQuestionResp> findQuestionPage(IPage<CourseQuestionResp> questionRespPage,
        @Param("videoId") Integer videoId,@Param("userUniqueCode") String userUniqueCode);
}
