package com.gupao.edu.account.client.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 2020/4/24
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteListReq extends BasePageReq {

    @ApiModelProperty(value = "查询列表类型：1课程 2问答 3文章", required = true)
    private Integer listType;
}
