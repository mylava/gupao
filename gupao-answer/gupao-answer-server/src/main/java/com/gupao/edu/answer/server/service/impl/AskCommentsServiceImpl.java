package com.gupao.edu.answer.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.answer.client.enumeration.OpenAreaType;
import com.gupao.edu.answer.client.exception.BaseRuntimeException;
import com.gupao.edu.answer.client.exception.ErrorCode;
import com.gupao.edu.answer.server.dao.AskCommentsMapper;
import com.gupao.edu.answer.server.entity.AskArticles;
import com.gupao.edu.answer.server.entity.AskComments;
import com.gupao.edu.answer.server.entity.OperationType;
import com.gupao.edu.answer.server.entity.req.CommentsQueryReplyReq;
import com.gupao.edu.answer.server.entity.req.CommentsReq;
import com.gupao.edu.answer.server.enums.SourceType;
import com.gupao.edu.answer.server.service.AskArticlesService;
import com.gupao.edu.answer.server.service.AskCommentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author cxp
 * @since 2020-03-22
 */
@Service
public class AskCommentsServiceImpl extends ServiceImpl<AskCommentsMapper, AskComments> implements AskCommentsService {
    @Autowired
    private AskArticlesService askArticlesService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AskComments queryById(Integer id) {
        return baseMapper.selectById(id);
    }



    @Override
    public IPage<AskComments> queryListBySourceId(CommentsReq commentsReq) {
        commentsReq.formatePage();
        IPage<AskComments> page = new Page<>(commentsReq.getPageNum(), commentsReq.getPageSize());
        QueryWrapper<AskComments> wrapper = new QueryWrapper<>();
        wrapper.eq("source_id",commentsReq.getSourceId());
        wrapper.eq("source_type",commentsReq.getSourceType());
        if("default".equalsIgnoreCase(commentsReq.getOrderBy())){
            wrapper.orderByDesc("supports","created_at");
        }else if ("new".equalsIgnoreCase(commentsReq.getOrderBy())){
            wrapper.orderByDesc("created_at");
        }else {
            wrapper.orderByDesc("id");
        }
        return baseMapper.selectPage(page,wrapper);
    }


    @Override
    public void addComments(AskComments commentsForm)  {
        //登录设备
        if(StringUtils.isEmpty(commentsForm.getDevice())){
            commentsForm.setDevice(Byte.valueOf("1"));
        }
        //查询发布评论是否需要审核
        //commentsForm.setStatus(settingsService.getVerifyStatus(SettingsType.VerifySettingType.VERIFY_COMMENT,false));
        byte status =0;
        commentsForm.setStatus(status);

        baseMapper.insert(commentsForm);
        updateAddCommentsSourceType(commentsForm);  //修改新增评论对象  发送评论通知
    }

    private void updateAddCommentsSourceType(AskComments commentsForm) {

        SourceType sourceType = SourceType.getSourceTypeByCode(commentsForm.getSourceType());
        if(sourceType == null){
            return;
        }
        switch (sourceType) {
            case SOURCETYPE_QUESTION:
                //新增评论问题通知
                //addCommentsQuestions(comments);
                break;
            case SOURCETYPE_ANSWER:
                //新增评论回答通知
                //addCommentsAnswers(comments);
                break;
            case SOURCETYPE_ARTICLE:
                //新增评论文章通知
                addCommentsArticles(commentsForm);
                break;

            default:
                break;
        }
    }
    private void addCommentsArticles(AskComments comments)  {
        AskArticles articlesDTO = askArticlesService.queryById(comments.getSourceId());
        if(articlesDTO == null){
            throw new BaseRuntimeException(ErrorCode.PARAMETER_ERROR.getCode(),"操作错误：没有找到评论对象记录");
        }
        askArticlesService.updateArticlesInfo(comments.getSourceId(),null,null, OperationType.comments);
      /*  //TODO 判断用户是否接收通知
        boolean flag = usersService.checkSiteNotifications(articlesDTO.getUserId(), NotificationsType.COMMENT_ARTICLE);
        if(flag && !comments.getUserId().equals(articlesDTO.getUserId())){
            notificationsService.addNotifications(comments.getUserId(),articlesDTO.getUserId(),NotificationsType.COMMENT_ARTICLE,articlesDTO.getId(),articlesDTO.getTitle(),comments.getContent(),0, ReferType.ARTICLE);
        }
        //新增用户评论文章任务
        newbieTaskService.addCompleteNewbieTask(comments.getUserId(), NewbieTaskType.COMMENTS_ARTICLES,comments.getSourceId(),articlesDTO.getTitle());
*/    }
    @Override
    public void addReplyComments(AskComments askComments) {
        askComments.setStatus(Byte.valueOf("0"));
        addComments(askComments);
    }

