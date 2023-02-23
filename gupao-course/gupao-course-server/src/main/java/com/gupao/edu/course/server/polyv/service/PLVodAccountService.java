package com.gupao.edu.course.server.polyv.service;


import com.gupao.edu.course.server.polyv.bean.request.account.PLVodAccountRequest;
import com.gupao.edu.course.server.polyv.bean.result.PLVodCommonResult;

/**
 * 点播账户 Service
 *
 * @author zhangpan@centaline.com
 * createTime 2020/5/6
 */
public interface PLVodAccountService {

    /**
     * 获取用户空间及流量情况
     *
     * @param request
     * @return 响应结果
     */
    PLVodCommonResult getUserSpaceTrafficUsage(PLVodAccountRequest request);
}
