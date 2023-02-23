package com.gupao.edu.account.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gupao.edu.account.client.entity.UserAddress;
import com.gupao.edu.account.client.req.AddressReq;
import com.gupao.edu.account.client.req.BasePageReq;
import com.gupao.edu.account.client.resp.AddressResp;
import com.gupao.edu.account.client.resp.AddressVO;
import com.gupao.edu.account.server.mapper.UserAddressMapper;
import com.gupao.edu.account.server.service.UserAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收货地址表 服务实现类
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

//    @Override
//    public UserAddress findUserAddress(String userUniqueCode) {
//        Map<String,Object> map = new HashMap<>();
//        map.put("user_unique_code",userUniqueCode);
//        map.put("is_default",true);
//        return this.baseMapper.selectOne(new QueryWrapper<UserAddress>().allEq(map));
//    }

    /**
     * 我的地址列表
     * @param basePageReq 分页实体
     * @return IPage<AddressResp>
     */
    @Override
    public IPage<AddressResp> selectUserAddressPage(BasePageReq basePageReq) {
        Page<AddressResp> page = new Page<>(basePageReq.getPage(),basePageReq.getPageNum());
        return page.setRecords(baseMapper.selectUserAddressList(page, basePageReq.getUserUniqueCode()));
    }

    /**
     * 我的地址详情
     * @param userUniqueCode 用户唯一编码
     * @param addressId 用户地址ID
     * @return AddressVO
     * @throws Exception 抛出异常
     */
    @Override
    public AddressVO detail(String userUniqueCode,Integer addressId) throws Exception {
        AddressVO addressVO = new AddressVO();
        UserAddress userAddress = this.getById(addressId);
        if (null == userAddress) {
            throw new Exception("没有该用户地址");
        }
        if (!userUniqueCode.equals(userAddress.getUserUniqueCode())){
            throw new Exception("没有权限查看该地址信息");
        }
        BeanUtils.copyProperties(userAddress,addressVO);
        addressVO.setAddressId(userAddress.getId());
        return addressVO;
    }

    /**
     * 新增地址
     * @param addressReq 地址实体
     * @return Boolean
     */
    @Override
    public Boolean add(AddressReq addressReq) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressReq,userAddress);
        if (addressReq.getIsDefault()){ //如果该地址为默认地址，则该用户的其他默认地址设置为false
            cancelDefault(addressReq.getUserUniqueCode());
        }
        return this.save(userAddress);
    }

    /**
     * 修改物流地址
     * @param addressReq 地址实体
     * @return Boolean
     */
    @Override
    public Boolean modify(AddressReq addressReq) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressReq,userAddress);
        userAddress.setId(addressReq.getAddressId());
        if (addressReq.getIsDefault()){ //如果该地址为默认地址，则该用户的其他默认地址设置为false
            cancelDefault(addressReq.getUserUniqueCode());
        }
        return this.saveOrUpdate(userAddress);
    }

    private void cancelDefault(String userUniqueCode){
        Map<String,Object> map = new HashMap<>();
        map.put("user_unique_code",userUniqueCode);
        map.put("is_default",true);
        List<UserAddress> userAddressList = baseMapper.selectByMap(map);
        for (UserAddress u:userAddressList) {
            u.setIsDefault(false);
            this.save(u);
        }
    }


}
