package com.gupao.edu.course.server.polyv.service.impl;

import com.gupao.edu.course.server.polyv.bean.request.account.PLVodAccountRequest;
import com.gupao.edu.course.server.polyv.bean.result.PLVodCommonResult;
import com.gupao.edu.course.server.polyv.constant.ApiConstants;
import com.gupao.edu.course.server.polyv.service.PLVodAccountService;
import com.gupao.edu.course.server.polyv.service.PLVodRequestAbstractService;


/**
 * 点播账户 Service 实现类
 *
 * @author zhangpan@centaline.com
 * createTime 2020/5/6
 */
public class PLVodAccountServiceImpl extends PLVodRequestAbstractService implements PLVodAccountService {

    @Override
    public PLVodCommonResult getUserSpaceTrafficUsage(PLVodAccountRequest request) {
        String url = ApiConstants.GET_USER_SPACE_TRAFFIC_USAGE.replace("{userid}", request.getUserId());
        PLVodCommonResult result = getRequest(url, request.getParams());
        return result;
    }
}
