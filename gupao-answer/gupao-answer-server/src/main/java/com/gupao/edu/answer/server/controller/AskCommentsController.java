package com.gupao.edu.answer.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gupao.edu.answer.client.exception.BaseException;
import com.gupao.edu.common.result.Result;
import com.gupao.edu.answer.server.entity.AskComments;
import com.gupao.edu.answer.server.entity.OperationType;
import com.gupao.edu.answer.server.entity.req.CommentsChangeReq;
import com.gupao.edu.answer.server.entity.req.CommentsQueryReplyReq;
import com.gupao.edu.answer.server.entity.req.CommentsReplyReq;
import com.gupao.edu.answer.server.entity.req.CommentsReq;
import com.gupao.edu.answer.server.service.AskCommentsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.gupao.edu.common.result.CodeMessage.VALIDITY_FAILED;

/**
 * 评论表(AskComments)表控制层
 *
 * @author makejava
 * @since 2020-03-18 22:11:17
 */
@Api(value = "评论接口",tags = {"评论相关"})
@RestController
@RequestMapping("askComments")
@Slf4j
public class AskCommentsController {
    /**
     * 服务对象
     */
    @Resource
    private AskCommentsService askCommentsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    @ApiOperation("根据主键查询评论")
    public Result<AskComments> selectOne(Integer id) {
        return Result.success(this.askCommentsService.queryById(id));
    }


    @GetMapping("selectSourceId")
    @ApiOperation("根据SourceId查询评论列表")
    public Result<IPage<AskComments>> selectSourceId(CommentsReq commentsReq) {

        commentsReq.requestCheck();
        return Result.success(this.askCommentsService.queryListBySourceId(commentsReq));
    }


    @PostMapping("addUpdateComments")
    @ApiOperation("新增、修改评论")
    public Result addUpdateComments(CommentsChangeReq commentsReq) {

        AskComments askComments = new AskComments();
        if(OperationType.add.equals(commentsReq.getType())){
            log.info("评论新增 params " + commentsReq);
            BeanUtils.copyProperties(commentsReq,askComments);
            askCommentsService.addComments(askComments);
        }else if(OperationType.update.equals(commentsReq.getType())){
            log.debug("评论修改 params " + commentsReq);
            if(StringUtils.isEmpty(commentsReq.getId())){
                return Result.fail(VALIDITY_FAILED.fillArgs( "修改id不能为空！"));
            }
            BeanUtils.copyProperties(commentsReq,askComments);
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.setEntity(askComments);
            askCommentsService.update(updateWrapper);
        }else{
            return Result.fail(VALIDITY_FAILED.fillArgs( "参数错误！"));
        }
        return Result.success("发布成功");
    }


    @PostMapping("replyComments")
    @ApiOperation("回复评论")
    public Result replyComments(CommentsReplyReq commentsReq) {

        AskComments askComments = new AskComments();

        try {
            commentsReq.requestCheck();
        } catch (Exception e) {
            return Result.fail(VALIDITY_FAILED.fillArgs( e.getMessage()));
        }
        BeanUtils.copyProperties(commentsReq,askComments);
        askCommentsService.addReplyComments(askComments);
        return Result.success("发布成功");
    }


    @ApiOperation(value = "查询文章评论回复数据")
    @GetMapping(value = "pageQueryByCommentsReply")
    public Result<IPage<AskComments>> pageQueryByCommentsReply(CommentsQueryReplyReq commentsForm) throws BaseException {
        if(log.isDebugEnabled()){
            log.debug("查询文章评论回复数据 params " + commentsForm);
        }
        return Result.success(askCommentsService.pageQueryByCommentsReply(commentsForm));
    }
}