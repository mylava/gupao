package com.gupao.edu.common.cache.key;

/**
 * @author wzq.Jolin
 * @company none
 * @create 2020-04-25 20:19
 */
public class UserPrefix extends BasePrefix{

    private UserPrefix(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }


    public static final UserPrefix USER_TOKEN = new UserPrefix(300*1000,"user:token");
    public static final UserPrefix USER_UNIQUECODE = new UserPrefix(300*1000,"user:uniquecode");
    /**
     * 问答数量key
     */
    public static final UserPrefix USER_QUESTION = new UserPrefix(0,"user:question");
    /**
     * 文章数量key
     */
    public static final UserPrefix USER_ARTICLE = new UserPrefix(0,"user:article");
    /**
     * 课程数量key
     */
    public static final UserPrefix USER_COURSE = new UserPrefix(0,"user:course");
    /**
     * 订单数量key
     */
    public static final UserPrefix USER_ORDER = new UserPrefix(0,"user:order");
    /**
     * 消息数量key
     */
    public static final UserPrefix USER_MESSAGER = new UserPrefix(0,"user:message");
    /**
     * 优惠券数量key
     */
    public static final UserPrefix USER_COUPON = new UserPrefix(0,"user:coupon");
}
