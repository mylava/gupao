package com.gupao.edu.answer.client.enumeration;

public class SettingsType {

    public enum VerifySettingType{
        VERIFY_ANSWER("verify_answer", "是否开启回答审核"),  //（1：开启，0：关闭）
        VERIFY_ARTICLE("verify_article", "是否开启撰写文章审核"),  //（1：开启，0：关闭）
        VERIFY_COMMENT("verify_comment", "是否开启新增评论审核"),  //（1：开启，0：关闭）
        VERIFY_QUESTION("verify_question", "是否开启发布问答审核"),  //（1：开启，0：关闭）
        VIP_EXPISEIN_DATE("vip_expiseIn_date", "vip有效时间"),  //（2022-01-01 00:00:00  默认有效期）
        ;

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private VerifySettingType(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "VerifySettingType{" +
                    "code='" + code + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public enum CoinSettingType{
        COINS_ADOPTED("coins_adopted", "采纳回答获得奖励G币"),
        COINS_ANSWER("coins_answer", "回答问题获得奖励G币"),
        COINS_ASK("coins_ask", "发布问答获得奖励G币"),
        COINS_LOGIN("coins_login", "每日登录获得奖励G币"),
        COINS_SIGN("coins_sign", "每日签到获得奖励G币"),
        COINS_REGISTER("coins_register", "注册获得奖励G币"),
        COINS_WRITE_ARTICLE("coins_write_article", "撰写文章获得奖励G币"),
        INVITATION_ANSWER_COINS("invitation_answer_coins", "邀请回答扣除G币"),
        ;

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private CoinSettingType(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "CoinSettingType{" +
                    "code='" + code + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public enum CreditsSettingType{
        CREDITS_ADOPTED("credits_adopted", "采纳回答获得奖励经验"),
        CREDITS_ANSWER("credits_answer", "回答问题获得经验"),
        CREDITS_ASK("credits_ask", "发布问答获得奖励经验"),
        CREDITS_LOGIN("credits_login", "注册获得奖励经验"),
        CREDITS_SIGN("credits_sign", "签到获得奖励经验"),
        CREDITS_REGISTER("credits_register", "注册获得奖励经验"),
        CREDITS_WRITE_ARTICLE("credits_write_article", "撰写文章获得奖励经验"),
        ;

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private CreditsSettingType(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "CreditsSettingType{" +
                    "code='" + code + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public enum LimitNumSettingType{
        QUESTION_LIMIT_NUM("question_limit_num", "1小时内最大提问数"),
        ANSWER_LIMIT_NUM("answer_limit_num", "1小时内最大回答数"),
        ARTICLE_LIMIT_NUM("article_limit_num", "1小时内最大文章发表次数"),
        UNVIP_ARTICLE_LIMIT_NUM("unvip_article_limit_num", "1小时内非vip用户最大文章发表次数"),
        UNVIP_QUESTION_LIMIT_NUM("unvip_question_limit_num", "1小时内非vip用户最大提问数"),
        ARTICLE_OPPOSITIONS_NUM("article_oppositions_num", "众申文章审核不推荐无效数"),
        ARTICLE_SUPPORT_NUM("article_support_num", "众申文章审核推荐有效数"),
        QUESTION_OPPOSITIONS_NUM("question_oppositions_num", "众申问题审核不推荐无效数"),
        QUESTION_SUPPORT_NUM("question_support_num", "众申问题审核推荐有效数"),
        ;

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private LimitNumSettingType(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "LimitNumSettingType{" +
                    "code='" + code + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public enum UnVipCoinsReward{
        UNVIP_LOGIN_GOLD_REWARD("unvip_login_gold_reward", "非vip登录奖励G币"),
        UNVIP_SIGN_GOLD_REWARD("unvip_sign_gold_reward", "非vip签到奖励G币"),
        UNVIP_ANSWER_GOLD_REWARD("unvip_answer_gold_reward", "非vip回答奖励G币"),
        UNVIP_ARTICLE_GOLD_REWARD("unvip_article_gold_reward", "非vip发表文章奖励G币"),
        UNVIP_ASK_GOLD_REWARD("unvip_ask_gold_reward", "非vip问答奖励G币"),
        UNVIP_REGISTER_GOLD_REWARD("unvip_register_gold_reward", "非vip发表文章奖励G币"),
        UNVIP_ADOPTED_GOLD_REWARD("unvip_adopted_gold_reward", "非vip回答采纳奖励G币"),
        ;

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private UnVipCoinsReward(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "UnVipCoinsReward{" +
                    "code='" + code + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public enum UserActivateLevelCoins{
        ACTIVATE_GREEN_HAND_COINS("activate_green_hand_coins", "激活菜鸟所需金币"),
        ACTIVATE_CODE_FARMER_COINS("activate_code_farmer_coins", "激活码农所需金币"),
        ACTIVATE_BIG_CATTLE_COINS("activate_big_cattle_coins", "激活大牛所需金币"),
        ACTIVATE_BIG_GOD_COINS("activate_big_god_coins", "激活大神所需金币"),
        ;

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private UserActivateLevelCoins(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "UserActivateLevelCoins{" +
                    "code='" + code + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public enum InvitationAnswerCoins{
        INVITATION_BIG_CATTLE_COINS("invitation_big_cattle_coins", "邀请大牛所需金币"),
        INVITATION_BIG_GOD_COINS("invitation_big_god_coins", "邀请大神所需金币"),
        INVITATION_CODE_FARMER_COINS("invitation_code_farmer_coins", "邀请码农所需金币"),
        INVITATION_GREEN_HAND_COINS("invitation_green_hand_coins", "邀请菜鸟所需金币"),
        ;

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private InvitationAnswerCoins(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "InvitationAnswerCoins{" +
                    "code='" + code + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }

    public enum ExcellentWork{
        SCORE_NUMBER("score_number", "优秀作业评比至少人数"),
        AVERAGE_SCORE("average_score", "优秀作业评比平均分至少分数"),
        WORK_EXCELLENT_NUMBER("work_excellent_number", "优秀作业评比前多少名"),
        ;

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }

        private ExcellentWork(String code, String desc){
            this.code = code;
            this.desc = desc;
        }

        @Override
        public String toString() {
            return "ExcellentWork{" +
                    "code='" + code + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }
}
