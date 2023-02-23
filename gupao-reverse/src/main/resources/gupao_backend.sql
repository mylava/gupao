-- =============================================================================
-- 用户
-- =============================================================================

-- -----------------
-- 用户信息
-- -----------------
-- 1 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `email` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机号',
  `user_name` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `avatar` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `student_no` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学号',
  `qq_open_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方绑定，用户qq openId',
  `wechat_union_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方绑定，用户微信 unionId',
  `account_state` tinyint(2) unsigned DEFAULT NULL COMMENT '账户状态: 1-正常 2-冻结',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_mobile` (`mobile`) USING BTREE COMMENT '唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 2 登录历史记录表
DROP TABLE IF EXISTS `user_login_history`;
CREATE TABLE `user_login_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `os_type` tinyint(2) unsigned DEFAULT NULL COMMENT 'app平台:1-ios 2-android)',
  `device_type` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备类型(机型)',
  `geographical_location` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地理位置',
  `device_no` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机器设备号',
  `ip_address` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `service_type` tinyint(2) unsigned DEFAULT NULL COMMENT '登录入口 1-app 2-社区 3-小程序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='登录历史记录表';

-- 3 用户详细信息表(包含职业生涯)
DROP TABLE IF EXISTS `user_info_detail`;
CREATE TABLE `user_info_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `user_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `title` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学员称谓',
  `grade_ids` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学科ID，多个id使用json:{"1":"ID"}',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  `gender` tinyint(2) unsigned NOT NULL DEFAULT '1' COMMENT '1-男 2-女',
  `work_city` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所在城市',
  `province_id` int(11) unsigned DEFAULT NULL COMMENT '所在省ID:对应字典表',
  `city_id` int(11) unsigned DEFAULT NULL COMMENT '所在市ID:对应字典表',
  `education` int(11) unsigned DEFAULT NULL COMMENT '学历:对应字典表',
  `qq` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'QQ号',
  `wechat` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信号',
  `employer` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所在公司',
  `seniority` tinyint(2) unsigned DEFAULT NULL COMMENT '工作年限',
  `job_title` tinyint(2) unsigned DEFAULT NULL COMMENT '职位名称ID:对应字典表',
  `current_salary` int(3) unsigned DEFAULT NULL COMMENT '目前年薪:单位万',
  `expect_salary` int(4) unsigned DEFAULT NULL COMMENT '期望年薪:单位万',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户详细信息表';

-- 4 地址表
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `receiver` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收件人姓名',
  `mobile` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `province` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省份',
  `province_id` int(11) unsigned DEFAULT NULL COMMENT '所在省ID:对应字典表',
  `city` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '市',
  `city_id` int(11) unsigned DEFAULT NULL COMMENT '所在市ID:对应字典表',
  `county` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区',
  `county_id` int(11) unsigned DEFAULT NULL COMMENT '所在区ID:对应字典表',
  `address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '详细地址',
  `is_default` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '地址默认 1-是 0-否',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '1-删除 0-未删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户地址表';

-- 5 会员表
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键会员ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `effective_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效日期',
  -- 添加字段
  `invalid_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '失效日期',
  `duration` int(11) unsigned DEFAULT '0' COMMENT '购买时长',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`),
  CONSTRAINT `member_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='会员表';

-- 6 用户资金账户表
DROP TABLE IF EXISTS  `account_balance`;
CREATE TABLE `account_balance` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `amount` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '金额(分为单位)',
  `available_amount` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '可用金额(分为单位)',
  `frozen_amount` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '冻结金额(分为单位)',
  `account_state` tinyint(1) unsigned COMMENT '账户状态: 1-正常 2-冻结',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_id` (`user_id`) USING BTREE COMMENT '用户id索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学币账户表';

-- -----------------
-- 资产
-- -----------------
-- 7 账户流水记录表
DROP TABLE IF EXISTS `user_asset_history`;
CREATE TABLE `user_asset_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户id',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单号id',
  `amount` int(11) DEFAULT NULL COMMENT '金额/数量',
  `action_type` tinyint(1) unsigned DEFAULT NULL COMMENT '操作类型：1-充值 2 -消费',
  `desc` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学币流水记录表';

-- 8 会员权益关联表
DROP TABLE IF EXISTS `member_right`;
CREATE TABLE `member_right` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `right_dict_id` int(11) unsigned NOT NULL COMMENT '权益名称，对应字典表',
  `right_type` tinyint(1) unsigned NOT NULL COMMENT '权益类型：1 直接发放 2 主动领取',
  `right_resource_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '资源id',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员权益关联表';

-- 9 权益资源表
DROP TABLE IF EXISTS `member_right_resource`;
CREATE TABLE `member_right_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_right_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '权益ID',
  `resource_type` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '资源分类：1书籍 2文化衫',
  `name` varchar(32) DEFAULT NULL COMMENT '资源名称',
  `summary` varchar(32) DEFAULT NULL COMMENT '资源简介',
  `cost` int(11) unsigned NOT NULL DEFAULT 0 COMMENT '现价，有效价格',
  `price` double(14,2) NOT NULL DEFAULT '0.00' COMMENT '原价，无效价格',
  `description` blob NOT NULL COMMENT '资源图文描述',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权益明细表';


-- 10 权益领取记录表
DROP TABLE IF EXISTS `member_right_claim_history`;
CREATE TABLE `member_right_claim_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID（领取人）',
  `member_right_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '权益ID',
  `resource_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '资源id',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员ID（领取人）',
  `user_address_id` int(11) unsigned NOT NULL COMMENT '收货地址',
  `delivery_state` tinyint(1) unsigned NOT NULL COMMENT '物流状态：1待发货 2已发货 3已收货 4其他',
  `logistics_no` varchar(32) NOT NULL COMMENT '物流单号',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权益领取记录表';

-- -----------------
-- 互动
-- -----------------
-- 11 关注表
DROP TABLE IF EXISTS `user_attention`;
CREATE TABLE `user_attention` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '关注者ID',
  `attention_user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '被关注ID',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` varchar(255) DEFAULT NULL COMMENT '取消关注: 0已取消 1未取消',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户关注表';

-- 12 互动消息表
DROP TABLE IF EXISTS `user_interaction_message`;
CREATE TABLE `user_interaction_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `avatar` varchar(512) DEFAULT NULL COMMENT '主动互动方头像',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '主动互动方昵称',
  `to_user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '接收人',
  `attention_type` int(11) unsigned NOT NULL COMMENT '通知类型:对应字典表',
  `source_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '1-问答 2-文章 3-答疑 4-作业',
  `source_id` int(11) unsigned DEFAULT NULL COMMENT '互动内容id',
  `subject` varchar(256) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) COMMENT '内容',
  `is_read` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已读 0-未读 1-已读',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '0-未删除 1-删除',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='互动消息表';

-- 13 私信表
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` int(11) unsigned NOT NULL COMMENT '消息父ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `to_user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `message` varchar(512) COMMENT '消息内容',
  `is_read` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已读 0-未读 1-已读',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '0-未删除 1-删除',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='私信表';

