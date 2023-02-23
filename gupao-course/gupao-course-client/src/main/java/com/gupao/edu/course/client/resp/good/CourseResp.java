package com.gupao.edu.course.client.resp.good;
/**
 * Created by dudu on 2020/3/22.
 */

import com.gupao.edu.course.client.resp.TeacherTeam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <h3>gupao-parent</h3>
 * <p>课程 主表 信息 查询  管理后台 bean</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-03-22 21:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("课程信息")
public class CourseResp {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "视频提问id", name = "id",  position = 1)
    private Integer id;

    /**
     * 课程分类id（训练营、精品小课、vip课，对应字典表）
     */
    @ApiModelProperty(value = "课程分类id（训练营、精品小课、vip课，对应字典表）", name = "categoryId",  position = 2)
    private Integer categoryId;

    /**
     * 所属学科id:(JAVA、大数据,对应字典表)
     */
    @ApiModelProperty(value = "所属学科id:(JAVA、大数据,对应字典表)", name = "gradeId",  position = 3)
    private Integer gradeId;

    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称", name = "courseName",  position = 4)
    private String courseName;

    /**
     * 课程主题名称
     */
    @ApiModelProperty(value = "课程主题名称", name = "courseTitle",  position = 5)
    private String courseTitle;

    /**
     * 课程封面图片
     */
    @ApiModelProperty(value = "课程封面图片", name = "imageUrl",  position = 6)
    private String imageUrl;

    /**
     * 观看人数
     */
    @ApiModelProperty(value = "观看人数", name = "viewNum",  position = 7)
    private Integer viewNum;

    /**
     * 已学人数
     */
    @ApiModelProperty(value = "已学人数", name = "studyNum",  position = 8)
    private Integer studyNum;

    /**
     * 评论人数
     */
    @ApiModelProperty(value = "评论人数", name = "commentNum",  position = 9)
    private Integer commentNum;

    /**
     * 关注人数
     */
    @ApiModelProperty(value = "关注人数", name = "attentionNum",  position = 10)
    private Integer attentionNum;

    /**
     * 好评数
     */
    @ApiModelProperty(value = "好评数", name = "praiseNum",  position = 11)
    private Integer praiseNum;

    /**
     * 课程视频总时长(分)
     */
    @ApiModelProperty(value = "课程视频总时长(分)", name = "totalMinutes",  position = 12)
    private Integer totalMinutes;

    /**
     * 好评度
     */
    @ApiModelProperty(value = "好评度", name = "praiseScore",  position = 13)
    private Float praiseScore;

    /**
     * 平均评分（课程的）
     */
    @ApiModelProperty(value = "平均评分", name = "averageScore",  position = 14)
    private Float averageScore;

    /**
     * 答疑数， 维护在课程表中,方便查询
     */
    @ApiModelProperty(value = "答疑数， 维护在课程表中,方便查询", name = "replyCount",  position = 15)
    private Integer replyCount;

    /**
     * 现价，有效价格
     */
    @ApiModelProperty(value = "现价，有效价格", name = "cost",  position = 16)
    private Integer cost;

    /**
     * 原价，无效价格
     */
    @ApiModelProperty(value = "原价，无效价格", name = "price",  position = 17)
    private Integer price;

    /**
     * 老师的Id，多个老师用json {"1":"10001","2"...}
     */
    @ApiModelProperty(value = "老师的Id，多个老师用json {\"1\":\"10001\",\"2\"...}", name = "lecturerIds",  position = 18)
    private String lecturerIds;

    /**
     * 图文详情，富文本应编码存储
     */
    @ApiModelProperty(value = "图文详情，富文本应编码存储", name = "description",  position = 19)
    private String description;


    /**
     * 课程的开播时间
     */
    @ApiModelProperty(value = "课程的开播时间", name = "startTime",  position = 20)
    private LocalDateTime startTime;

    /**
     * 课程的结束时间
     */
    @ApiModelProperty(value = "课程的结束时间", name = "endTime",  position = 21)
    private LocalDateTime endTime;


    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime",  position = 22)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updateTime",  position = 23)
    private LocalDateTime updateTime;

    //******************新增老师集合具体信息
    @ApiModelProperty(value = "讲师集合", name = "teacherTeams",  position = 24)
    private List<TeacherTeam> teacherTeams;

}
