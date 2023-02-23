package com.gupao.edu.account.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupao.edu.account.client.entity.PersonalLetter;
import com.gupao.edu.account.client.req.MessageListReq;
import com.gupao.edu.account.client.resp.PersonalLetterDetailVO;
import com.gupao.edu.account.client.resp.PersonalLetterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 私信表 Mapper 接口
 * </p>
 *
 * @author lipengfei
 * @since 2020-04-12
 */
public interface PersonalLetterMapper extends BaseMapper<PersonalLetter> {


    /**
     * 查询 所有 人 发送给我的私信
     * 列表
     * @param req
     * @return
     */
    List<PersonalLetterVO> listPersonalLetters(@Param("condition")MessageListReq req);


    /**
     * @param userUniqueCode 用户唯一编码
     * @param oppositeUniqueCode 对方唯一编码
     * @return
     */
    List<PersonalLetterDetailVO> listPersonalLetterDetail(@Param("userUniqueCode")String userUniqueCode,
                                                          @Param("oppositeUniqueCode")String oppositeUniqueCode);

    /**
     * 批量更新 阅读状态
     * @param userUniqueCode
     * @param oppositeUniqueCode
     * @param readStatus
     * @return
     */
    int batchUpdate(@Param("userUniqueCode")String userUniqueCode,
                    @Param("oppositeUniqueCode")String oppositeUniqueCode,
                    @Param("readStatus") int readStatus);
}
