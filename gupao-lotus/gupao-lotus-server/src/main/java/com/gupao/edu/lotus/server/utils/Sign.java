package com.gupao.edu.lotus.server.utils;

import com.gupao.edu.common.utils.Md5Utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-05-02 13:35
 */
public class Sign {
    /**
     * 微信支付签名算法sign
     *
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public static String createSign(String characterEncoding,
                                    SortedMap<Object, Object> parameters, String key) {
        StringBuffer sb = new StringBuffer();
        // 所有参与传参的参数按照 accsii 排序（升序）
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k)
                    && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);

        String sign = Md5Utils.MD5Encode(sb.toString(), characterEncoding)
                .toUpperCase();
        return sign;
    }
}
