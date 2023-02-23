package com.gupao.edu.lotus.server.utils;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import lombok.Data;


import java.util.Map;

/**
 * @Author zhangpan@centaline.com.cn
 * @Date 2020/4/26 16:46
 * 云片短信发送
 */
@Data
public class JavaSmsApi {

    /**
     * 使用JDK发送单条短信,智能匹配短信模板
     *
     * @param apikey 成功注册后登录云片官网,进入后台可查看
     * @param text   需要使用已审核通过的模板或者默认模板
     * @param mobile 接收的手机号,仅支持单号码发送
     */
    //方法2
    public static int singleSend(String apikey, String mobile, String text,String templateId) {
        //初始化clnt,使用单例方式
        YunpianClient clnt = new YunpianClient(apikey).init();
        //发送短信API
        Map<String, String> param = clnt.newParam(3);
        param.put(YunpianClient.APIKEY, apikey);
        param.put(YunpianClient.MOBILE, mobile);
        param.put(YunpianClient.TPL_VALUE,text);
        param.put(YunpianClient.TPL_ID,templateId);
        Result<SmsSingleSend> result = clnt.sms().tpl_single_send(param);
        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()

        //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*

       //释放clnt
        clnt.close();
        System.out.println(result);
        return result.getCode();
    }

}
