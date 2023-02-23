/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost
 Source Database       : gupao

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : utf-8

 Date: 04/07/2020 00:01:22 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================================================
-- 用户
-- =============================================================================
-- 1 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码,所有关联用户的标使用这个字段作为外键',
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
  UNIQUE KEY `uk_user_mobile` (`mobile`) USING BTREE COMMENT '唯一索引',
  UNIQUE KEY `uk_user_unique_code` (`user_unique_code`) USING BTREE COMMENT '用户编码唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 2 登录历史记录表
DROP TABLE IF EXISTS `user_login_history`;
CREATE TABLE `user_login_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码,所有关联用户的标使用这个字段作为外键',
  `os_type` tinyint(2) unsigned DEFAULT NULL COMMENT 'app平台:1-ios 2-android)',
  `device_type` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '设备类型(机型)',
  `geographical_location` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地理位置',
  `device_no` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机器设备号',
  `ip_address` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录ip',
  `service_type` tinyint(2) unsigned DEFAULT NULL COMMENT '登录入口 1-app 2-社区 3-小程序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_unique_code` (`user_unique_code`) USING BTREE COMMENT '用户编码唯一索引',
  CONSTRAINT `user_login_history_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='登录历史记录表';

-- 3 用户详细信息表(包含职业生涯)
DROP TABLE IF EXISTS `user_info_detail`;
CREATE TABLE `user_info_detail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码,所有关联用户的标使用这个字段作为外键',
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
  `job_title` int(11) unsigned DEFAULT NULL COMMENT '职位名称ID:对应字典表',
  `current_salary` int(3) unsigned DEFAULT NULL COMMENT '目前年薪:单位万',
  `expect_salary` int(4) unsigned DEFAULT NULL COMMENT '期望年薪:单位万',
  `introduction` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '自我介绍',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_unique_code` (`user_unique_code`) USING BTREE COMMENT '用户编码唯一索引',
  CONSTRAINT `user_info_detail_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户详细信息表';

-- 4 用户收货地址表
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码,所有关联用户的标使用这个字段作为外键',
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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_unique_code` (`user_unique_code`) USING BTREE COMMENT '用户编码唯一索引',
  CONSTRAINT `user_address_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='用户收货地址表';

-- 5 会员表 资源领取的权限校验
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键会员ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码,所有关联用户的标使用这个字段作为外键',
  `effective_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效日期',
  -- 添加字段
  `invalid_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '失效日期',
  `duration` int(11) unsigned DEFAULT '0' COMMENT '购买时长',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_unique_code` (`user_unique_code`) USING BTREE COMMENT '用户编码唯一索引',
  CONSTRAINT `member_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='会员表';

-- 6 学币账户表
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码',
  `amount` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '金额(分为单位)',
  `available_amount` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '可用金额(分为单位)',
  `frozen_amount` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '冻结金额(分为单位)',
  `account_state` tinyint(2) unsigned DEFAULT NULL COMMENT '账户状态: 1-正常 2-冻结',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_unique_code` (`user_unique_code`) USING BTREE COMMENT '用户编码唯一索引',
  CONSTRAINT `user_account_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学币账户表';

-- 7 账户流水记录表
DROP TABLE IF EXISTS `user_account_history`;
CREATE TABLE `user_account_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `amount` int(11) DEFAULT NULL COMMENT '金额/数量',
  `action_type` tinyint(2) unsigned DEFAULT NULL COMMENT '操作类型：1-充值 2 -消费',
  `desc` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_unique_code` (`user_unique_code`) USING BTREE COMMENT '用户编码唯一索引',
  KEY `order_no` (`order_no`),
  CONSTRAINT `user_account_history_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_account_history_ibfk_2` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学币流水记录表';

-- 8 会员权益表
DROP TABLE IF EXISTS `member_right`;
CREATE TABLE `member_right` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `right_name` varchar(32) NOT NULL COMMENT '权益名称',
  `right_type` tinyint(2) unsigned NOT NULL COMMENT '权益类型：1 直接发放 2 主动领取',
  `right_resource_type` tinyint(2) unsigned NOT NULL COMMENT '权益资源类型：1优惠券 2书籍',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员权益表';

-- 9 权益资源表
DROP TABLE IF EXISTS `member_right_resource`;
CREATE TABLE `member_right_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_right_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '权益ID',
  `resource_type` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '资源分类：1书籍 2优惠券 3文化衫',
  `resource_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '关联其他资源ID,比如优惠券模板ID',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `summary` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源简介',
  `cost` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '现价，有效价格',
  `price` double(14,2) NOT NULL DEFAULT '0.00' COMMENT '原价，无效价格',
  `description` blob NOT NULL COMMENT '资源图文描述',
  `invalid_state` tinyint(1) NOT NULL COMMENT '过期状态：0未过期 1已过期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `member_right_id` (`member_right_id`),
  CONSTRAINT `member_right_resource_ibfk_1` FOREIGN KEY (`member_right_id`) REFERENCES `member_right` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权益资源表';

-- 10 会员与权益关联表
DROP TABLE IF EXISTS `member_right_rel`;
CREATE TABLE `member_right_rel` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码',
  `member_right_id` int(11) unsigned NOT NULL COMMENT '关联[会员权益与资源关联表]的父权益ID',
  `gain_limit` tinyint(2) NOT NULL COMMENT '领取次数限制',
  `gain_times` tinyint(2) NOT NULL COMMENT '已领取次数',
  `invalid_time` timestamp NOT NULL COMMENT '权益过期时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `member_right_id` (`member_right_id`),
  CONSTRAINT `member_right_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `member_right_ibfk_2` FOREIGN KEY (`member_right_id`) REFERENCES `member_right` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会员与权益关联表';

-- 11 权益领取记录表
DROP TABLE IF EXISTS `member_right_gain_history`;
CREATE TABLE `member_right_gain_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码',
  `member_right_id` int(11) unsigned NOT NULL COMMENT '关联[会员权益与资源关联表]的权益ID',
  `resource_id` int(11) unsigned NOT NULL COMMENT '资源ID',
  `category_id` int(11) NOT NULL COMMENT '资源类型ID',
  `category_code` varchar(64) DEFAULT NULL COMMENT '资源类型编码',
  `member_id` int(11) unsigned NOT NULL COMMENT '会员ID（领取人）',
  `user_address_id` int(11) unsigned NOT NULL COMMENT '收货地址',
  `delivery_state` tinyint(2) unsigned NOT NULL COMMENT '物流状态：1待发货 2已发货 3已收货 4其他',
  `logistics_no` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物流单号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `member_right_id` (`member_right_id`),
  KEY `resource_id` (`resource_id`),
  KEY `user_address_id` (`user_address_id`),
  CONSTRAINT `member_right_gain_history_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `member_right_gain_history_ibfk_2` FOREIGN KEY (`member_right_id`) REFERENCES `member_right` (`id`),
  CONSTRAINT `member_right_gain_history_ibfk_3` FOREIGN KEY (`resource_id`) REFERENCES `member_right_resource` (`id`),
  CONSTRAINT `member_right_gain_history_ibfk_4` FOREIGN KEY (`user_address_id`) REFERENCES `user_address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权益领取记录表';