-- =============================================================================
-- 课程
-- =============================================================================
-- 14 课程表
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `category_id` int(11) unsigned  DEFAULT 0 COMMENT '课程分类id（训练营、精品小课、vip课，对应字典表）',
  `grade_id` int(11) unsigned DEFAULT NULL COMMENT '所属学科id:(JAVA、大数据,对应字典表)',
  `course_name` varchar(128) DEFAULT NULL COMMENT '课程名',
  `course_title` varchar(128)  NOT NULL COMMENT '课程主题名称',
  `image_url` varchar(512)  DEFAULT NULL COMMENT '课程封面图片',
  `view_num` int(11) unsigned DEFAULT '0' COMMENT '观看人数',
  `study_num` int(11) unsigned DEFAULT '0' COMMENT '已学人数',
  `comment_num` int(11) unsigned DEFAULT '0' COMMENT '评论人数',
  `attention_num` int(11) unsigned DEFAULT '0' COMMENT '关注人数',
  `praise_num` int(11) unsigned DEFAULT '0' COMMENT '好评数',
  -- 新增字段，用于计算每个人的学习进度
  `total_minutes` int(11) unsigned DEFAULT '0' COMMENT '课程视频总时长(秒)',
  `praise_score` float(2,2) unsigned DEFAULT '0.00' COMMENT '好评度',
  `average_score` float(2,2) unsigned DEFAULT '0.0' COMMENT '平均评分',
  `reply_count` int(6) unsigned DEFAULT '0' COMMENT '答疑数， 维护在课程表中,方便查询',
  `cost` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '现价，有效价格',
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '原价，无效价格',
  `lecturer_ids` varchar(256) DEFAULT NULL COMMENT '老师的Id，多个老师用json',
  `description` blob  DEFAULT NULL COMMENT '图文详情，富文本应编码存储',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0未删除，1已删除',
  `start_Time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '课程的开播时间',
  `endTime` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '课程的结束时间',
  `putaway_time` timestamp NULL  COMMENT '上架时间',
  `create_user_id` int(11) unsigned DEFAULT NULL COMMENT '创建者的userId',
  `update_user_id` int(11) unsigned DEFAULT NULL COMMENT '修改者的userId',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='课程表';

-- 15 课程大纲表
DROP TABLE IF EXISTS `course_outline`;
CREATE TABLE `course_outline` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '大纲ID，主键',
  `parent_id` int(11) unsigned  DEFAULT null COMMENT '父级目录ID',
  `course_id` int(11) unsigned NOT NULL COMMENT '所属课程ID',
  `title` varchar(128) NOT NULL COMMENT '标题',
  `sort` tinyint(2) unsigned DEFAULT NULL COMMENT '课程大纲排序',
-- 直播需要添加字段
  `video_or_live` tinyint(1) unsigned  COMMENT '录播还是直播  0录播 1直播',
--   `live_start_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '直播开始时间',
--   `live_end_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '直播开始时间(预告结束时间)',
--   `live_stream_url` varchar(512) NOT NULL COMMENT '直播流地址',
  `video_or_stream_id` int(11) unsigned COMMENT '关联视频ID（如果是直播，需创建直播表）',
  `outline_level` tinyint(1) unsigned DEFAULT '0' COMMENT '1 大纲（章-一级目录）2 是视频（节-二级目录）方便查询使用',
  `is_vedio` tinyint(1) unsigned DEFAULT '0' COMMENT '是否是视频  0否 1是 方便查询使用',
  `create_user_id` int(11) unsigned DEFAULT NULL COMMENT '创建者的userId',
  `update_user_id` int(11) unsigned DEFAULT NULL COMMENT '修改者的userId',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='课程大纲表（关联视频资源）';

-- 16 视频资源表
DROP TABLE IF EXISTS `course_video`;
CREATE TABLE `course_video` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `grade_id` int(11) unsigned DEFAULT NULL COMMENT '所属学科id:(JAVA、大数据,对应字典表)',
  `title` varchar(50) DEFAULT NULL COMMENT '视频标题',
  `duration` tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '视频时长单位秒',
  `view_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `file_size` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '文件大小单位M',
  `audit_state` tinyint(1) unsigned NOT NULL DEFAULT '0'COMMENT '状态(-1:审核不通过，0:审核中，1:审核通过)',
  `cloud_video_state` varchar(36) NOT NULL DEFAULT 'unknown' COMMENT '预留字段：保利威云视频状态',
  `image_url` varchar(512) DEFAULT NULL COMMENT '本视频的图片展示地址',
  `video_url` varchar(256) DEFAULT NULL COMMENT '播放地址',
  `create_user_id` int(11) unsigned DEFAULT NULL COMMENT '创建者的userId',
  `update_user_id` int(11) unsigned DEFAULT NULL COMMENT '修改者的userId',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='视频资源表';

-- 17 讲师详情表
DROP TABLE IF EXISTS `lecturer`;
CREATE TABLE `lecturer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '教师名字',
  `summary` varchar(512) DEFAULT NULL COMMENT '教师简介',
  `description` blob DEFAULT NULL COMMENT '教师图文详情',
  `job_title` tinyint(2) unsigned DEFAULT NULL COMMENT '教师职位:对应字典表',
  `tag` varchar(32) DEFAULT NULL COMMENT '教师标签',
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机',
  `qq` varchar(64) DEFAULT NULL COMMENT 'qq',
  `wechat` varchar(64) DEFAULT NULL COMMENT '微信',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `star` tinyint(1) unsigned DEFAULT NULL COMMENT '是否明星讲师 1是0否',
  `sort` tinyint(2) unsigned DEFAULT NULL COMMENT '教师排序',
  `state` tinyint(1) unsigned DEFAULT NULL COMMENT '讲师状态',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除 默认0否 1是',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator_id` int(11) unsigned DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='讲师详情表';

-- 18 课程评论表
DROP TABLE IF EXISTS `course_comment`;
CREATE TABLE `course_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '被评标的所属的课程ID',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '发表评论的用户的userId',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '发表评论的用户的呢称',
  `star` tinyint(1) unsigned DEFAULT '0' COMMENT '星级，一般最高为五星好评',
  `content` varchar(1024) DEFAULT NULL COMMENT '评论的内容',
  `office_replied` tinyint(1) unsigned DEFAULT 0 COMMENT '官方是否回复--回复类容存在答案表中',
  `course_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '已上课时间（分）',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0未删除，1已删除',
  `praise_or_negative` tinyint(1) unsigned DEFAULT NULL COMMENT '是否好评 0 未评价 1好评 2差评',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='课程评论表';

