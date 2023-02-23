package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.User;
import com.gupao.edu.account.client.entity.UserInfoDetail;
import com.gupao.edu.account.client.req.UserInfoReq;
import com.gupao.edu.account.client.resp.UserInfoResp;
import com.gupao.edu.account.server.mapper.UserInfoDetailMapper;
import com.gupao.edu.account.server.mapper.UserMapper;
import com.gupao.edu.account.server.service.UserInfoDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * 用户详细信息表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserInfoDetailServiceImpl extends ServiceImpl<UserInfoDetailMapper, UserInfoDetail> implements UserInfoDetailService {

    @Autowired
    private UserInfoDetailMapper userInfoDetailMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 发生异常就进行回滚
     * @param req
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(UserInfoReq req) {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
        lambdaUpdateWrapper.eq(User::getUserUniqueCode, req.getUserUniqueCode());
        if(!StringUtils.isEmpty(req.getNickName())) {
            lambdaUpdateWrapper.set(User::getNickName, req.getNickName());
        }
        if(!StringUtils.isEmpty(req.getAvatar())) {
            lambdaUpdateWrapper.set(User::getAvatar, req.getAvatar());
        }
        lambdaUpdateWrapper.set(User::getUpdateTime, LocalDate.now());

       if(userMapper.update(null, lambdaUpdateWrapper) > 0) {
           DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LambdaUpdateWrapper<UserInfoDetail> userInfoLamda = Wrappers.lambdaUpdate();
           userInfoLamda.eq(UserInfoDetail::getUserUniqueCode, req.getUserUniqueCode());
           if(!StringUtils.isEmpty(req.getRealName())) {
               userInfoLamda.set(UserInfoDetail::getUserName, req.getRealName());
           }
           if(!StringUtils.isEmpty(req.getBirthday())) {
               userInfoLamda.set(UserInfoDetail::getBirthday,  LocalDate.parse(req.getBirthday(), df));
           }
           if(req.getGender() != null) {
               userInfoLamda.set(UserInfoDetail::getGender, req.getGender());
           }
           if(req.getProvince() != null) {
               userInfoLamda.set(UserInfoDetail::getProvinceId, req.getProvince());
           }
           if(req.getCity() != null) {
               userInfoLamda.set(UserInfoDetail::getCityId, req.getCity());
           }
           if(req.getCertificate() != null) {
               userInfoLamda.set(UserInfoDetail::getEducation, req.getCertificate());
           }
           if(req.getWorkAge() != null) {
               userInfoLamda.set(UserInfoDetail::getSeniority,req.getWorkAge());
           }
           if(req.getPosition() != null) {
               userInfoLamda.set(UserInfoDetail::getJobTitle, req.getPosition());
           }
           if(req.getCurrentSalary() != null) {
               userInfoLamda.set(UserInfoDetail::getCurrentSalary,req.getCurrentSalary());
           }
           if(req.getExpectSalary() != null) {
               userInfoLamda.set(UserInfoDetail::getExpectSalary, req.getExpectSalary());
           }
           if(req.getIntroduction() != null) {
               userInfoLamda.set(UserInfoDetail::getIntroduction, req.getIntroduction());
           }
           if(req.getCompany() != null) {
               userInfoLamda.set(UserInfoDetail::getEmployer, req.getCompany());
           }
           return userInfoDetailMapper.update(null, userInfoLamda) > 0 || userMapper.update(null, lambdaUpdateWrapper) > 0;
       } else {
           return false;
       }
    }

    @Override
    public UserInfoResp getUserInfo(String targetUniqueCode) {
        UserInfoResp userInfoResp = userInfoDetailMapper.getUserInfo(targetUniqueCode);
        LocalDate localDate = userInfoResp.getBirthday();
        int age = localDate.until(LocalDate.now()).getYears();
        userInfoResp.setAge(age);
        return userInfoResp;
    }
}
