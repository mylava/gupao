package com.gupao.edu.answer.client.enumeration;

public enum CoinOperation {
    //赚取金币
    EARN_CREATE_QUESTION("ask", "创建问题"),
    EARN_ANSWER_QUESTION("answer", "回答问题"),
    EARN_ANSWER_ADOPTION("answer_adopted", "回答采纳"),
    EARN_CREATE_ARTICLE("create_article", "撰写文章"),
    EARN_EXCHANGE_BACK("exchange_back", "兑换退回"),
    EARN_REGISTER("register", "注册"),
    EARN_LOGIN("login", "登录"),
    EARN_SIGN_IN("sign", "签到"),
    EARN_ADMIN_REWARD("reward_user", "后台奖赏"),
    //用户完成任务奖励金币
    EARN_TASK_ASK_QUESTIONS("task_ask_questions", "发布问答"),
    EARN_TASK_ANSWERS_QUESTIONS("task_answers_questions", "回答问题"),
    EARN_TASK_ADOPT_QUESTIONS("task_adopt_questions","采纳问题"),
    EARN_TASK_WRITING_ARTICLES("task_writing_articles","撰写文章"),
    EARN_TASK_SHARES_ARTICLES("task_shares_articles","分享文章"),
    EARN_TASK_COMMENTS_ARTICLES("task_comments_articles","评论文章"),
    EARN_TASK_DAILY_ATTENDANCE("task_daily_attendance", "日常签到"),
    EARN_TASK_INFORMATION("task_information", "完善资料"),
    //第二期任务
    EARN_TASK_HELP_ADOPT("task_help_adopt", "帮助采纳问题"),
    EARN_TASK_COLLECT_ARTICLES("task_collect_articles","收藏文章"),
    EARN_TASK_SUPPORT_ARTICLES("task_support_articles","点赞文章"),
    EARN_TASK_ATTENTION_AUTHOR("task_attention_author","关注作者"),
    EARN_TASK_ATTENTION_QUESTIONS("task_attention_questions","关注问题"),
    EARN_TASK_SHARES_QUESTIONS("task_shares_questions", "分享问题"),
    EARN_TASK_COMMENTS_QUESTIONS("task_comments_questions", "评论问题"),
    EARN_TASK_COMMENTS_ANSWERS("task_comments_answers", "评论答案"),
    EARN_TASK_SUPPORT_ANSWERS("task_support_answers", "点赞回答"),
    EARN_TASK_SCORE_WORKS("task_score_works","作业评分"),
    EARN_TASK_COMMENTS_WORKS("task_comments_works","作业评论"),

    EARN_INVITATION_ANSWER_BACK("invitation_answer_back", "求助撤回返回金币"),

    EARN_REWARD_ARTICLES("reward_articles", "打赏文章奖励"),

    //花费金币
    EXPEND_REWARD_QUESTION("ask", "提问悬赏"),
    EXPEND_APPEND_REWARD_QUESTION("append_reward", "追加悬赏"),
    EXPEND_GOODS_EXCHANGE("exchange", "商品兑换"),
    EXPEND_ADMIN_PUNISH_USER("punish_user", "后台惩罚"),
    EXPEND_INVITATION_ANSWER_COINS("invitation_answer_coins", "邀请回答扣除金币"),

    EXPEND_REWARD_ARTICLES("expend_reward_articles", "打赏文章扣除"),
    ;


    private String code;
    private String label;

    public String getCode() {
        return code;
    }

    CoinOperation(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
