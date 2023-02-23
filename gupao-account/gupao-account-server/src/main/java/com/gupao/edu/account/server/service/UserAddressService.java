package com.gupao.edu.account.server.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gupao.edu.account.client.entity.UserAddress;
import com.gupao.edu.account.client.req.AddressReq;
import com.gupao.edu.account.client.req.BasePageReq;
import com.gupao.edu.account.client.resp.AddressResp;
import com.gupao.edu.account.client.resp.AddressVO;

/**
 * <p>
 * 用户收货地址表 服务类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserAddressService extends IService<UserAddress> {

//    UserAddress findUserAddress(String userUniqueCode);

    /**
     * 查看用户地址列表
     * @param basePageReq 分页实体
     * @return  IPage<AddressResp>
     */
    IPage<AddressResp> selectUserAddressPage(BasePageReq basePageReq);

    /**
     * 我的地址详情
     * @param userUniqueCode 用户唯一编码
     * @param addressId 用户地址ID
     * @return AddressVO
     * @throws Exception 抛出异常
     */
    AddressVO detail(String userUniqueCode,Integer addressId) throws Exception ;

    /**
     * 新增地址
     * @param addressReq 地址实体
     * @return Boolean
     */
    Boolean add(AddressReq addressReq);

    /**
     * 新增地址
     * @param addressReq 地址实体
     * @return Boolean
     */
    Boolean modify(AddressReq addressReq);
}
