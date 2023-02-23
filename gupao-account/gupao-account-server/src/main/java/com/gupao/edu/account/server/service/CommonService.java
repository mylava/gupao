package com.gupao.edu.account.server.service;

import com.gupao.edu.account.client.resp.AreaResp;
import com.gupao.edu.account.client.resp.DictResp;
import com.gupao.edu.common.result.Result;

import java.util.List;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/26 16:33
 * 通用接口
 */
public interface CommonService {
    Result sendMessage(String mobile, Integer bizCode);

    Result verifyCode(String mobile, Integer bizCode,String msg) throws Exception;

    /**
     * 获取用户文章数量
     * @param userUniqueCode
     * @return
     */
    int getArticleNum(String userUniqueCode);

    /**
     * 获取用户问答数量
     * @param userUniqueCode
     * @return
     */
    int getAskNum(String userUniqueCode);

    /**
     * 获取用户课程数量
     * @param userUniqueCode
     * @return
     */
    int getCourseNum(String userUniqueCode);

    /**
     * 获取用户消息数量
     * @param userUniqueCode
     * @return
     */
    int getMessageNum(String userUniqueCode);

    /**
     * 获取用户订单数量
     * @param userUniqueCode
     * @return
     */
    int getOrderNum(String userUniqueCode);

    /**
     * 获取用户收藏数量
     * @param userUniqueCode
     * @return
     */
    int getFavoriteNum(String userUniqueCode);

    /**
     * 获取用户优惠券数量
     * @param userUniqueCode
     * @return
     */
    int getCouponNum(String userUniqueCode);

    /**
     * 获取区域列表
     * @param id id
     * @return List<AreaResp>
     */
    List<AreaResp> getAreaList(Integer id);

    /**
     * 获取字典列表
     * @param categoryCode categoryCode
     * @return List<DictResp>
     */
    List<DictResp> getDictList(String categoryCode);
}
