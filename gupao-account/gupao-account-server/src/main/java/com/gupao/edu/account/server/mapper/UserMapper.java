package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.account.client.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 根据用户唯一编码查询用户
	 *
	 * @param userUniqueCode userUniqueCode
	 * @return User
	 */
	User selectByUniqueCode(@Param("userUniqueCode") String userUniqueCode);
}
