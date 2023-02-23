/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost:3306
 Source Schema         : style

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 23/03/2020 10:56:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '优惠券名称',
  `description` varchar(64) DEFAULT NULL COMMENT '优惠券描述',
  `discipline_id` tinyint(4) DEFAULT NULL COMMENT '学科ID ',
  `preferential_way` tinyint(2) DEFAULT NULL COMMENT '优惠方式 1.打折 2. 满减3:立减',
  `range` tinyint(2) DEFAULT NULL COMMENT '优惠范围 1：全场 2：指定商品  3：指定科目',
  `amount` int(10) DEFAULT NULL COMMENT '优惠券数量',
  `send_amount` int(10) DEFAULT NULL COMMENT '优惠券发放数量',
  `valid_date` datetime DEFAULT NULL COMMENT '有效时间',
  `start_time` datetime DEFAULT NULL COMMENT '优惠券开始使用日期',
  `end_time` datetime DEFAULT NULL COMMENT '优惠券截止使用日期',
  `status` tinyint(2) DEFAULT NULL COMMENT '优惠券状态1.启用 2.停用 3.失效',
  `issued_way` tinyint(2) DEFAULT NULL COMMENT '发行方式  1:私密发行 2：批量发行 3: 公开发行',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建者',
  `updater` varchar(64) DEFAULT NULL COMMENT '更新着',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='优惠券';

-- ----------------------------
-- Table structure for coupon_goods_relation
-- ----------------------------
DROP TABLE IF EXISTS `coupon_goods_relation`;
CREATE TABLE `coupon_goods_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券ID',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `goods_type` tinyint(2) DEFAULT '1' COMMENT '商品类型  1课程 （扩展字段）',
  `delete_flag` tinyint(2) DEFAULT '0' COMMENT '删除标记 0未删除 1 删除',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '优惠券商品关系表';

-- ----------------------------
-- Table structure for coupon_subject_relation
-- ----------------------------
DROP TABLE IF EXISTS `coupon_subject_relation`;
CREATE TABLE `coupon_subject_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券ID',
  `subject_id` int(11) DEFAULT NULL COMMENT '科目ID',
  `delete_flag` tinyint(2) DEFAULT '0' COMMENT '删除标记 0未删除 1 删除',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '优惠券科目关系表';

-- ----------------------------
-- Table structure for coupon_preferential_strategy
-- ----------------------------
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券-优惠策略表';

-- ----------------------------
-- Table structure for coupon_receive_limit
-- ----------------------------
DROP TABLE IF EXISTS `coupon_receive_limit`;
CREATE TABLE `coupon_receive_limit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠卷ID',
  `count` int(10) DEFAULT NULL COMMENT '每个用户领取张数',
  `user_limit` tinyint(2) DEFAULT NULL COMMENT '用户领取身份限制0:所有用户 1：会员  2：vip身份',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- ----------------------------
-- Table structure for coupon_send_log
-- ----------------------------
DROP TABLE IF EXISTS `coupon_send_log`;
CREATE TABLE `coupon_send_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券ID',
  `send_count` int(10) DEFAULT NULL COMMENT '发放数量',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券发送记录';

-- ----------------------------
-- Table structure for coupon_use_limit
-- ----------------------------
DROP TABLE IF EXISTS `coupon_use_limit`;
CREATE TABLE `coupon_use_limit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠卷ID',
  `count` int(10) DEFAULT '1' COMMENT '单次可使用张数',
  `is_overlay` tinyint(2) DEFAULT NULL COMMENT '是否可以叠加使用 1 叠加 0不叠加',
  `user_limit` tinyint(2) DEFAULT NULL COMMENT '用户使用身份限制0:所有用户 1：会员  2：vip身份',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for coupon_use_log (删除)
-- ----------------------------
DROP TABLE IF EXISTS `coupon_use_log`;
CREATE TABLE `coupon_use_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `use_description` varchar(64) DEFAULT NULL COMMENT '使用说明',
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更细时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券用户使用记录表';

-- ----------------------------
-- Table structure for coupon_user_relation (删除)
-- ----------------------------
DROP TABLE IF EXISTS `coupon_user_relation`;
CREATE TABLE `coupon_user_relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券ID',
  `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
  `valid_time` datetime DEFAULT NULL COMMENT '有效时间',
  `coupon_status` tinyint(2) DEFAULT '0' COMMENT '优惠卷状态 0：未领取 1:正常 2:失效 3：已经使用',
  `gmt_created` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户-优惠卷关系表';

-- ----------------------------
-- Table structure for flash_promotion
-- ----------------------------
DROP TABLE IF EXISTS `flash_promotion`;
CREATE TABLE `flash_promotion` (
  `id` int(11) NOT NULL COMMENT '限时优惠活动ID',
  `goods_id` int(11) DEFAULT NULL COMMENT '商品 ID',
  `course_price` decimal(10,2) DEFAULT NULL COMMENT '课程价格',
  `preferential_price` decimal(10,2) DEFAULT NULL COMMENT '优惠价格',
  `start_time` datetime DEFAULT NULL COMMENT '限时优惠开始使用日期',
  `end_time` datetime DEFAULT NULL COMMENT '限时优惠截止使用日期',
  `is_limit` tinyint(2) DEFAULT NULL COMMENT '是否限制 0 不限制 1 限制',
  `available_quantity` int(10) DEFAULT '0' COMMENT '可用数量 ',
  `used_quantity` int(10) DEFAULT '0' COMMENT '已用数量',
  `status` tinyint(2) DEFAULT NULL COMMENT '活动状态 0 未开始 1 进行中 2 已失效 ',
  `creator` varchar(32) DEFAULT NULL COMMENT '操作人',
  `updater` varchar(32) DEFAULT NULL COMMENT '操作人',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_updated` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
