package com.gupao.edu.common.utils.IdLeaf;

import org.springframework.stereotype.Component;

/**分号器统一接口
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-17 20:46
 */
public interface IdLeafService {

    /**
     * 生成对应编号
     * @return
     */
    long getOrderNo();
}
