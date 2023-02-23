package com.gupao.edu.course.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.course.client.entity.CourseVideoQuestionReply;
import com.gupao.edu.course.client.resp.center.CourseVedioReplyResp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 视频答疑回复表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface CourseVideoQuestionReplyMapper extends BaseMapper<CourseVideoQuestionReply> {

    List<CourseVedioReplyResp> findReplyRespListByQuestionId(
        IPage<CourseVedioReplyResp> page,
        @Param("questionId") Integer questionId);
}
