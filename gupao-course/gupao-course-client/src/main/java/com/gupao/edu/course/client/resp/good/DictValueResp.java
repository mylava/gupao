package com.gupao.edu.course.client.resp.good;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/5/4 18:16
 * 字典列表返回实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("字典列表返回实体类")
public class DictValueResp {
    /**
     * 字典数据名称
     */
    @ApiModelProperty(value = "字典数据名称", name = "dictName")
    private String dictName;

    /**
     * 字典数据的值
     */
    @ApiModelProperty(value = "字典数据的值", name = "dictValue")
    private String dictValue;
}
