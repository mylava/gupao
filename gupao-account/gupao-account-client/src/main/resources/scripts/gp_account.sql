-- 1用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  -- 长度32
  `email` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机号',
  `qq` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'qq号',
  `user_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `avatar` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `open_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方绑定，用户qq openId',
  `union_id` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '第三方绑定，用户微信 unionId',
  -- tinyint(2)
  `age` tinyint(2) DEFAULT NULL COMMENT '年龄',
  -- tinyint(1)
  `gender` tinyint(1) NOT NULL default 1 COMMENT '1-男 2-女',
  `vip_num` int(11) NOt NULL  default  0  COMMENT 'vip学号',
  user_status tinyint(1) COMMENT '1-白名单 2-黑名单',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  -- 主键使用pk_表名_字段名
  PRIMARY KEY pk_user_id (`id`) USING BTREE,
  -- 唯一索引使用uk_表名_字段名
  UNIQUE KEY `uk_user_mobile` (`mobile`) USING BTREE COMMENT '唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 2购买课程表
DROP TABLE IF EXISTS `vip_course`;
CREATE TABLE `vip_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `vip_audit_status` varchar(20)DEFAULT NULL COMMENT '学员入学审核状态 1-通过 0-未通过',
  `vip_course_type` int(11) DEFAULT NULL COMMENT '报名学科，1-java构架 2-人工智能 3-大数据 4-软件测试..',
  `vip_course_id` int(11) DEFAULT NULL COMMENT '课程id',
  `vip_course_name` varchar(32) NOT NULL default 1 COMMENT '课程名称',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1-删除 0-未删除',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  -- 主键使用pk_表名_字段名
  PRIMARY KEY pk_vip_course_id (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学员购买课程表';

-- 3登录历史表
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `access_token` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录token',
  `plat_form` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'app平台(ios,android)',
  `device_type` tinyint(2) DEFAULT NULL COMMENT '0-未知  1-小米 2-华为',
  -- 地理位置信息？ 需要app申请权限，可以先预留
  `device_token` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机器设备号',
  `login_ip` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `login_access` tinyint(2) DEFAULT NULL COMMENT '登录入口 1-app 2-社区 3-小程序',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未删除 1-删除',
  UNIQUE KEY `idx_uid_login_access` (`user_id`,`login_access`) USING BTREE COMMENT '用户和登录入口唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='登录信息表';


-- 4学币账户表
DROP TABLE IF EXISTS  `account_balance`;
CREATE TABLE `account_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `balance` int(11) NOT NULL DEFAULT 0 COMMENT '余额(分为单位)',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '1-删除 0-未删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_id` (`user_id`) USING BTREE COMMENT '用户id索引'
) ENGINE=InnoDB AUTO_INCREMENT=11200000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学币账户表';

