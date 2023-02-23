package com.gupao.edu.course.client.req.center;

import com.gupao.edu.course.client.req.BasePageParams;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/21 11:42
 * 获取答疑列表 --与我的答疑
 */
@Data
public class UserAskReq extends BasePageParams {
    /**
     * 视频id
     */
    @ApiModelProperty(name = "videoId", value = "视频id")
    private Integer videoId;
    /**
     * 类型  0 全部答疑列表  1 我的答疑列表
     */
    @ApiModelProperty(name = "type", value = "类型（0代表问答列表，1代表我的问答）",example = "0 或者 1")
    private Integer type;

    /**
     * 用户唯一编码
     */
    @ApiModelProperty(value = "用户唯一编码", name = "userUniqueCode")
    private String userUniqueCode;
}
