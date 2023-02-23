package com.gupao.edu.course.client.req.center;

import com.gupao.edu.course.client.req.BasePageParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * <h3>app-backend</h3>
 * <p>学习中心 请求实体</p>
 *
 * @author : hduong
 * @version : 1.0
 * @date : 2020-05-03 22:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户学习中心请求实体")
public class StudyCenterReq extends BasePageParams {

    @ApiModelProperty(value = "唯一编码", name = "userUniqueCode")
    @NotBlank(message = "用户唯一编码不能为空")
    private String userUniqueCode;
}
