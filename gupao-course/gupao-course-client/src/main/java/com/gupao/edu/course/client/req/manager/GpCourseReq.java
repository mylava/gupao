package com.gupao.edu.course.client.req.manager;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gupao.edu.course.client.entity.CourseOutline;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * <h3>gupao-parent</h3>
 * <p>GpCourseReq 创建 课程req</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-03-24 10:56
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel("课程请求对象")
public class GpCourseReq{

    @TableId(value = "course_id", type = IdType.AUTO)
//    @ApiModelProperty(value = "课程Id", name = "courseId", position = 1)
    private Integer courseId;

    /**
     * 课程类型id（训练营、精品小课、vip课）
     */
    private Integer typeId;

    /**
     * 所属科目： JAVA  大数据
     */
    private Integer subjectId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程主题名称
     */
    private String courseTitle;

    /**
     * 课程封面图片
     */
    private String image;

    /**
     * 现价，有效价格
     */
    private BigDecimal nowPrice;

    /**
     * 原价，无效价格
     */
    private BigDecimal oldPrice;
    /**
     * 观看人数
     */
    private Integer viewNum;

    /**
     * 已学人数
     */
    private Integer studyNum;

    /**
     * 评论人数
     */
    private Integer commentNum;

    /**
     * 关注人数
     */
    private Integer attentionNum;

    /**
     * 好评数
     */
    private Integer goodNum;

    /**
     * 好评度
     */
    private BigDecimal goodPraise;

    /**
     * 老师的userId，多个老师用-隔开
     */
    @TableField("teacherUserIdList")
    private String teacherUserIdList;

    /**
     * 图文详情，富文本应编码存储
     */
    private String imageText;

    /**
     * 0未删除，1已删除
     */
    private Boolean isdelete;

    /**
     * 课程大纲
     */
    List<CourseOutline> courseOutlines;

}