-- 12 关注表
DROP TABLE IF EXISTS `user_attention`;
CREATE TABLE `user_attention` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '关注者唯一编码',
  `attention_user_code` varchar(64) NOT NULL COMMENT '被关注者唯一编码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `attention_user_code` (`attention_user_code`),
  CONSTRAINT `user_attention_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_attention_ibfk_2` FOREIGN KEY (`attention_user_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户关注表';

-- 13 互动消息表
DROP TABLE IF EXISTS `user_interaction`;
CREATE TABLE `user_interaction` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` int(11) unsigned DEFAULT NULL,
  `from_unique_code` varchar(64) NOT NULL COMMENT '消息发送者',
  `target_unique_code` varchar(64) NOT NULL COMMENT '消息接收者',
  `resource_id` int(11) unsigned DEFAULT '0' COMMENT '对应问答、文章、答疑、作业、课程互动的ID',
  --
  `source_type_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '问答、文章、答疑、作业、课程互动 对应字典表',
  `attention_type_id` int(11) unsigned NOT NULL COMMENT '通知类型:对应字典表',
  `avatar` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主动互动方头像',
  `nick_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '主动互动方昵称',
  `subject` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `content` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `is_read` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已读 0-未读 1-已读',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '0-未删除 1-删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `from_unique_code` (`from_unique_code`),
  KEY `parent_id` (`parent_id`),
  KEY `source_type_id` (`source_type_id`),
  KEY `attention_type_id` (`attention_type_id`),
  KEY `target_unique_code` (`target_unique_code`),
  CONSTRAINT `user_interaction_message_ibfk_1` FOREIGN KEY (`from_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_interaction_message_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `user_interaction_message` (`id`),
  CONSTRAINT `user_interaction_message_ibfk_3` FOREIGN KEY (`source_type_id`) REFERENCES `dict` (`id`),
  CONSTRAINT `user_interaction_message_ibfk_4` FOREIGN KEY (`attention_type_id`) REFERENCES `dict` (`id`),
  CONSTRAINT `user_interaction_message_ibfk_5` FOREIGN KEY (`target_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='互动消息表';

-- 14 私信表
DROP TABLE IF EXISTS `personal_letter`;
CREATE TABLE `personal_letter` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `parent_id` int(11) unsigned NOT NULL COMMENT '消息父ID',
  `from_unique_code` varchar(64) NOT NULL COMMENT '消息发送者',
  `target_unique_code` varchar(64) NOT NULL COMMENT '消息接收者',
  `message` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '消息内容',
  `is_image` tinyint(1) unsigned DEFAULT '0' COMMENT '是否为图片 0-否 1-是',
  `is_read` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已读 0-未读 1-已读',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '0-未删除 1-删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`),
  KEY `from_unique_code` (`from_unique_code`),
  KEY `target_unique_code` (`target_unique_code`),
  CONSTRAINT `user_message_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `user_message` (`id`),
  CONSTRAINT `user_message_ibfk_2` FOREIGN KEY (`from_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_message_ibfk_3` FOREIGN KEY (`target_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='私信表';


-- =============================================================================
-- 课程
-- =============================================================================
-- 15 课程表
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `category_id` int(11) unsigned DEFAULT '0' COMMENT '课程分类id（训练营、精品小课、vip课，对应字典表）',
  `grade_id` int(11) unsigned DEFAULT NULL COMMENT '所属学科id:(JAVA、大数据,对应字典表)',
  `course_name` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程名',
  `course_title` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程主题名称',
  `image_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程封面图片',
  `view_num` int(11) unsigned DEFAULT '0' COMMENT '观看人数',
  `study_num` int(11) unsigned DEFAULT '0' COMMENT '已学人数',
  `comment_num` int(11) unsigned DEFAULT '0' COMMENT '评论人数',
  `attention_num` int(11) unsigned DEFAULT '0' COMMENT '关注人数',
  `praise_num` int(11) unsigned DEFAULT '0' COMMENT '好评数',
  `total_minutes` int(11) unsigned DEFAULT '0' COMMENT '课程视频总时长(秒)',
  `praise_score` float(2,2) unsigned DEFAULT '0.00' COMMENT '好评度',
  `average_score` float(2,2) unsigned DEFAULT '0.00' COMMENT '平均评分',
  `reply_count` int(6) unsigned DEFAULT '0' COMMENT '答疑数， 维护在课程表中,方便查询',
  `cost` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '现价，有效价格',
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '原价，无效价格',
  `lecturer_ids` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '老师的Id，多个老师用json {"1":"10001","2"...}',
  `description` blob COMMENT '图文详情，富文本应编码存储',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0未删除，1已删除',
  `start_Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '课程的开播时间',
  `endTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '课程的结束时间',
  `putaway_time` timestamp NULL DEFAULT NULL COMMENT '上架时间',
  `create_user_id` int(11) unsigned DEFAULT NULL COMMENT '创建者的userId',
  `update_user_id` int(11) unsigned DEFAULT NULL COMMENT '修改者的userId',
  -- 试听 可试听时间段  视频试听时长(5分钟)
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `category_id` (`category_id`),
  KEY `grade_id` (`grade_id`),
  KEY `lecturer_ids` (`lecturer_ids`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `dict` (`id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`grade_id`) REFERENCES `dict` (`id`),
  CONSTRAINT `course_ibfk_3` FOREIGN KEY (`lecturer_ids`) REFERENCES `lecturer` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='课程表';

-- 16 课程大纲表
DROP TABLE IF EXISTS `course_outline`;
CREATE TABLE `course_outline` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '大纲ID，主键',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父级目录ID',
  `course_id` int(11) unsigned NOT NULL COMMENT '所属课程ID',
  `title` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `sort` tinyint(2) unsigned DEFAULT NULL COMMENT '课程大纲排序',
  `video_or_live` tinyint(1) unsigned DEFAULT NULL COMMENT '录播还是直播  0录播 1直播',
  `video_or_stream_id` int(11) unsigned DEFAULT NULL COMMENT '关联视频ID（如果是直播，需创建直播表）',
  `outline_level` tinyint(2) unsigned DEFAULT '0' COMMENT '1 大纲（章-一级目录）2 是视频（节-二级目录）方便查询使用',
  `is_video` tinyint(1) unsigned DEFAULT '0' COMMENT '是否是视频  0否 1是 方便查询使用',
  `create_user_id` int(11) unsigned DEFAULT NULL COMMENT '创建者的userId',
  `update_user_id` int(11) unsigned DEFAULT NULL COMMENT '修改者的userId',
  -- 试听 可试听时间段  视频试听时长(5分钟)
  `on_try` tinyint(1) unsigned DEFAULT '0' COMMENT '是否可以试听 0-否 1-是',
  `try_time_start` timestamp NULL DEFAULT NULL COMMENT '试听活动开始时间',
  `try_time_end` timestamp NULL DEFAULT NULL COMMENT '试听活动结束时间',
  `limit_time` int(11) COMMENT '试听时长，单位秒 -1表示永久',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_outline_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `course_outline` (`id`),
  CONSTRAINT `course_outline_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='课程大纲表（关联视频资源）';

-- 17 视频资源表 1.用例图 2.怎么保存字段（有哪些、类型、长度） 3.限制（安全、关联）
DROP TABLE IF EXISTS `course_video`;
CREATE TABLE `course_video` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `grade_id` int(11) unsigned DEFAULT NULL COMMENT '所属学科id:(JAVA、大数据,对应字典表)',
  `title` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '视频标题',
  `duration` tinyint(11) unsigned NOT NULL DEFAULT '0' COMMENT '视频时长单位秒',
  `view_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `file_size` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '文件大小单位M',
  `audit_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态(-1:审核不通过，0:审核中，1:审核通过)',
  `cloud_video_state` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'unknown' COMMENT '预留字段：保利威云视频状态',
  `image_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '本视频的图片展示地址',
  `polyv_video_id` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '保利威视频ID',
  `create_user_id` int(11) unsigned DEFAULT NULL COMMENT '创建者的userId',
  `update_user_id` int(11) unsigned DEFAULT NULL COMMENT '修改者的userId',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `grade_id` (`grade_id`),
  CONSTRAINT `course_video_ibfk_1` FOREIGN KEY (`grade_id`) REFERENCES `dict` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='视频资源表';

-- 18 讲师详情表
DROP TABLE IF EXISTS `lecturer`;
CREATE TABLE `lecturer` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师名字',
  `summary` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师简介',
  `description` blob COMMENT '教师图文详情',
  `job_title` tinyint(2) unsigned DEFAULT NULL COMMENT '教师职位:对应字典表',
  `tag` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师标签',
  `mobile` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机',
  `qq` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'qq',
  `wechat` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `star` tinyint(1) unsigned DEFAULT NULL COMMENT '是否明星讲师 1是0否',
  `sort` tinyint(2) unsigned DEFAULT NULL COMMENT '教师排序',
  `state` tinyint(2) unsigned DEFAULT NULL COMMENT '讲师状态',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除 默认0否 1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator_id` int(11) unsigned DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='讲师详情表';

-- 19 课程评论表
DROP TABLE IF EXISTS `course_comment`;
CREATE TABLE `course_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '被评标的所属的课程ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '发表评论的用户编码',
  `nick_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发表评论的用户的呢称',
  `star` tinyint(2) unsigned DEFAULT '0' COMMENT '星级，一般最高为五星好评',
  `content` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论的内容',
  `office_replied` tinyint(1) unsigned DEFAULT '0' COMMENT '官方是否回复',
  `office_replied_content` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '官方回复内容',
  `course_time` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '已上课时间（秒）',
  `praise_or_negative` tinyint(2) unsigned DEFAULT NULL COMMENT '是否好评 0 未评价 1好评 2差评',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_id` (`course_id`),
  KEY `user_unique_code` (`user_unique_code`),
  CONSTRAINT `course_comment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `coupon` (`id`),
  CONSTRAINT `course_comment_ibfk_2` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='课程评论表';

-- 20 视频答疑问题表
DROP TABLE IF EXISTS `course_video_question`;
CREATE TABLE `course_video_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '问题ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `video_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '视频id',
  `point` int(6) unsigned DEFAULT NULL COMMENT '视频播放时间点(秒)',
  `title` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '问题标题',
  `content` blob NOT NULL COMMENT '问题内容',
  `reply_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '回答数',
  `browse_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '浏览人数 ',
  `audit_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态(-1:审核不通过，0:审核中，1:审核通过)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `video_id` (`video_id`),
  CONSTRAINT `course_video_question_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `course_video_question_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `course_video` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='视频答疑问题表';

-- 21 视频答疑回复表
DROP TABLE IF EXISTS `course_video_question_reply`;
CREATE TABLE `course_video_question_reply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `question_title` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '问题标题',
  `course_video_question_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '问题id',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `praise_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `negative_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '反对数量',
  `office_reply` tinyint(1) unsigned DEFAULT NULL COMMENT '是否官方回复',
  `content` blob COMMENT '回复内容',
  `is_adopted` tinyint(1) unsigned DEFAULT NULL COMMENT '是否被采纳',
  `adopted_time` timestamp NULL DEFAULT NULL COMMENT '采纳时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_video_question_id` (`course_video_question_id`),
  KEY `user_unique_code` (`user_unique_code`),
  CONSTRAINT `course_video_question_reply_ibfk_1` FOREIGN KEY (`course_video_question_id`) REFERENCES `course_video_question` (`id`),
  CONSTRAINT `course_video_question_reply_ibfk_2` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='视频答疑回复表';

-- 22 视频答疑回复评论表
DROP TABLE IF EXISTS `course_video_question_reply_comment`;
CREATE TABLE `course_video_question_reply_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '自关联ID',
  `course_video_question_reply_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '答疑回复id',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `praise_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `content` blob COMMENT '评论内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`),
  KEY `course_video_question_reply_id` (`course_video_question_reply_id`),
  KEY `user_unique_code` (`user_unique_code`),
  CONSTRAINT `course_video_question_reply_comment_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `course_homework_reply_comment` (`id`),
  CONSTRAINT `course_video_question_reply_comment_ibfk_2` FOREIGN KEY (`course_video_question_reply_id`) REFERENCES `course_homework_reply` (`id`),
  CONSTRAINT `course_video_question_reply_comment_ibfk_3` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='视频答疑回复评论表';

-- 23 答疑点赞表
DROP TABLE IF EXISTS `course_video_question_reply_praise`;
CREATE TABLE `course_video_question_reply_praise` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `course_video_question_reply_id` int(11) unsigned NOT NULL COMMENT '关联答疑回复表ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `course_video_question_reply_id` (`course_video_question_reply_id`),
  CONSTRAINT `course_video_question_reply_praise_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `course_video_question_reply_praise_ibfk_2` FOREIGN KEY (`course_video_question_reply_id`) REFERENCES `course_video_question_reply` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='答疑点赞表';

-- 24 作业问题表
DROP TABLE IF EXISTS `course_homework`;
CREATE TABLE `course_homework` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `course_outline_id` int(11) unsigned NOT NULL COMMENT '大纲ID（大纲章ID，不是节ID）',
  `grade_id` int(11) unsigned DEFAULT NULL COMMENT '学科ID',
  `title` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作业标题',
  `lecturer_id` int(11) unsigned DEFAULT NULL COMMENT '出题人,关联讲师ID',
  `content` blob COMMENT '作业内容',
  `version` int(11) unsigned DEFAULT '0' COMMENT '版本',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_outline_id` (`course_outline_id`),
  KEY `grade_id` (`grade_id`),
  KEY `lecturer_id` (`lecturer_id`),
  CONSTRAINT `course_homework_ibfk_1` FOREIGN KEY (`course_outline_id`) REFERENCES `course_outline` (`id`),
  CONSTRAINT `course_homework_ibfk_2` FOREIGN KEY (`grade_id`) REFERENCES `dict` (`id`),
  CONSTRAINT `course_homework_ibfk_3` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='作业问题表';

-- 25 学员答作业表
DROP TABLE IF EXISTS `course_homework_reply`;
CREATE TABLE `course_homework_reply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '作业回复主键 ID',
  `course_homework_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '提交作业ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `lecturer_id` int(11) unsigned DEFAULT NULL COMMENT '出题人,关联讲师ID',
  `homework_reply_content` blob COMMENT '作业内容',
  `score` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '作业得分',
  `is_good` tinyint(1) unsigned DEFAULT NULL COMMENT '是否优秀作业',
  `correct_status` tinyint(2) unsigned DEFAULT '0' COMMENT '作业状态。0：未批阅，1：已批阅',
  `correct` blob COMMENT '批阅内容',
  `correct_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '批阅时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_homework_id` (`course_homework_id`),
  KEY `user_unique_code` (`user_unique_code`),
  KEY `lecturer_id` (`lecturer_id`),
  CONSTRAINT `course_homework_reply_ibfk_1` FOREIGN KEY (`course_homework_id`) REFERENCES `course_homework` (`id`),
  CONSTRAINT `course_homework_reply_ibfk_2` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `course_homework_reply_ibfk_3` FOREIGN KEY (`lecturer_id`) REFERENCES `lecturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='学员答作业表';

-- 26 学员作业评论表
DROP TABLE IF EXISTS `course_homework_reply_comment`;
CREATE TABLE `course_homework_reply_comment` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '自关联ID',
  `course_homework_reply_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '学员作业id',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `praise_count` int(6) unsigned NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `content` blob COMMENT '评论内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`),
  KEY `course_homework_reply_id` (`course_homework_reply_id`),
  KEY `user_unique_code` (`user_unique_code`),
  CONSTRAINT `course_homework_reply_comment_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `course_homework_reply_comment` (`id`),
  CONSTRAINT `course_homework_reply_comment_ibfk_2` FOREIGN KEY (`course_homework_reply_id`) REFERENCES `course_homework_reply` (`id`),
  CONSTRAINT `course_homework_reply_comment_ibfk_3` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='学员作业评论表';

-- 27 学员作业评论点赞表
DROP TABLE IF EXISTS `course_homework_reply_praise`;
CREATE TABLE `course_homework_reply_praise` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `course_homework_reply_id` int(11) unsigned NOT NULL COMMENT '学员答作业表ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `course_homework_reply_id` (`course_homework_reply_id`),
  KEY `user_unique_code` (`user_unique_code`),
  CONSTRAINT `course_homework_reply_praise_ibfk_1` FOREIGN KEY (`course_homework_reply_id`) REFERENCES `course_homework_reply` (`id`),
  CONSTRAINT `course_homework_reply_praise_ibfk_2` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='学员作业评论点赞表';

-- 28 我购买的课程Table
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  -- 新增字段
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '课程id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `user_course_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_course_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='我购买的课程表';

-- 29 我的课程播放进度表
DROP TABLE IF EXISTS `user_course_progress`;
CREATE TABLE `user_course_progress` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `course_id` int(11) unsigned NOT NULL COMMENT '课程id',
  `video_id` int(11) unsigned NOT NULL COMMENT '视频ID',
  `course_outline_id` int(11) unsigned NOT NULL COMMENT '大纲ID(对应大纲小节)',
  `play_seconds` int(8) unsigned DEFAULT NULL COMMENT '播放时间，单位秒',
  `video_seconds` int(8) unsigned DEFAULT NULL COMMENT '视频总时长，单位秒',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `video_id` (`video_id`),
  KEY `course_id` (`course_id`),
  KEY `course_outline_id` (`course_outline_id`),
  CONSTRAINT `user_course_progress_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_course_progress_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `course_video` (`id`),
  CONSTRAINT `user_course_progress_ibfk_3` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `user_course_progress_ibfk_4` FOREIGN KEY (`course_outline_id`) REFERENCES `course_outline` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='我的课程播放进度表';

-- 30 我的课表
DROP TABLE IF EXISTS `user_course_notice`;
CREATE TABLE `user_course_notice` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '课程ID',
  `notice_week_day` tinyint(2) unsigned DEFAULT NULL COMMENT '1-7 代表第几周',
  `notice_time` time NOT NULL DEFAULT '20:01:01' COMMENT '提醒时间 例如 12:00',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `user_course_notice_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_course_notice_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='我的课表';

-- 31 课程收藏表
DROP TABLE IF EXISTS `user_course_favorite`;
CREATE TABLE `user_course_favorite` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '收藏id',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `course_id` int(11) unsigned NOT NULL COMMENT '课程id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `user_course_favorite_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_course_favorite_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='课程收藏表';

-- =============================================================================
-- 订单
-- =============================================================================
-- 32 订单表
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单主键 订单id',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `total_amount` int(11) unsigned NOT NULL COMMENT '订单总价格 订单总价格，单位:分',
  `real_pay_amount` int(11) unsigned NOT NULL COMMENT '实际支付总价格 实际支付总价格，单位:分',
--   `pay_method` tinyint(2) unsigned NOT NULL COMMENT '支付方式 1:微信 2:支付宝 3苹果支付',
  `left_msg` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '买家留言',
--   `extand` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '扩展字段',
  `order_state` tinyint(2) unsigned NOT NULL COMMENT '订单状态 1待付款 2已付款，待发货 3已发货，待收货 4交易成功 5交易关闭',
--   `payment_state` tinyint(2) unsigned NOT NULL COMMENT '支付状态 1未支付 2支付中 3已支付 4支付失败 5已退款',
  `pay_time` datetime DEFAULT NULL COMMENT '支付成功时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `success_time` datetime DEFAULT NULL COMMENT '交易成功时间',
--   `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
--   `need_delivery` tinyint(1) unsigned DEFAULT NULL COMMENT '是否需要发货 0不需要 1需要',
  `channel_source` tinyint(2) unsigned DEFAULT NULL COMMENT '渠道 区分三方渠道过来的订单 1:PC，2:APP，3：小程序 4腾讯课堂 5网易课堂',
  `third_order_no` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '第三方订单号',
--   `is_apple_pay` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否苹果支付',
  `user_coupon_ids` varchar(128) NOT NULL COMMENT '用户优惠券ID,使用json格式保存，兼容叠加使用',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除状态 1: 删除 0:未删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_ORDERS_CREATEDTIME` (`create_time`),
  KEY `user_unique_code` (`user_unique_code`),
  UNIQUE KEY `uk_order_no` (`order_no`) USING BTREE COMMENT '订单编号唯一索引',
--   KEY `user_coupon_id` (`user_coupon_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
--   ,
--   CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`user_coupon_id`) REFERENCES `user_coupon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='订单表';

-- 33 订单详情表
DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `id` int(11) unsigned NOT NULL COMMENT '主键id',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `item_id` int(11) unsigned NOT NULL COMMENT '商品id',
  `item_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `item_img` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品图片（缩略图）',
  `price` int(11) unsigned DEFAULT NULL COMMENT '原始价格，单位:分',
  `cost` int(11) unsigned NOT NULL COMMENT '成交价格，单位:分',
  `buy_counts` int(5) unsigned NOT NULL COMMENT '购买数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_no` (`order_no`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`),
  CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='订单详情表';

-- 34 支付表
DROP TABLE IF EXISTS `order_pay`;
CREATE TABLE `order_pay` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `merchant_order_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商户订单号',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `pay_method` int(11) unsigned NOT NULL COMMENT '支付方式 1:微信 2:支付宝 3苹果支付',
  `payment_state` tinyint(2) unsigned NOT NULL COMMENT '支付状态 1未支付 2支付中 3已支付 4支付失败 5已退款',
--   `channel_source` tinyint(2) unsigned DEFAULT NULL COMMENT '渠道 区分三方渠道过来的订单 1:PC，2:APP，3：小程序',
  `cost` int(11) unsigned NOT NULL COMMENT '实际支付金额 实际支付的金额，单位:分',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `IDX_ORDERS_CREATEDTIME` (`create_time`),
  KEY `order_no` (`order_no`),
  KEY `user_unique_code` (`user_unique_code`),
  CONSTRAINT `order_pay_ibfk_1` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`),
  CONSTRAINT `order_pay_ibfk_2` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='支付表';

-- 35 订单补充表
DROP TABLE IF EXISTS `order_supplement`;
CREATE TABLE `order_supplement` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
--   冗余更新，审核通过之后才会有
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
--   `third_order_no` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '第三方订单号',
  `category_id` int(11) unsigned DEFAULT '0' COMMENT '课程分类id（训练营、精品小课、vip课，对应字典表）',
  `course_id` int(11) unsigned NOT NULL COMMENT '课程id，关联course表',
  `recommend_code` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '推荐码',
  `recommend_teacher_id` int(11) DEFAULT NULL COMMENT '推荐老师id',
  `recommend_mobile` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐学员手机号',
  `recommend_user_id` int(11) DEFAULT NULL COMMENT '推荐学员id',
  `lecture_id` int(11) unsigned DEFAULT NULL COMMENT '听哪位老师课',
  `realize_channel` int(11) unsigned DEFAULT NULL COMMENT '了解渠道，关联字典表',
  `audition_counts` int(11) unsigned DEFAULT '0' COMMENT '试听次数,关联字典表',
  `solve_problem` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '需解决的问题',
--   新增字段
--   `is_audited` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已审核 0否 1是',
  `audit_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '第三方订单审核状态(-1:审核不通过，0:待审核，1:审核通过)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `order_no` (`order_no`),
  KEY `user_unique_code` (`user_unique_code`),
  KEY `category_id` (`category_id`),
  KEY `course_id` (`course_id`),
  KEY `lecture_id` (`lecture_id`),
  CONSTRAINT `order_supplement_ibfk_1` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`),
  CONSTRAINT `order_supplement_ibfk_2` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `order_supplement_ibfk_3` FOREIGN KEY (`category_id`) REFERENCES `dict` (`id`),
  CONSTRAINT `order_supplement_ibfk_4` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `order_supplement_ibfk_5` FOREIGN KEY (`lecture_id`) REFERENCES `lecturer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='订单补充表 用于存储第三方渠道的课程生成的订单信息';

-- 36 物流信息表
DROP TABLE IF EXISTS `order_logistics`;
CREATE TABLE `order_logistics` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单主键 同时也是订单编号',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `order_no` varchar(64) NOT NULL COMMENT '订单号',
  `logistics_no` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物流单号',
  `logistics_channel` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '物流渠道：比如顺丰、申通',
  `user_address_id` int(11) unsigned NOT NULL COMMENT '收货地址ID',
  `receiver_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人姓名',
  `receiver_mobile` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人手机号',
  `logistics_fees` int(11) unsigned DEFAULT '0' COMMENT '邮费 默认可以为零，代表包邮',
  `sender_id` int(11) unsigned DEFAULT NULL COMMENT '发件人ID,关联字典表（json）',
  `sender_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '寄件人姓名',
  `sender_address` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '寄件人地址',
  `sender_mobile` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '寄件人手机号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `IDX_UK_ORDERS_LOGISTICS_NO` (`logistics_no`),
  KEY `IDX_ORDERS_CREATETIME` (`create_time`),
  KEY `user_unique_code` (`user_unique_code`),
  KEY `order_no` (`order_no`),
  KEY `user_address_id` (`user_address_id`),
  CONSTRAINT `order_logistics_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `order_logistics_ibfk_2` FOREIGN KEY (`order_no`) REFERENCES `orders` (`order_no`),
  CONSTRAINT `order_logistics_ibfk_3` FOREIGN KEY (`user_address_id`) REFERENCES `user_address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='物流信息表';

-- 37 商品表
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id 唯一标识',
  `resource_id` int(11) unsigned COMMENT '关联资源ID,如果是课程,就存课程ID,如果是书籍就存书籍ID',
  `item_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `item_img` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品图片（缩略图）',
  `item_type` tinyint(2) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品类型:1课程 2学币 3会员',
  `summary` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
  `description` blob COMMENT '图文详情',
  `price_normal` int(11) DEFAULT NULL COMMENT '原始价格 单位:分',
  `price_vip` int(11) DEFAULT NULL COMMENT '会员价 单位分',
  `buy_counts` int(11) NOT NULL COMMENT '库存数量： -1表示无限',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='商品表';

-- 38 快递公司维护表
DROP TABLE IF EXISTS `express_basic_info`;
CREATE TABLE `express_basic_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 id',
  `company_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递公司编码',
  `company_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递公司名称',
  `company_type` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递公司类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='快递公司维护表 ';

-- =============================================================================
-- 营销
-- =============================================================================
-- 39 优惠券表
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '优惠券名称',
  `description` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '优惠券描述',
  `grade_id` int(11) unsigned DEFAULT NULL COMMENT '所属学科id:(JAVA、大数据,对应字典表)',
  `preferential_way` tinyint(2) unsigned DEFAULT NULL COMMENT '优惠方式 1.打折 2. 满减3:立减',
  `range` tinyint(2) unsigned DEFAULT NULL COMMENT '优惠范围 1：全场 2：指定商品  3：指定科目',
  `amount` int(11) unsigned DEFAULT NULL COMMENT '优惠券数量',
  `send_amount` int(11) unsigned DEFAULT NULL COMMENT '优惠券已发放数量',
  `valid_date` datetime DEFAULT NULL COMMENT '有效时间',
  `start_time` datetime DEFAULT NULL COMMENT '优惠券开始使用日期',
  `end_time` datetime DEFAULT NULL COMMENT '优惠券截止使用日期',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '优惠券状态1.启用 2.停用 3.失效',
  `issued_way` tinyint(2) unsigned DEFAULT NULL COMMENT '发行方式  1:私密发行 2：批量发行 3: 公开发行',
  `creator_id` int(11) unsigned DEFAULT NULL COMMENT '创建者',
  `updater_id` int(11) unsigned DEFAULT NULL COMMENT '更新者',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `grade_id` (`grade_id`),
  CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`grade_id`) REFERENCES `dict` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='优惠券表';

-- 40 用户优惠券表
DROP TABLE IF EXISTS `user_coupon`;
CREATE TABLE `user_coupon` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `coupon_id` int(11) unsigned NOT NULL COMMENT '优惠券ID',
  -- 同一张优惠券领取多张
--   `valid_date` datetime DEFAULT NULL COMMENT '时间',
  `gain_date` datetime DEFAULT NULL COMMENT '获取日期',
  `start_time` datetime DEFAULT NULL COMMENT '有效起始日期',
  `end_time` datetime DEFAULT NULL COMMENT '有效截止日期',
  `used_time` datetime DEFAULT NULL COMMENT '使用时间',
  `available_state` tinyint(2) unsigned  NOT NULL default '0' COMMENT '可用状态 0-已使用 1-未使用 3-已过期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `coupon_id` (`coupon_id`),
  CONSTRAINT `user_coupon_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_coupon_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='用户优惠券表';

-- 41 优惠券商品关系表
DROP TABLE IF EXISTS `coupon_goods_relation`;
CREATE TABLE `coupon_goods_relation` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠券ID',
  `goods_id` int(11) unsigned DEFAULT NULL COMMENT '商品ID',
  `goods_type` tinyint(2) unsigned DEFAULT '1' COMMENT '商品类型  1课程 （扩展字段）',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `coupon_id` (`coupon_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `coupon_goods_relation_ibfk_1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`),
  CONSTRAINT `coupon_goods_relation_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='优惠券商品关系表';

-- -- 42 优惠券科目关系表
-- DROP TABLE IF EXISTS `coupon_subject_relation`;
-- CREATE TABLE `coupon_subject_relation` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
--   `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠券ID',
--   `grade_id` int(11) unsigned DEFAULT NULL COMMENT '所属学科id:(JAVA、大数据,对应字典表)',
--   `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已删除 0否 1是',
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`) USING BTREE,
--   KEY `coupon_id` (`coupon_id`),
--   KEY `grade_id` (`grade_id`),
--   CONSTRAINT `coupon_subject_relation_ibfk_1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`),
--   CONSTRAINT `coupon_subject_relation_ibfk_2` FOREIGN KEY (`grade_id`) REFERENCES `dict` (`id`)
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='优惠券科目关系表';

-- 43 优惠策略表
DROP TABLE IF EXISTS `coupon_preferential_strategy`;
CREATE TABLE `coupon_preferential_strategy` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券ID',
    `full_price` decimal(10,2) DEFAULT NULL COMMENT '满金额',
    `reduce_price` decimal(10,2) DEFAULT NULL COMMENT '减金额',
    `discount` decimal(10,2) DEFAULT NULL COMMENT '打折',
    `status` tinyint(2) DEFAULT '1' COMMENT '状态 1生效  0失效',
    `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP    COMMENT '创建时间',
    `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC  COMMENT='优惠券-优惠策略表';

-- 44 优惠券领取限制表
DROP TABLE IF EXISTS `coupon_receive_limit`;
CREATE TABLE `coupon_receive_limit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠卷ID',
  `count` int(11) unsigned DEFAULT NULL COMMENT '每个用户领取张数',
  `user_limit` tinyint(2) unsigned DEFAULT NULL COMMENT '用户领取身份限制0:所有用户 1：会员  2：vip身份',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `coupon_id` (`coupon_id`),
  CONSTRAINT `coupon_receive_limit_ibfk_1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='优惠券领取限制表';

-- 45 优惠券发送记录
DROP TABLE IF EXISTS `coupon_send_log`;
CREATE TABLE `coupon_send_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠券ID',
  `send_count` int(11) unsigned DEFAULT NULL COMMENT '发放数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_unique_code` (`user_unique_code`),
  KEY `coupon_id` (`coupon_id`),
  CONSTRAINT `coupon_send_log_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `coupon_send_log_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='优惠券发送记录';

-- 46 优惠券使用限制表
DROP TABLE IF EXISTS `coupon_use_limit`;
CREATE TABLE `coupon_use_limit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coupon_id` int(11) unsigned DEFAULT NULL COMMENT '优惠卷ID',
  `count` int(11) unsigned DEFAULT '1' COMMENT '单次可使用张数',
  `is_overlay` tinyint(2) unsigned DEFAULT NULL COMMENT '是否可以叠加使用 1 叠加 0不叠加',
  `user_limit` tinyint(2) unsigned DEFAULT NULL COMMENT '用户使用身份限制0:所有用户 1：会员  2：vip身份',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `coupon_id` (`coupon_id`),
  CONSTRAINT `coupon_use_limit_ibfk_1` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='优惠券使用限制表';

-- 47 限时优惠活动表
DROP TABLE IF EXISTS `flash_promotion`;
CREATE TABLE `flash_promotion` (
  `id` int(11) unsigned NOT NULL COMMENT '限时优惠活动ID',
  `goods_id` int(11) unsigned DEFAULT NULL COMMENT '商品 ID',
  `course_price` decimal(10,2) DEFAULT NULL COMMENT '课程价格',
  `preferential_price` decimal(10,2) DEFAULT NULL COMMENT '优惠价格',
  `start_time` datetime DEFAULT NULL COMMENT '限时优惠开始使用日期',
  `end_time` datetime DEFAULT NULL COMMENT '限时优惠截止使用日期',
  `is_limit` tinyint(2) unsigned DEFAULT NULL COMMENT '是否限制 0 不限制 1 限制',
  `available_quantity` int(10) unsigned DEFAULT '0' COMMENT '可用数量 ',
  `used_quantity` int(10) unsigned DEFAULT '0' COMMENT '已用数量',
  `status` tinyint(2) unsigned DEFAULT NULL COMMENT '活动状态 0 未开始 1 进行中 2 已失效 ',
  `creator_id` int(11) unsigned DEFAULT NULL COMMENT '创建者',
  `updater_id` int(11) unsigned DEFAULT NULL COMMENT '更新着',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `flash_promotion_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='限时优惠活动表';

-- =============================================================================
-- 运营后台
-- =============================================================================
-- 48 常见问题
DROP TABLE IF EXISTS `common_question`;
CREATE TABLE `common_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `question` int(11) unsigned NOT NULL COMMENT '问题',
  `answer` int(11) unsigned NOT NULL COMMENT '答案',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `question` (`question`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='常见问题';

-- 49 平台通知表
DROP TABLE IF EXISTS `system_attention`;
CREATE TABLE `system_attention` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` tinyint(2) unsigned NOT NULL COMMENT '通知类型 1-后台录入通知 2-优惠卷过期提醒 3-收到优惠卷提醒 4-关注课程上架提醒 5-权益到期提醒 6-会员新季度书籍上架提醒',
  `source_id` int(11) unsigned DEFAULT NULL COMMENT '发送内容id(供链接跳转)',
  `subject` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `url` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '通知内容url',
  `content` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `is_send` tinyint(1) unsigned DEFAULT '1' COMMENT '1-送达 0-未送达',
  `is_read` tinyint(1) unsigned DEFAULT '0' COMMENT '是否已读 0-未读 1-已读',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '0-未删除 1-删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='平台通知表';

-- 50 课程咨询表
DROP TABLE IF EXISTS `course_feedback`;
CREATE TABLE `course_feedback` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int(11) unsigned DEFAULT NULL COMMENT '自关联ID',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '课程ID',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `seller_id` int(11) unsigned DEFAULT NULL COMMENT '销售ID',
  `content` blob COMMENT '图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `parent_id` (`parent_id`),
  KEY `course_id` (`course_id`),
  KEY `user_unique_code` (`user_unique_code`),
  CONSTRAINT `course_feedback_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `course_feedback` (`id`),
  CONSTRAINT `course_feedback_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `course_feedback_ibfk_3` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='课程咨询表';

-- 51 分享记录表
DROP TABLE IF EXISTS `user_course_share`;
CREATE TABLE `user_course_share` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_unique_code` varchar(64) NOT NULL COMMENT '用户唯一编码',
  `course_id` int(11) unsigned DEFAULT NULL COMMENT '课程id',
  `category_id` int(11) unsigned DEFAULT '0' COMMENT '课程分类id（训练营、精品小课、vip课，对应字典表）',
  `channel` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分享类型（qq、wechat）',
  `message` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分享感言',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_unique_code` (`user_unique_code`),
  KEY `course_id` (`course_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `user_course_share_ibfk_1` FOREIGN KEY (`user_unique_code`) REFERENCES `user` (`user_unique_code`),
  CONSTRAINT `user_course_share_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `user_course_share_ibfk_3` FOREIGN KEY (`category_id`) REFERENCES `dict` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='分享记录表';

-- 52 首页banner表
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sort` int(11) unsigned DEFAULT NULL COMMENT 'banner排序',
  `banner_image` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'banner图片',
  `banner_url` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'banner跳转对象地址',
  `is_deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '0-未删除 1-删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='首页banner表';

-- 53 数据字典表
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_code` varchar(32) NOT NULL COMMENT '分类编码',
  `category_id` tinyint(2) NOT NULL COMMENT '分类ID 1课程类型（训练营、小课） 2班级类型（Java、大数据） 3学历 4职位 5互动通知类型 6订单渠道 7获悉渠道 8权益',
  `available_state` tinyint(2) unsigned  NOT NULL default '0' COMMENT '可用状态 0-未启用 1-启用',
  `dict_name` varchar(64) NOT NULL COMMENT '字典数据名称',
  `dict_value` varchar(128) NOT NULL COMMENT '字典数据的值',
  `desc` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='数据字典表';
-- =============================================================================
-- 基础服务
-- =============================================================================
--  权限角色表
-- DROP TABLE IF EXISTS `auth_role`;
-- CREATE TABLE `auth_role` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `parent_id` int(11) NOT NULL COMMENT '父角色ID',
--   `role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
--   `name` varchar(64) DEFAULT NULL COMMENT '角色名称',
--   `desc` varchar(128) DEFAULT NULL COMMENT '角色描述',
--   `sort` tinyint(2) unsigned DEFAULT NULL COMMENT '排序',
--   `use_state` tinyint(2) unsigned DEFAULT NULL COMMENT '启用状态：0未启用 1启用',
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`) USING BTREE,
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限角色表';
--
-- --  用户角色关联表
-- DROP TABLE IF EXISTS `auth_category_resource_rel`;
-- CREATE TABLE `auth_resource_type_rel` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码',
--   `role_id` int(11) NOT NULL COMMENT '角色ID',
--   `role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
--   `valid_state` tinyint(2) unsigned DEFAULT NULL COMMENT '有效状态：0无效 1有效',
-- --   `start_valid_time` timestamp COMMENT '起始有效时间',
-- --   `end_valid_time` timestamp COMMENT '结束有效时间',
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`) USING BTREE,
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

--  权限资源分类表 例如：游客权限、普通用户权限（单独购买课程）、会员权限、vip学员权限
-- DROP TABLE IF EXISTS `auth_category`;
-- CREATE TABLE `auth_category` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `parent_id` int(11) NOT NULL COMMENT '父类型ID',
--   `category_code` varchar(64) DEFAULT NULL COMMENT '资源类型编码',
--   `name` varchar(64) DEFAULT NULL COMMENT '资源类型名称',
--   `desc` varchar(128) DEFAULT NULL COMMENT '资源类型描述',
--   `sort` tinyint(2) unsigned DEFAULT NULL COMMENT '排序',
--   `use_state` tinyint(2) unsigned DEFAULT NULL COMMENT '启用状态：0未启用 1启用',
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`) USING BTREE,
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限资源分类表';

--  用户关联表
-- DROP TABLE IF EXISTS `auth_category_resource_rel`;
-- CREATE TABLE `auth_resource_type_rel` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `user_unique_code` varchar(64) NOT NULL COMMENT '用户全局唯一编码',
--   `role_id` int(11) NOT NULL COMMENT '角色ID',
--   `role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
--   `valid_state` tinyint(2) unsigned DEFAULT NULL COMMENT '有效状态：0无效 1有效',
-- --   `start_valid_time` timestamp COMMENT '起始有效时间',
-- --   `end_valid_time` timestamp COMMENT '结束有效时间',
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`) USING BTREE,
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

--  权限角色与类型关联表
-- DROP TABLE IF EXISTS `auth_role_category_rel`;
-- CREATE TABLE `auth_role_category_rel` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `role_id` int(11) NOT NULL COMMENT '角色ID',
--   `role_code` varchar(64) DEFAULT NULL COMMENT '角色编码',
--   `category_id` int(11) NOT NULL COMMENT '资源类型ID',
--   `category_code` varchar(64) DEFAULT NULL COMMENT '资源类型编码',
--   `sort` tinyint(2) unsigned DEFAULT NULL COMMENT '排序',
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`) USING BTREE,
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限资源与类型关联表';

--  权限资源表 包括视频、优惠券、书籍 linux 抽象 file的权限控制   3个表 ：课程、章节、视频  关联查询订单表 select 关联具体表
-- DROP TABLE IF EXISTS `auth_resource`; -- 我购买的课程表、权益关联表、权益资源表
-- CREATE TABLE `auth_resource` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   -- ? 如果资源类型是课程，课程与视频关系保存到redis中
-- --   `parent_id` int(11) NOT NULL COMMENT '父资源ID，用于做资源包',
--   -- ? 使用数据字典
--   `resource_type` tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '资源类型：1书籍 2文化衫 11课程 12大纲 13视频 14优惠券 ',
--   `third_resource_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '关联其他资源ID,比如视频ID、优惠券ID',
--   `name` varchar(64) DEFAULT NULL COMMENT '资源名称',
--   `desc` varchar(128) DEFAULT NULL COMMENT '资源描述',
--   `summary` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源简介',
--   `cost` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '现价，有效价格',
--   `price` double(14,2) NOT NULL DEFAULT '0.00' COMMENT '原价，无效价格',
--   `sort` tinyint(2) unsigned DEFAULT NULL COMMENT '排序',
--   `use_state` tinyint(2) unsigned DEFAULT NULL COMMENT '启用状态：0未启用 1启用',
--   `description` blob NOT NULL COMMENT '资源图文描述',
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`) USING BTREE,
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限资源表';

-- --  权限资源与用户关联表
-- DROP TABLE IF EXISTS `auth_user_resource_rel`;
-- CREATE TABLE `auth_user_resource_rel` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
--   `resource_id` int(11) NOT NULL COMMENT '资源ID',
--   `category_id` int(11) NOT NULL COMMENT '资源类型ID',
--   `category_code` varchar(64) DEFAULT NULL COMMENT '资源类型编码',
--   `sort` tinyint(2) unsigned DEFAULT NULL COMMENT '排序',
--   `valid_state` tinyint(2) unsigned DEFAULT NULL COMMENT '有效状态：0无效 1有效',
--   `start_valid_time` timestamp COMMENT '起始有效时间',
--   `end_valid_time` timestamp COMMENT '结束有效时间',
-- --   `limit_time` int(11) COMMENT '试用时长，单位秒 -1表示永久',
--   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
--   PRIMARY KEY (`id`) USING BTREE,
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限资源与用户关联表';

-- ----------------------------
--  Table structure for `home_page_manage`
-- ----------------------------
DROP TABLE IF EXISTS `home_page_manage`;
CREATE TABLE `home_page_manage` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(2) unsigned NOT NULL COMMENT '首页栏位类型: 1:banner  2： 训练营 3： 课程预告 ',
  `sort` int(11) unsigned DEFAULT NULL COMMENT '分类  排序',
  `banner_id` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '首页bannerid，json格式按顺序存放',
  `train_id` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '训练营id，json格式按顺序存放',
  `forecast_id` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程预告id，json格式按顺序存放',
  `isdelete` tinyint(1) unsigned DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='首页管理表';

-- ----------------------------
--  Table structure for `member_right_resource`
-- ----------------------------
DROP TABLE IF EXISTS `member_right_resource`;
CREATE TABLE `member_right_resource` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `member_right_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '权益ID',
  `resource_type` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '资源分类：1书籍 2优惠券 3文化衫',
  -- ? 模板表
  -- | ID      规则      描述信息
  --  1000 优惠券规则 满1000-200

  -- 优惠券表
    -- | ID 规则      templateID
    -- | 1 1000-200  1000
    -- | 2 1000-200  1000
    -- | ……
    -- | 100000 1000-200 1000
  `resource_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '关联其他资源ID,比如优惠券模板ID',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源名称',
  `summary` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资源简介',
  `cost` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '现价，有效价格',
  `price` double(14,2) NOT NULL DEFAULT '0.00' COMMENT '原价，无效价格',
  `description` blob NOT NULL COMMENT '资源图文描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `member_right_id` (`member_right_id`),
  CONSTRAINT `member_right_resource_ibfk_1` FOREIGN KEY (`member_right_id`) REFERENCES `member_right` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权益明细表';


-- ----------------------------
--  Table structure for `search_history`
-- ----------------------------
DROP TABLE IF EXISTS `search_history`;
CREATE TABLE `search_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '搜索历史ID',
  `user_id` int(11) unsigned NOT NULL COMMENT '用户ID',
  `keyword` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '搜索关键词',
  `search_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '搜索时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `search_history_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='搜索历史表';

-- ----------------------------
--  Table structure for `search_hot_word`
-- ----------------------------
DROP TABLE IF EXISTS `search_hot_word`;
CREATE TABLE `search_hot_word` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '搜索热词ID',
  `hot_word` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '搜索热词',
  `sort_num` int(11) unsigned NOT NULL COMMENT '排序字段',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '可用状态 1可用 0禁用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci  COMMENT='搜索热词表';


SET FOREIGN_KEY_CHECKS = 1;


select count(*) tables ,table_schema from information_schema.tables
where table_schema='gp' group by table_schema;