-- 19 视频答疑问题表
DROP TABLE IF EXISTS `course_vedio_question`;
CREATE TABLE `course_vedio_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '问题ID',
  `user_id` int(10) unsigned NOT NULL COMMENT '提问者ID',
  `vedio_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '视频id',
  `point`  int(5) DEFAULT null COMMENT '视频播放时间点(秒)',
  `title` varchar(255) NOT NULL COMMENT '问题标题',
  `content` blob NOT NULL COMMENT '问题内容',
  `reply_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '回答数',
  `browse_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '浏览人数 ',
  `audit_state` tinyint(1) unsigned NOT NULL DEFAULT '0'COMMENT '状态(-1:审核不通过，0:审核中，1:审核通过)',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='问题记录表';

-- 20 视频答疑回复表
DROP TABLE IF EXISTS `course_vedio_question_reply`;
CREATE TABLE `course_vedio_question_reply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `question_title` varchar(256) NOT NULL COMMENT '问题标题',
  `course_vedio_question_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '问题id',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '用户id',
  `praise_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `negative_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '反对数量',
  `office_reply` tinyint(1) unsigned DEFAULT null COMMENT '是否官方回复',
  `content` blob DEFAULT NULL COMMENT '回复内容',
  `is_adopted` tinyint(1) unsigned DEFAULT null COMMENT '是否被采纳',
  `adopted_time` timestamp NULL DEFAULT NULL COMMENT '采纳时间',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='视频答疑回复表';

-- 21 视频答疑回复评论表
DROP TABLE IF EXISTS `course_vedio_question_reply_comment`;
CREATE TABLE `course_vedio_question_reply_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '自关联ID',
  `course_vedio_question_reply_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '答疑回复id',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '用户id',
  `praise_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `content` blob DEFAULT NULL COMMENT '评论内容',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='视频答疑回复评论表';

-- 22 答疑点赞表
DROP TABLE IF EXISTS `course_vedio_question_reply_praise`;
create table `course_vedio_question_reply_praise` (
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`course_vedio_question_reply_id` int(11) unsigned NOT NULL COMMENT '关联答疑回复表ID',
	`user_id` int(11) unsigned comment '点赞用户ID: 自己可以对字节点赞 ： 如果控制不重复点赞 ： 建一个唯一索引',
	`create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='答疑点赞表';

-- 23 作业问题表
DROP TABLE IF EXISTS `course_homework`;
CREATE TABLE `course_homework` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `course_outline_id` int(11) unsigned NOT NULL  COMMENT '大纲ID（大纲章ID，不是节ID）',
  `grade_id` int(11) unsigned DEFAULT NULL COMMENT '学科ID',
  `title` varchar(128) DEFAULT NULL COMMENT '作业标题',
  `lecturer_id` varchar(64) DEFAULT NULL COMMENT '出题人,关联讲师ID',
  `content` blob DEFAULT NULL COMMENT '作业内容',
  `version` int(11) unsigned DEFAULT '0' COMMENT '版本',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='作业问题表';

-- 24 学员答作业表
DROP TABLE IF EXISTS `course_homework_reply`;
CREATE TABLE `course_homework_reply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '作业回复主键 ID',
      `course_homework_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '作业ID',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '用户id',
  `lecturer_id` varchar(64) DEFAULT NULL COMMENT '出题人,关联讲师ID',
  `homework_reply_content` blob DEFAULT NULL COMMENT '作业内容',
  `score` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '作业得分',
  `is_good` tinyint(1) unsigned DEFAULT NULL COMMENT '是否优秀作业',
  `correct_status` tinyint(1) unsigned DEFAULT '0' COMMENT '作业状态。0：未批阅，1：已批阅',
  `correct` blob DEFAULT null COMMENT '批阅内容',
  `correct_time` timestamp DEFAULT CURRENT_TIMESTAMP  COMMENT '批阅时间',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='学员答作业表';

-- 25 学员作业评论表
DROP TABLE IF EXISTS `course_homework_reply_comment`;
CREATE TABLE `course_homework_reply_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '自关联ID',
  `course_homework_reply_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '学员作业id',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0'COMMENT '用户id',
  `praise_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `content` blob DEFAULT NULL COMMENT '评论内容',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='学员作业评论表';

-- 26 学员作业评论点赞表
DROP TABLE IF EXISTS `course_homework_reply_praise`;
create table `course_homework_reply_praise`(
	`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
	`course_vedio_reply_id` int(11) unsigned NOT NULL COMMENT '关联答疑回复表ID',
	`user_id` int(11) unsigned comment '点赞用户ID: 自己可以对字节点赞 ： 如果控制不重复点赞 ： 建一个唯一索引',
	`create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='学员作业评论点赞表';

-- 27 我购买的课程Table
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '课程id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY pk_user_course_id (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户购买课程表';

-- 28 我的课程播放进度表
DROP TABLE IF EXISTS `user_course_progress`;
CREATE TABLE `user_course_progress` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `course_id` int(11) unsigned NOT NULL COMMENT '课程id',
  `video_id` int(11) unsigned NOT NULL  COMMENT '视频ID',
  `course_outline_id` int(11) unsigned NOT NULL  COMMENT '大纲ID(对应大纲小节)',
  `play_seconds` int(8) unsigned DEFAULT NULL COMMENT '播放时间，单位秒',
  `video_seconds` int(8) unsigned DEFAULT NULL COMMENT '视频总时长，单位秒',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='我的课程播放进度表';

-- 29 我的课表
DROP TABLE IF EXISTS `user_course_notice`;
CREATE TABLE `user_course_notice` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户id',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '课程ID',
  `notice_week_day` tinyint(1) unsigned DEFAULT NULL COMMENT '1-7 代表第几周',
  `notice_time` time NOT NULL DEFAULT '200101' COMMENT '提醒时间 例如 12:00',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='我的课表';


-- 30 课程收藏表
DROP TABLE IF EXISTS `user_course_favorite`;
CREATE TABLE `user_course_favorite` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_id` INT(11) UNSIGNED NOT NULL COMMENT '用户id',
  `course_id` INT(10) UNSIGNED NOT NULL COMMENT '课程id',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='课程收藏表';

-- ----------------------------------------------------------------------------------------------------------------------------
-- 课程
-- ----------------------------------------------------------------------------------------------------------------------------