-- 5学币流水记录表
DROP TABLE IF EXISTS `account_balance_record`;
CREATE TABLE `account_balance_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  order_id varchar(32) DEFAULT NULL COMMENT '订单号id',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '消费金额',
  `type` tinyint(2) DEFAULT NULL COMMENT '1-充值 2 -消费',
  `desc` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '1-删除 0-未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学币流水记录表';

-- 6优惠券账户表
DROP TABLE IF EXISTS `account_coupon`;
CREATE TABLE `account_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `coupon_id` int(11) NOT NULL  COMMENT '优惠券ID',
   use_status tinyint(1)  NOT NULL default '0' COMMENT '优惠卷使用情况 1-已使用 0-未使用',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '1-删除 0-未删除',
  PRIMARY KEY pk_coupon_id (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户优惠券表';

-- 7优惠券使用记录表
DROP TABLE IF EXISTS `account_coupon_record`;
CREATE TABLE `account_coupon_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
   order_id varchar(32) NOT NULL  COMMENT '订单ID',
  `coupon_id` int(11) NOT NULL  COMMENT '优惠券ID',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(2) DEFAULT '0' COMMENT '1-删除 0-未删除',
  PRIMARY KEY pk_coupon_id (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='优惠券使用记录表';

-- 8学员工作经历表
DROP TABLE IF EXISTS `vip_work`;
CREATE TABLE `vip_work` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
   mobile varchar (11)  DEFAULT NULL COMMENT '手机号',
  `user_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `company` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所在公司',
  -- 使用数据字典
  `work_age` int(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工作年限id',
  `position` int(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '岗位id',
  -- 使用数据字典
  `annual_salary` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '目前年薪',
  `expect_salary` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '期望年薪',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=475 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学员工作经历表';


-- 9关注表
CREATE TABLE `user_attention` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '关注者ID',
  `attention_user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '被关注ID',
  `created_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '0-未删除 1-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户关注表';

-- 10互动消息表
DROP TABLE IF EXISTS `interact_message`;
CREATE TABLE `interact_message` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `avatar` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主动互动方头像',
  `nick_name` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主动互动方昵称',
  `to_user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '接收人',
  `type` tinyint(1) NOT NULL COMMENT '通知类型 1-回答 2-评论 3-收藏 4-回复 5-评分',
  `source_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '1-问答 2-文章 3-答疑 4-作业',
  `source_id` int(10) unsigned DEFAULT NULL COMMENT '互动内容id',
  `subject` varchar(255) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) COMMENT '内容',
  `is_read` tinyint(1) DEFAULT '0' COMMENT '是否已读 0-未读 1-已读',
  `is_deleted` tinyint(1)  DEFAULT '0' COMMENT '0-未删除 1-删除',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2487 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='互动消息表';


-- 11平台通知表
DROP TABLE IF EXISTS `notifications`;
CREATE TABLE `notifications` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` char(32) NOT NULL COMMENT '通知类型 1-后台录入通知 2-优惠卷过期提醒 3-收到优惠卷提醒 4-关注课程上架提醒 5-权益到期提醒 6-会员新季度书籍上架提醒',
  `source_id` int(10) unsigned DEFAULT NULL COMMENT '发送内容id(供链接跳转)',
  `subject` varchar(255) DEFAULT NULL COMMENT '标题',
  `url` varchar(100) DEFAULT null COMMENT '通知内容url',
  `content` varchar(500) COMMENT '内容',
  `is_send` tinyint(1) DEFAULT '1' COMMENT '1-送达 0-未送达',
  `is_read` tinyint(1) DEFAULT '0' COMMENT '是否已读 0-未读 1-已读',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '0-未删除 1-删除',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2487 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='平台通知表';

-- 12私信表
DROP TABLE IF EXISTS `private_letter`;
CREATE TABLE `private_letter` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `to_user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `message` varchar(500) COMMENT '消息内容',
  `is_read` tinyint(1) DEFAULT '0' COMMENT '是否已读 0-未读 1-已读',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '0-未删除 1-删除',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2487 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='私信表';

-- 13我的课表
DROP TABLE IF EXISTS `my_applet`;
CREATE TABLE `my_applet` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT '用户名',
  `course_id` int(11) DEFAULT NULL COMMENT '课程ID',
  `course_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '课程名',
  `course_title` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '科目主题，例如"并发编程从入门到入魔"',
  `notice_week_day` tinyint(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '1-7 代表第几周',
  `notice_time` varchar(17) NOT NULL DEFAULT '' COMMENT '例如 12:00',
  `created_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='我的课表';

-- 14课程收藏表
DROP TABLE IF EXISTS `course_collections`;
CREATE TABLE `course_collections` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_id` INT(10) UNSIGNED NOT NULL COMMENT '用户id',
  `course_id` INT(10) UNSIGNED NOT NULL COMMENT '课程id',
  `created_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=INNODB AUTO_INCREMENT=2346 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='课程收藏表';

-- 15地址表
CREATE TABLE `user_address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `receiver` varchar(16) DEFAULT NULL COMMENT '收件人',
  `mobile` varchar(16) DEFAULT NULL COMMENT '联系电话',
  `province` varchar(12) DEFAULT NULL COMMENT '省份',
  `city` varchar(12) DEFAULT NULL COMMENT '市',
  `district` varchar(12) DEFAULT NULL COMMENT '区',
  `address` varchar(64) DEFAULT NULL COMMENT '详细地址',
  is_default tinyint(1)  NOT NULL default 0 COMMENT '地址默认 1-是 0-否',
  `is_deleted` tinyint(1) NOT NULL default 0 COMMENT '1-删除 0-未删除',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户地址表';

-- 16会员表
CREATE TABLE `member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键会员ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `effective_time` timestamp DEFAULT NULL COMMENT '生效日期',
  `failure_time` timestamp DEFAULT NULL COMMENT '失效日期',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '1-删除 0-未删除',
  `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='会员表';

-- 17 会员权益关联表
DROP TABLE IF EXISTS `member_rights_relation`;
CREATE TABLE `member_rights_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `member_id` int(11) NOT NULL COMMENT '会员ID（领取人）',
  `rights_dirc_id` int(11) NOT NULL COMMENT '权益字典ID',
  `receive_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-未领取 1-已领取' ,
  `receive_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '领取时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员权益关联表';

-- 18权益领取表
DROP TABLE IF EXISTS `rights_receive`;
CREATE TABLE `rights_receive` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID（领取人）',
  `member_id` int(11) NOT NULL COMMENT '会员ID（领取人）',
  `rights_dirc_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '权益字典ID',
  `resource_type` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '权益资源类型:优惠卷-1 书籍-2',
  `right_resource_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '资源id',
	 `created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权益领取表';

-- 19问答表
DROP TABLE IF EXISTS `user_question`;
CREATE TABLE `user_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `question` int(11) unsigned NOT NULL COMMENT '问题',
  `answer` int(11) NOT NULL COMMENT '答案',
  `creater_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `is_deleted` tinyint(1) NOT NULL default 0 COMMENT '1-删除 0-未删除',
	`created_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='问答表';


alter  table my_applet add column `course_type` tinyint(1) NOT NULL default 1 COMMENT '课程类型: 1-录播 2-直播';


