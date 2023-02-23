package com.gupao.edu.course.client.req.good;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/5/4 18:06
 * 字典表查询
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictListReq {
    /**
     * 分类编码
     */
    @ApiModelProperty(value = "分类编码", name = "category_code")
    private String category_code;
    /**
     * 分类ID
     */
    @ApiModelProperty(value = "分类id", name = "category_id")
    private Integer category_id;

}