    @Override
    //查询回复信息
    public IPage<AskComments> pageQueryByCommentsReply(CommentsQueryReplyReq commentsForm) {
        //判断查询当前评论文章是否对非vip开放
        AskComments askComments = new AskComments();
        BeanUtils.copyProperties(commentsForm,askComments);
        if(!StringUtils.isEmpty(commentsForm.getSourceId())){
            AskArticles articlesDTO = askArticlesService.queryById(commentsForm.getSourceId());
            if(articlesDTO != null && OpenAreaType.VIP.getCode().equals(articlesDTO.getOpenArea()) && !articlesDTO.getUserId().equals(commentsForm.getUserId())
                /* TODO 验证 是否vip && !vipUsersService.checkUserVip(userId)*/){
                return new Page<>();
            }
        }
        commentsForm.formatePage();
        Page<AskComments> page = new Page<>(commentsForm.getPageNum(),commentsForm.getPageSize());

        QueryWrapper<AskComments> wrapper = new QueryWrapper<>();
        wrapper.eq("source_id",commentsForm.getSourceId());
        wrapper.eq("source_type",commentsForm.getSourceType());
        wrapper.eq("user_id",commentsForm.getUserId());
        IPage<AskComments> askCommentsIPage = baseMapper.selectPage(page, wrapper);
        List<AskComments> records = askCommentsIPage.getRecords();
        if(records != null && records.size()>0){
            for(AskComments commentsDTO : records){
                getCommentsDTO(commentsDTO);
                getReplyCommentInfo(commentsDTO,commentsForm.getUserId(),"reply");
            }
        }
        return askCommentsIPage;
    }

    private void getReplyCommentInfo(AskComments commentsDTO, Integer userId, String reply) {
        AskComments replyCommentsForm = new AskComments();
        replyCommentsForm.setSourceType(SourceType.SOURCETYPE_COMMENT.getCode());
        replyCommentsForm.setSourceId(commentsDTO.getId());
        replyCommentsForm.setOrderType("default");
        List<AskComments> replyList = baseMapper.queryAll(replyCommentsForm);
        if("reply".equals(reply)){
            List<AskComments> resultCommentsList = new ArrayList<>();
            for(int i = 0;i < replyCommentsForm.getPageSize() && i < replyList.size();i++){
                AskComments replyCommentDTO = replyList.get(i);
                this.getCommentsDTO(replyCommentDTO);
                resultCommentsList.add(replyCommentDTO);
            }
            commentsDTO.setAskComments(resultCommentsList);
        }
        commentsDTO.setTotal(replyList.size());
    }
    //匹配用户信息 TODO
    public AskComments getCommentsDTO(AskComments commentsDTO) {
       /* UserDTO userDTO = usersService.getBaseInfo(commentsDTO.getUserId());
        commentsDTO.setAvatarUrl(userDTO.getAvatarUrl());
        commentsDTO.setUserName(userDTO.getName());
        commentsDTO.setVipSign(userDTO.isVipSign());
        if(!StringUtils.isEmpty(commentsDTO.getToUserId())){
            userDTO = usersService.getBaseInfo(commentsDTO.getToUserId());
            commentsDTO.setToUserName(userDTO.getName());
        }*/
        return commentsDTO;
    }
}
