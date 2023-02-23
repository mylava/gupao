package com.gupao.edu.marketing.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.marketing.client.entity.CouponGoodsRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 优惠券商品关系表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Mapper
@Repository
public interface CouponGoodsRelationMapper extends BaseMapper<CouponGoodsRelation> {

}
