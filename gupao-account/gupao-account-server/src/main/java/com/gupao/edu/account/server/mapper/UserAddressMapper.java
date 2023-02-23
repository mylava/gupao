package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gupao.edu.account.client.entity.UserAddress;
import com.gupao.edu.account.client.resp.AddressResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户收货地址表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {

    /**
     * 查询 : 根据userUniqueCode用户唯一编码查询用户地址列表，分页显示
     * @param page 翻页对象
     * @param userUniqueCode 用户唯一编码
     * @return List<AddressResp>
     */
    List<AddressResp> selectUserAddressList(Page<AddressResp> page, @Param("userUniqueCode") String userUniqueCode);

}
