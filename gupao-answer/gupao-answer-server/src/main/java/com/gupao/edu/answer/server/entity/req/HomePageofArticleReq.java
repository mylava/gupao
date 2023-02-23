package com.gupao.edu.answer.server.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("文章首页")
public class HomePageofArticleReq  {
    @ApiModelProperty("标签类型id。- 分割")
    private  String tagIds;
    @ApiModelProperty(value = "排序规则",allowableValues = "0 综合排序 ；1 最新优先； 2 最热优先")
    private String orderBy;
    @ApiModelProperty("页码")
    private int pageNum;
    @ApiModelProperty("页面展示长度")
    private int pageSize;
    @ApiModelProperty("起始行数")
    private int startNum =0;
    //查询时使用。请求参数
    private List<Integer> tagIdList;

    public void formatePage() {
        pageNum = pageNum>0?pageNum:1;
        pageSize = pageSize>0?pageSize:10;
        startNum = (pageNum-1) * pageSize;
    }
    public void requestCheck() {

    }
}