-- -- 课程类型表(课程类型表训练营、精品小课、vip课)
-- DROP TABLE IF EXISTS `course_type`;
-- CREATE TABLE `course_type` (
--   `type_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID-type类型',
--   `name` varchar(255) NOT NULL COMMENT '课程类型名字（训练营、精品小课、vip课） ',
--   `createUserId` INT(10) unsigned NOT NULL DEFAULT '0' COMMENT '添加人',
--   `updateUserId` INT(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新人',
--   `ctime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `utime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
--   `del_flag` TINYINT(4) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
--   `remark` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '备注',
--   PRIMARY KEY (`type_id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='课程类型表训练营、精品小课、vip课';
--
-- -- 科目表
-- DROP TABLE IF EXISTS `course_subject`;
-- CREATE TABLE `course_subject` (
--   `subject_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
--   `name` varchar(50)  NOT NULL COMMENT '科目名称 ： ',
--   `del_flag` TINYINT(4) unsigned NOT NULL DEFAULT '0' COMMENT '删除标记',
--   `version` varchar(60)  DEFAULT '' COMMENT '版本',
--   `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`subject_id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='课程科目表： Java科目 大数据科目 人工智能';



-- ----------------------------------------------------------------------------------------------------------------------------
-- 订单
-- ----------------------------------------------------------------------------------------------------------------------------
-- 31
DROP TABLE IF EXISTS orders;
CREATE TABLE orders(
    id INT(11) unsigned NOT NULL AUTO_INCREMENT  COMMENT '订单主键 订单id' ,
    order_no VARCHAR(64)    COMMENT '订单号' ,
    user_id int(11) unsigned NOT NULL   COMMENT '用户id' ,
    total_amount INT(11) unsigned NOT NULL   COMMENT '订单总价格 订单总价格，单位:分' ,
    real_pay_amount INT(11) unsigned NOT NULL   COMMENT '实际支付总价格 实际支付总价格，单位:分' ,
    pay_method tinyint(1) unsigned NOT NULL   COMMENT '支付方式 1:微信 2:支付宝' ,
    left_msg VARCHAR(128)    COMMENT '买家留言' ,
    extand VARCHAR(32)    COMMENT '扩展字段' ,
    is_deleted tinyint(1) unsigned NOT NULL  DEFAULT 0 COMMENT '逻辑删除状态 1: 删除 0:未删除' ,
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    order_state tinyint(1) unsigned  NOT NULL COMMENT '订单状态' ,
    payment_state tinyint(1) unsigned  NOT NULL COMMENT '支付状态 ' ,
    pay_time datetime COMMENT '支付成功时间' ,
    close_time datetime COMMENT '交易关闭时间' ,
    success_time datetime COMMENT '交易成功时间' ,
    delivery_time datetime COMMENT '发货时间' ,
    need_delivery tinyint(1) unsigned   COMMENT '是否需要发货 0不需要 1需要' ,
    channel_source tinyint(1) unsigned  COMMENT '渠道 区分三方渠道过来的订单 1:PC，2:APP，3：小程序 4腾讯课堂 5网易课堂' ,
    is_apple_pay tinyint(1) unsigned  NOT NULL  DEFAULT 0 COMMENT '是否苹果支付' ,
    `audit_state` tinyint(1) unsigned NOT NULL DEFAULT '0'COMMENT '第三方订单审核状态(-1:审核不通过，0:审核中，1:审核通过)',
    `user_coupon_id` int(11) unsigned NOT NULL COMMENT '用户优惠券ID',
    PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT = '订单表 ';
ALTER TABLE orders ADD INDEX IDX_ORDERS_CREATEDTIME(create_time);;
ALTER TABLE orders COMMENT '订单表';;

-- 32
DROP TABLE IF EXISTS order_details;
CREATE TABLE order_details(
    id INT(11) unsigned NOT NULL   COMMENT '主键id' ,
    order_id INT(11) unsigned NOT NULL   COMMENT '订单id' ,
    order_no VARCHAR(32)    COMMENT '订单号 同时也是订单编号' ,
    item_id INT(11) unsigned NOT NULL   COMMENT '商品id' ,
    item_name VARCHAR(32) NOT NULL   COMMENT '商品名称' ,
    item_img VARCHAR(256) NOT NULL   COMMENT '商品图片（缩略图）' ,
--     item_type INT(11) unsigned NOT NULL   COMMENT '商品类型 关联字典表' ,
--     item_spec_id VARCHAR(32) NOT NULL   COMMENT '规格id' ,
--     item_spec_name VARCHAR(32) NOT NULL   COMMENT '规格名称' ,
    cost INT(11) unsigned NOT NULL   COMMENT '成交价格，单位:分' ,
    buy_counts INT(5) unsigned NOT NULL   COMMENT '购买数量' ,
    price INT(11) unsigned  COMMENT '原始价格，单位:分' ,
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT = '订单详情表 ';
ALTER TABLE order_details COMMENT '订单详情表';

-- 33
DROP TABLE IF EXISTS orders_logs;
CREATE TABLE orders_logs(
    id BIGINT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    order_id INT(11) unsigned NOT NULL   COMMENT '订单id' ,
    total_amount int(11) unsigned NOT NULL   COMMENT '订单总价格 订单总价格，单位:分' ,
    real_pay_amount INT(11) unsigned NOT NULL   COMMENT '实际支付总价格 实际支付总价格，单位:分' ,
    left_msg VARCHAR(128)    COMMENT '买家留言' ,
    `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT = '订单快照表 ';

-- 34
DROP TABLE IF EXISTS order_pay;
CREATE TABLE order_pay(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    order_id INT(11) unsigned NOT NULL   COMMENT '订单id' ,
    order_no VARCHAR(32)    COMMENT '订单号 同时也是订单编号' ,
    merchant_order_id VARCHAR(64) NOT NULL   COMMENT '商户订单号' ,
    user_id int(11) unsigned NOT NULL   COMMENT '用户id' ,
    pay_method INT(11) unsigned NOT NULL   COMMENT '支付方式 1:微信 2:支付宝,关联字典表' ,
    pay_status tinyint(1) unsigned NOT NULL   COMMENT '支付状态 1：未支付 2：已支付 3：支付失败 4：已退款' ,
    channel_source tinyint(1) unsigned  COMMENT '渠道 区分三方渠道过来的订单 1:PC，2:APP，3：小程序' ,
    cost INT(11) unsigned NOT NULL   COMMENT '实际支付金额 实际支付的金额，单位:分' ,
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
    PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT = '支付表 ';;
ALTER TABLE order_pay ADD INDEX IDX_ORDERS_CREATEDTIME(create_time);
ALTER TABLE order_pay COMMENT '支付表';

-- 35
DROP TABLE IF EXISTS orders_supplement;
CREATE TABLE orders_supplement(
    id INT(11) unsigned NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    order_id INT(11) unsigned NOT NULL   COMMENT '订单id' ,
    user_id int(11) unsigned NOT NULL   COMMENT '用户id' ,
    third_order_no VARCHAR(32) NOT NULL   COMMENT '第三方订单号' ,
    `category_id` int(11) unsigned DEFAULT 0 COMMENT '课程分类id（训练营、精品小课、vip课，对应字典表）',
    course_id BIGINT NOT NULL   COMMENT '课程id，关联course表' ,
    recommend_code VARCHAR(32) NOT NULL   COMMENT '推荐码' ,
    recommend_teacher_id INT COMMENT '推荐老师id' ,
    recommend_mobile varchar(16) COMMENT '推荐学员手机号' ,
    recommend_user_id INT COMMENT '推荐学员id' ,
    lecture_id int(11) unsigned    COMMENT '听哪位老师课' ,
    realize_channel int(11) unsigned    COMMENT '了解渠道，关联字典表' ,
    audition_counts int(11) unsigned   DEFAULT 0 COMMENT '试听次数,关联字典表' ,
    `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    solve_problem VARCHAR(512)    COMMENT '需解决的问题' ,
    PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT = '订单补充表 用于存储第三方渠道的课程生成的订单信息';;

-- 36
DROP TABLE IF EXISTS `order_logistics`;
CREATE TABLE order_logistics(
    id INT(11) unsigned NOT NULL AUTO_INCREMENT  COMMENT '订单主键 同时也是订单编号' ,
    user_id int(11) unsigned NOT NULL   COMMENT '用户id' ,
    order_id INT(11) unsigned NOT NULL   COMMENT '订单id' ,
    order_no VARCHAR(32)    COMMENT '订单号' ,
    `logistics_no` varchar(32) NOT NULL COMMENT '物流单号',
    `user_address_id` int(11) unsigned NOT NULL COMMENT '收货地址ID',
    receiver_name VARCHAR(32) NOT NULL   COMMENT '收货人姓名' ,
    receiver_mobile VARCHAR(16) NOT NULL   COMMENT '收货人手机号' ,
--     track_company_name VARCHAR(32)    COMMENT '快递公司名称 快递公司名称' ,
--     track_company_code VARCHAR(32)    COMMENT '快递公司编号 快递公司编号' ,
    logistics_fees INT(11) unsigned  DEFAULT 0 COMMENT '邮费 默认可以为零，代表包邮' ,
    sender_id int(11) unsigned COMMENT '发件人ID,关联字典表（json）' ,
    sender_name VARCHAR(32)    COMMENT '寄件人姓名' ,
    sender_address VARCHAR(32)    COMMENT '寄件人地址' ,
    sender_mobile VARCHAR(32)    COMMENT '寄件人手机号' ,
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT = '物流信息表 ';;
ALTER TABLE order_logistics ADD INDEX IDX_ORDERS_CREATETIME(create_time);;
ALTER TABLE order_logistics ADD UNIQUE IDX_UK_ORDERS_LOGISTICS_NO(logistics_no);;
ALTER TABLE order_logistics COMMENT '物流信息表';

-- 37
DROP TABLE IF EXISTS goods;
CREATE TABLE goods(
    id INT(11) unsigned NOT NULL AUTO_INCREMENT  COMMENT '主键id 唯一标识' ,
    course_id int(11) unsigned NOT NULL COMMENT '关联课程ID',
    item_name VARCHAR(32) NOT NULL   COMMENT '商品名称' ,
    item_img VARCHAR(256) NOT NULL   COMMENT '商品图片（缩略图）' ,
--  判断是否需要发货吗？目前商品只有课程
    item_type VARCHAR(32) NOT NULL   COMMENT '商品类型' ,
    `summary` varchar(512) DEFAULT NULL COMMENT '简介',
    `description` blob DEFAULT NULL COMMENT '图文详情',
    price_vip INT COMMENT '会员价 单位分' ,
--  是限制购买数量？
    buy_counts INT NOT NULL COMMENT '购买数量' ,
    price_normal INT COMMENT '原始价格 单位:分' ,
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT = '商品表 ';;
ALTER TABLE goods COMMENT '商品表';


-- 38
DROP TABLE IF EXISTS express_basic_info ;
CREATE TABLE express_basic_info(
   id INT NOT NULL AUTO_INCREMENT  COMMENT '主键 id' ,
   company_code VARCHAR(64)  NOT NULL  COMMENT '快递公司编码' ,
   company_name VARCHAR(64) NOT NULL   COMMENT '快递公司名称' ,
   company_type VARCHAR(64) NOT NULL   COMMENT '快递公司类型' ,
   PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT = '快递公司维护表 ';
-- ----------------------------------------------------------------------------------------------------------------------------
-- 营销
-- ----------------------------------------------------------------------------------------------------------------------------
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
-- 39
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '优惠券名称',
  `description` varchar(64) DEFAULT NULL COMMENT '优惠券描述',
  `grade_id` int(11) unsigned DEFAULT NULL COMMENT '所属学科id:(JAVA、大数据,对应字典表)',
  `preferential_way` tinyint(2) unsigned DEFAULT NULL COMMENT '优惠方式 1.打折 2. 满减3:立减',
  `range` tinyint(2) unsigned DEFAULT NULL COMMENT '优惠范围 1：全场 2：指定商品  3：指定科目',
  `amount` int(11) unsigned DEFAULT NULL COMMENT '优惠券数量',
  `send_amount` int(11) unsigned DEFAULT NULL COMMENT '优惠券发放数量',
  `valid_date` datetime DEFAULT NULL COMMENT '有效时间',
  `start_time` datetime DEFAULT NULL COMMENT '优惠券开始使用日期',
  `end_time` datetime DEFAULT NULL COMMENT '优惠券截止使用日期',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '优惠券状态1.启用 2.停用 3.失效',
  `issued_way` tinyint(2) unsigned DEFAULT NULL COMMENT '发行方式  1:私密发行 2：批量发行 3: 公开发行',
  `creator_id` int(11) unsigned DEFAULT NULL COMMENT '创建者',
  `updater_id` int(11) unsigned DEFAULT NULL COMMENT '更新者',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='优惠券';

-- 8 用户优惠券表
-- 40 营销模块维护
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户id',
  `coupon_id` int(11) unsigned NOT NULL  COMMENT '优惠券ID',
--   `available_state` tinyint(1) unsigned  NOT NULL default '0' COMMENT '可用状态 0-已使用 1-未使用 2-锁定 3-已过期',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户优惠券表';

-- ----------------------------
-- Table structure for coupon_goods_relation
-- ----------------------------
-- 41
DROP TABLE IF EXISTS `coupon_goods_relation`;
CREATE TABLE `coupon_goods_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠券ID',
  `goods_id` int(11) unsigned DEFAULT NULL COMMENT '商品ID',
  `goods_type` tinyint(1) unsigned DEFAULT '1' COMMENT '商品类型  1课程 （扩展字段）',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT '优惠券商品关系表';

-- ----------------------------
-- Table structure for coupon_subject_relation
-- ----------------------------
-- 42
DROP TABLE IF EXISTS `coupon_subject_relation`;
CREATE TABLE `coupon_subject_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠券ID',
  `grade_id` int(11) unsigned DEFAULT NULL COMMENT '所属学科id:(JAVA、大数据,对应字典表)',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT '优惠券科目关系表';

-- ----------------------------
-- Table structure for coupon_preferential_strategy
-- ----------------------------
-- 43
DROP TABLE IF EXISTS `coupon_preferential_strategy`;
CREATE TABLE `coupon_preferential_strategy` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
`coupon_id` int(11) DEFAULT NULL COMMENT '优惠券ID',
`full_price` decimal(10,2) DEFAULT NULL COMMENT '满金额',
`reduce_price` decimal(10,2) DEFAULT NULL COMMENT '减金额',
`discount` decimal(10,2) DEFAULT NULL COMMENT '打折',
`status` tinyint(2) DEFAULT '1' COMMENT '状态 1生效  0失效',
`gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC  COMMENT='优惠券-优惠策略表';

-- ----------------------------
-- Table structure for coupon_receive_limit
-- ----------------------------
-- 44
DROP TABLE IF EXISTS `coupon_receive_limit`;
CREATE TABLE `coupon_receive_limit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠卷ID',
  `count` int(11) unsigned DEFAULT NULL COMMENT '每个用户领取张数',
  `user_limit` tinyint(2) unsigned DEFAULT NULL COMMENT '用户领取身份限制0:所有用户 1：会员  2：vip身份',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='优惠券-领取限制表';

-- ----------------------------
-- Table structure for coupon_send_log
-- ----------------------------
-- 45
DROP TABLE IF EXISTS `coupon_send_log`;
CREATE TABLE `coupon_send_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠券ID',
  `send_count` int(11) unsigned DEFAULT NULL COMMENT '发放数量',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='优惠券发送记录';

-- ----------------------------
-- Table structure for coupon_use_limit
-- ----------------------------
-- 46
DROP TABLE IF EXISTS `coupon_use_limit`;
CREATE TABLE `coupon_use_limit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠卷ID',
  `count` int(11) unsigned DEFAULT '1' COMMENT '单次可使用张数',
  `is_overlay` tinyint(2) unsigned DEFAULT NULL COMMENT '是否可以叠加使用 1 叠加 0不叠加',
  `user_limit` tinyint(2) unsigned DEFAULT NULL COMMENT '用户使用身份限制0:所有用户 1：会员  2：vip身份',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='优惠券使用限制表';

-- ----------------------------
-- Table structure for coupon_use_log (删除)
-- ----------------------------
-- DROP TABLE IF EXISTS `coupon_use_log`;
-- CREATE TABLE `coupon_use_log` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
--   `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠券ID',
--   `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
--   order_id INT(11) unsigned NOT NULL   COMMENT '订单id' ,
--   `use_description` varchar(64) DEFAULT NULL COMMENT '使用说明',
--   `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券用户使用记录表';

-- ----------------------------
-- Table structure for coupon_user_relation (删除)
-- -- ----------------------------
-- DROP TABLE IF EXISTS `coupon_user_relation`;
-- CREATE TABLE `coupon_user_relation` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
--   `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠券ID',
--   `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
--   `valid_time` datetime DEFAULT NULL COMMENT '有效时间',
--   `coupon_status` tinyint(2) unsigned DEFAULT '0' COMMENT '优惠卷状态 0：未领取 1:正常 2:失效 3：已经使用',
--   `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
--   `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户-优惠卷关系表';

-- ----------------------------
-- Table structure for flash_promotion
-- ----------------------------
-- 47
DROP TABLE IF EXISTS `flash_promotion`;
CREATE TABLE `flash_promotion` (
  `id` int(11) unsigned NOT NULL COMMENT '限时优惠活动ID',
  `goods_id` int(11) unsigned DEFAULT NULL COMMENT '商品 ID',
  `course_price` decimal(10,2) DEFAULT NULL COMMENT '课程价格',
  `preferential_price` decimal(10,2) DEFAULT NULL COMMENT '优惠价格',
  `start_time` datetime DEFAULT NULL COMMENT '限时优惠开始使用日期',
  `end_time` datetime DEFAULT NULL COMMENT '限时优惠截止使用日期',
  `is_limit` tinyint(2) unsigned DEFAULT NULL COMMENT '是否限制 0 不限制 1 限制',
  `available_quantity` int(11) unsigned DEFAULT '0' COMMENT '可用数量 ',
  `used_quantity` int(11) unsigned DEFAULT '0' COMMENT '已用数量',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '活动状态 0 未开始 1 进行中 2 已失效 ',
  `creator_id` int(11) unsigned DEFAULT NULL COMMENT '创建者',
  `updater_id` int(11) unsigned DEFAULT NULL COMMENT '更新着',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='限时优惠活动表';


-- ----------------------------------------------------------------------------------------------------------------------------
-- 问答
-- ----------------------------------------------------------------------------------------------------------------------------
DROP TABLE IF EXISTS `ask_questions`;
CREATE TABLE `ask_questions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `category_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '类别ID',
  `title` varchar(255) NOT NULL COMMENT '问题标题',
  `description` text COMMENT '问题描述',
  `price` smallint(6) unsigned DEFAULT '0' COMMENT '价格',
  `hide` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '是否隐藏？',
  `answers` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '采纳回答ID',
  `views` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查看次数',
  `followers` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '关注次数（？？？）',
  `collections` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '收藏次数',
  `comments` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论次数',
  `device` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '设备',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '状态 0   1   2',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `opt_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型',
  `istop` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否置顶',
  `top_at` timestamp NULL DEFAULT NULL COMMENT '置顶时间',
  `editorType` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ueditor' COMMENT '内容类型（ueditor  markdown）',
  `mddescription` text COMMENT 'markdown内容',
  `supports` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '支持数量',
  `oppositions` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '反对数量',
  `open_area` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'all' COMMENT '公开区域(all：所有，vip：对vip)',
  `draft` tinyint(4) unsigned DEFAULT '0' COMMENT '是否保存为草稿（0：否，1：是）',
  `to_user_id` int(10) unsigned DEFAULT NULL COMMENT '邀请用户id',
  `adopt_user_id` int(10) unsigned DEFAULT NULL COMMENT '采纳用户id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `questions_created_at_index` (`created_at`) USING BTREE,
  KEY `questions_updated_at_index` (`updated_at`) USING BTREE,
  KEY `questions_user_id_index` (`user_id`) USING BTREE,
  KEY `questions_title_index` (`title`) USING BTREE,
  KEY `questions_category_id_index` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5596 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='问题表';

DROP TABLE IF EXISTS `ask_question_activities`;
CREATE TABLE `ask_question_activities` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `total_day` int(11) unsigned DEFAULT '0' COMMENT '累计提问天数',
  `total_questions` int(11) unsigned DEFAULT '0' COMMENT '累计提问次数',
  `total_answers` int(11) unsigned DEFAULT '0' COMMENT '累计回答次数',
  `total_shares` int(11) unsigned DEFAULT '0' COMMENT '累计分享次数',
  `total_number` int(11) unsigned DEFAULT '0' COMMENT '累计奖励次数',
  `opt_type` tinyint(4) unsigned DEFAULT '1' COMMENT '是否删除(0：已删除，1：未删除)',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `level` tinyint(4) unsigned DEFAULT '0' COMMENT '记录用户完成等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='提问问题活动表';


-- 求助记录表（目前不涉及）
DROP TABLE IF EXISTS `ask_question_invitations`;
CREATE TABLE `ask_question_invitations` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `from_user_id` int(10) unsigned NOT NULL DEFAULT '0',
  `user_id` int(10) unsigned NOT NULL,
  `send_to` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `question_id` int(10) unsigned NOT NULL,
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' ,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `question_invitations_user_id_index` (`user_id`) USING BTREE,
  KEY `question_invitations_question_id_index` (`question_id`) USING BTREE,
  KEY `question_invitations_send_to_index` (`send_to`) USING BTREE,
  KEY `question_invitations_from_user_id_index` (`from_user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24467 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='求助记录表';

DROP TABLE IF EXISTS `ask_answers`;
CREATE TABLE `ask_answers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `question_title` varchar(255) NOT NULL COMMENT '问题标题',
  `question_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '问题ID',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `content` text NOT NULL COMMENT '回答内容',
  `supports` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '支持数量',
  `oppositions` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '反对数量',
  `comments` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '回复数量',
  `device` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '设备',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  `adopted_at` timestamp NULL DEFAULT NULL COMMENT '采用时间',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `opt_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型',
  `editorType` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ueditor' COMMENT '编辑器类型  ueditor  markdown',
  `mdcontent` text COMMENT 'markdown内容',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `answers_created_at_index` (`created_at`) USING BTREE,
  KEY `answers_updated_at_index` (`updated_at`) USING BTREE,
  KEY `answers_question_id_index` (`question_id`) USING BTREE,
  KEY `answers_user_id_index` (`user_id`) USING BTREE,
  KEY `answers_adopted_at_index` (`adopted_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21235 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='回答表';

DROP TABLE IF EXISTS `ask_articles`;
CREATE TABLE `ask_articles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_id` int(11) unsigned DEFAULT '0',
  `title` varchar(255) DEFAULT NULL  COMMENT '文章标题',
  `summary` varchar(255) DEFAULT NULL  COMMENT '文章简介',
  `content` mediumtext COMMENT '文章内容',
  `views` int(10) unsigned DEFAULT '0' COMMENT '查看数量',
  `collections` int(10) unsigned DEFAULT '0' COMMENT '收藏数量',
  `comments` int(10) unsigned DEFAULT '0' COMMENT '评论数量',
  `supports` int(10) unsigned DEFAULT '0' COMMENT '支持数量',
  `status` tinyint(4) unsigned DEFAULT '0',
  `device` tinyint(4) unsigned DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `opt_type` tinyint(4) unsigned DEFAULT '1' COMMENT '操作类型',
  `istop` tinyint(3) unsigned DEFAULT '0' COMMENT '是否置顶(1:置顶，0:否)',
  `top_at` timestamp NULL DEFAULT NULL COMMENT '置顶时间',
  `editorType` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'ueditor' COMMENT '编辑器类型  ueditor  markdown',
  `mdcontent` mediumtext  COMMENT '文章内容 markdown',
  `open_area` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'all' COMMENT '公开区域(all：所有，vip：对vip)',
  `oppositions` int(10) unsigned DEFAULT '0' COMMENT '不推荐数量',
  `article_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文章类型（ origina：原创，repost：转载，translated：翻译）',
  `confidential` tinyint(4) unsigned DEFAULT '0' COMMENT '是否私密文章（0：否，1：是）',
  `draft` tinyint(4) unsigned DEFAULT '0' COMMENT '是否保存为草稿（0：否，1：是）',
  `source_id` int(11) unsigned DEFAULT NULL COMMENT '后台爬取来源文章id',
  `source_type` varchar(100) DEFAULT NULL COMMENT '后台爬取来源文章类型(language:编程语言、soft:软件更新、generic：综合咨询、industry：行业咨询)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `articles_user_id_index` (`user_id`) USING BTREE,
  KEY `articles_category_id_index` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2097 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文章表';

DROP TABLE IF EXISTS `ask_comments`;
CREATE TABLE `ask_comments` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL  COMMENT '评论用户ID',
  `content` text NOT NULL  COMMENT '评论内容',
  `source_id` int(10) unsigned NOT NULL  COMMENT '被评论的ID',
  `source_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '"App\\Models\\Article","文章"
                                                                                        "App\\Models\\Question","问答"
                                                                                        "App\\Models\\Answer","回答"
                                                                                        "App\\Models\\Tag","标签"
                                                                                        "App\\Models\\User","用户"
                                                                                        "App\\Models\\Comment","评论"
                                                                                        "App\\Models\\Bubble","冒泡"
                                                                                        "App\\Models\\Works", "作业"
                                                                                        "App\\Models\\Subject", "题目"
                                                                                        "App\\Models\\GOODS","商品"
                                                                                        "App\\Models\\Course","录播课程',
  `to_user_id` int(10) unsigned DEFAULT NULL  COMMENT '评论来源用户的ID',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '1'  COMMENT '评论状态',
  `supports` int(11) unsigned NOT NULL DEFAULT '0'  COMMENT '点赞数量（应该没用）',
  `device` tinyint(4) unsigned NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `opt_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `comments_source_id_source_type_index` (`source_id`,`source_type`) USING BTREE,
  KEY `comments_user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14317 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='评论表';

DROP TABLE IF EXISTS `ask_taggables`;
CREATE TABLE `ask_taggables` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tag_id` int(10) unsigned NOT NULL COMMENT '标签的ID',
  `taggable_id` int(10) unsigned NOT NULL COMMENT '文章或者问答的ID',
  `taggable_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'App\\Models\\Article","文章"
                                                                         App\\Models\\Question","问答"',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `opt_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `taggables_taggable_id_taggable_type_index` (`taggable_id`,`taggable_type`) USING BTREE,
  KEY `taggables_tag_id_index` (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23466 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='提问文章标签关联表';

DROP TABLE IF EXISTS `ask_tags`;
CREATE TABLE `ask_tags` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `category_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '分类ID？？',
  `logo` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `description` text COMMENT '描述',
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '上级标签ID （没用）',
  `followers` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '支持数量',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `opt_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tags_name_unique` (`name`) USING BTREE,
  KEY `tags_parent_id_index` (`parent_id`) USING BTREE,
  KEY `tags_followers_index` (`followers`) USING BTREE,
  KEY `tags_category_id_index` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1426 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='标签表';

DROP TABLE IF EXISTS `ask_categories`;
CREATE TABLE `ask_categories` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0',
  `grade` int(11) unsigned NOT NULL DEFAULT '1' COMMENT '等级',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `slug` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sort` int(11) unsigned NOT NULL DEFAULT '0',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` smallint(6) unsigned NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `opt_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `categories_slug_unique` (`slug`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='分类表';

DROP TABLE IF EXISTS `ask_categories_tags`;
CREATE TABLE `ask_categories_tags` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `categories_id` int(10) unsigned NOT NULL COMMENT '分类id',
  `tags_id` int(10) unsigned NOT NULL COMMENT '标签id',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21415 DEFAULT CHARSET=utf8 COMMENT='分类标签关联表';

DROP TABLE IF EXISTS `ask_supports`;
CREATE TABLE `ask_supports` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `session_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(10) unsigned DEFAULT NULL COMMENT '用户ID',
  `supportable_id` int(10) unsigned NOT NULL COMMENT '点赞对象表ID',
  `supportable_type` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '点赞对象类型',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `opt_type` tinyint(4) unsigned NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `supports_supportable_id_supportable_type_index` (`supportable_id`,`supportable_type`) USING BTREE,
  KEY `supports_session_id_index` (`session_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11139 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='点赞表';

-- =============================================================================
-- 管理后台
-- =============================================================================
-- 48 常见问题 属于管理后台模块
DROP TABLE IF EXISTS `user_question`;
CREATE TABLE `user_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `question` int(11) unsigned NOT NULL COMMENT '问题',
  `answer` int(11) unsigned NOT NULL COMMENT '答案',
	`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='常见问题';

-- 49 平台通知表
-- 应该管理后台 先放用户模块
DROP TABLE IF EXISTS `system_attention`;
CREATE TABLE `system_attention` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` tinyint(2) unsigned NOT NULL COMMENT '通知类型 1-后台录入通知 2-优惠卷过期提醒 3-收到优惠卷提醒 4-关注课程上架提醒 5-权益到期提醒 6-会员新季度书籍上架提醒',
  `source_id` int(11) unsigned DEFAULT NULL COMMENT '发送内容id(供链接跳转)',
  `subject` varchar(256) DEFAULT NULL COMMENT '标题',
  `url` varchar(256) DEFAULT null COMMENT '通知内容url',
  `content` varchar(512) COMMENT '内容',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '0-未删除 1-删除',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT =DYNAMIC COMMENT='平台通知表';

-- 新增 平台通知用户关联表
DROP TABLE IF EXISTS `user_system_attention`;
CREATE TABLE `user_system_attention` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `system_attention_id` int(11) unsigned NOT NULL COMMENT '平台通知id',
  `user_unique_code` varchar(64)  DEFAULT NULL COMMENT '用户ID',
  `is_send` tinyint(1) unsigned DEFAULT '1' COMMENT '1-送达 0-未送达',
  `is_read` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已读 0-未读 1-已读',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '0-未删除 1-删除',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT =DYNAMIC COMMENT='平台通知用户关联表';


-- 50 课程咨询表
DROP TABLE IF EXISTS `course_feedback`;
CREATE TABLE `course_feedback` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '自关联ID',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '课程ID',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户ID',
  `seller_id` int(11) unsigned DEFAULT NULL COMMENT '销售ID',
  `content` blob DEFAULT NULL COMMENT '图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT =DYNAMIC COMMENT='课程咨询表';

-- 51 分享记录表
DROP TABLE IF EXISTS `user_course_share`;
CREATE TABLE `user_course_share` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) unsigned DEFAULT NULL COMMENT '用户id',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '课程id',
  `category_id` int(11) unsigned DEFAULT 0 COMMENT '课程分类id（训练营、精品小课、vip课，对应字典表）',
  `channel` varchar(100) DEFAULT NULL COMMENT '分享类型（qq、wechat）',
  `message` varchar(1024) DEFAULT NULL COMMENT '分享感言',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT =DYNAMIC COMMENT='分享记录表';

-- 52 首页banner表
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sort` int(11) unsigned DEFAULT NULL COMMENT 'banner排序',
  `banner_image` varchar(255) DEFAULT NULL COMMENT 'banner图片',
  `banner_url` varchar(255) DEFAULT NULL COMMENT 'banner跳转对象地址',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '0-未删除 1-删除',
  `create_time` timestamp DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT =DYNAMIC COMMENT='首页banner表';


-- 53 首页管理表 : 减少查询压力： 这里就存相关的ID  作为一个中间表 ： 课程 和 首页自己关联
DROP TABLE IF EXISTS `home_page_manage`;
CREATE TABLE `home_page_manage` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(1) unsigned not null comment '首页栏位类型: 1:banner  2： 训练营 3： 课程预告 ',
  `sort` int(11) unsigned DEFAULT NULL COMMENT '分类  排序',
  `banner_id` varchar(256) DEFAULT NULL COMMENT '首页bannerid，json格式按顺序存放',
  `train_id` varchar(256) DEFAULT NULL COMMENT '训练营id，json格式按顺序存放',
  `forecast_id` varchar(256) DEFAULT NULL COMMENT '课程预告id，json格式按顺序存放',
  `isdelete` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT =DYNAMIC COMMENT='首页管理表';

-- 54
DROP TABLE IF EXISTS `search_hot_word`;
CREATE TABLE `search_hot_word` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '搜索热词ID',
   `hot_word` varchar(200) NOT NULL COMMENT '搜索热词',
   `sort_num` int(11) unsigned NOT NULL COMMENT '排序字段',
   `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '可用状态 1可用 0禁用',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT =DYNAMIC COMMENT='搜索热词表';

-- 55
DROP TABLE IF EXISTS `search_history`;
CREATE TABLE `search_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '搜索历史ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `keyword` varchar(200) NOT NULL COMMENT '搜索关键词',
  `search_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '搜索时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='搜索历史表';

