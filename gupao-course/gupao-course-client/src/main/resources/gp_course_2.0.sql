-- 视频资源表
CREATE TABLE `course_video` (
  `video_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `course_id` int(11)  DEFAULT NULL COMMENT '所属课程ID',
  `subject_id` int(11) DEFAULT NULL COMMENT '所属学科id',
  `course_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '课程编码CODE',
  `title` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '视频标题',
  `duration` int(11) NOT NULL DEFAULT '0' COMMENT '视频时长单位分',
  `view_num` int(11) NOT NULL DEFAULT '0' COMMENT '播放次数',
  `file_size` int(11) NOT NULL DEFAULT '0' COMMENT '文件大小单位M',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0待审核，1已审核',
  `video_status` varchar(36) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'unknown' COMMENT '保利威云视频状态',
  `image` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '本视频的图片展示地址',
  `createUserId` int(10) DEFAULT NULL COMMENT '创建者的userId',
  `updateUserId` int(10) DEFAULT NULL COMMENT '修改者的userId',
  `ctime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '播放地址',
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='视频资源表';

-- 课程类型表(课程类型表训练营、精品小课、vip课)
DROP TABLE IF EXISTS `course_type`;
CREATE TABLE `course_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID-type类型',
  `name` varchar(255) NOT NULL COMMENT '课程类型名字（训练营、精品小课、vip课） ',
  `createUserId` INT(10) NOT NULL DEFAULT '0' COMMENT '添加人',
  `updateUserId` INT(10) NOT NULL DEFAULT '0' COMMENT '更新人',
  `ctime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `remark` VARCHAR(100) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='课程类型表训练营、精品小课、vip课';

-- 科目表
DROP TABLE IF EXISTS `course_subject`;
CREATE TABLE `course_subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '科目名称 ： ',
  `del_flag` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `version` varchar(60) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '版本',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='课程科目表： Java科目 大数据科目 人工智能';

-- 课程表
DROP TABLE IF EXISTS `gp_course`;
CREATE TABLE `gp_course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type_id` int(11)  COLLATE utf8_bin DEFAULT 0 COMMENT '课程类型id（训练营、精品小课、vip课）',
  `subject_id` int(11) DEFAULT NULL COMMENT '所属科目： JAVA  大数据',
  `course_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '课程编码CODE',
  `course_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '课程名',
  `course_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程主题名称',
  `image` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '课程封面图片',
  `view_num` int(11) NOT NULL DEFAULT '0' COMMENT '观看人数',
  `study_num` int(11) NOT NULL DEFAULT '0' COMMENT '已学人数',
  `comment_num` int(11) NOT NULL DEFAULT '0' COMMENT '评论人数',
  `attention_num` int(11) NOT NULL DEFAULT '0' COMMENT '关注人数',
  `good_num` int(11) NOT NULL DEFAULT '0' COMMENT '好评数',
  `good_praise` double(14,2) NOT NULL DEFAULT '0' COMMENT '好评度',
  `now_price` double(14,2) NOT NULL DEFAULT '0.00' COMMENT '现价，有效价格',
  `old_price` double(14,2) NOT NULL DEFAULT '0.00' COMMENT '原价，无效价格',
  `teacherUserIdList` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '老师的userId，多个老师用-隔开',
  `image_text` varchar(4096) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '图文详情，富文本应编码存储',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1已删除',
  `startTime` datetime DEFAULT NULL COMMENT '课程的开播时间',
  `endTime` datetime DEFAULT NULL COMMENT '课程的结束时间',
  `createUserId` int(10) DEFAULT NULL COMMENT '创建者的userId',
  `updateUserId` int(10) DEFAULT NULL COMMENT '修改者的userId',
   `url` varchar(256) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '播放地址',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `puton_time` timestamp NULL  COMMENT '上架时间',
  `member_can_see` tinyint(1) DEFAULT 0 comment '0: 免费  1： 会员可看',
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2346 DEFAULT CHARSET=utf8  COMMENT='课程表';

-- 课程大纲表
DROP TABLE IF EXISTS `course_outline`;
CREATE TABLE `course_outline` (
  `outline_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '大纲ID，主键',
  `name` varchar(128) NOT NULL COMMENT '目录名称',
  `course_id` int(11) NOT NULL COMMENT '所属课程ID',
  `grade_id` int(11)  DEFAULT NUll COMMENT '目录级别ID',
  `piarent_id` int(11)  DEFAULT null COMMENT '父级目录ID',
   `outline_sort` int(1) DEFAULT NULL COMMENT '课程大纲排序',
  `address` varchar(512) CHARACTER SET utf8 DEFAULT NULL COMMENT '记录视频地址： 手工维护',
  `video_id` int(11)  COMMENT '关联视频ID',
  `createUserId` int(10) DEFAULT NULL COMMENT '创建者的userId',
  `updateUserId` int(10) DEFAULT NULL COMMENT '修改者的userId',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否已删除',
  `is_outline` tinyint(1) DEFAULT '0' COMMENT '是否大纲： 默认0 大纲（章） ； 1 是视频（节）',
  PRIMARY KEY (`outline_id`) USING BTREE
) ENGINE=InnoDB  AUTO_INCREMENT=6555 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='课程大纲表（关联视频资源）';

-- 图文详情表。
DROP TABLE IF EXISTS `course_image`;
CREATE TABLE `course_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `course_id` int(10) NOT NULL COMMENT '关联课程id',
  `image_url` varchar(100) DEFAULT '' COMMENT '图片路径',
  `image_url_second` varchar(100) DEFAULT '' COMMENT '图片路径',
  `image_url_third` varchar(100) DEFAULT '' COMMENT '图片路径',
  `image_url_fourth` varchar(100) DEFAULT '' COMMENT '图片路径',
  `image_url_fifth` varchar(100) DEFAULT '' COMMENT '图片路径',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `isdelete` tinyint(1) DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  `version` int(11) DEFAULT '0' COMMENT '版本',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `utime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 讲师详情表
DROP TABLE IF EXISTS `teacher_team`;
CREATE TABLE `teacher_team` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `teacher_name` varchar(64) DEFAULT NULL COMMENT '教师名字',
  `teacher_star` int(1) DEFAULT NULL COMMENT '是否明星讲师 1是0否',
  `teacher_resume` varchar(500) DEFAULT NULL COMMENT '教师简介',
  `teacher_position` varchar(32) DEFAULT NULL COMMENT '教师职位',
  `teacher_tag` varchar(32) DEFAULT NULL COMMENT '教师标签',
  `teacher_contact` varchar(64) DEFAULT NULL COMMENT '联系方式 ',
  `teacher_image` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `teacher_sort` int(1) DEFAULT NULL COMMENT '教师排序',
  `teacher_status` int(1) DEFAULT NULL COMMENT '讲师状态',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除 默认0 否',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `utime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `creator` varchar(255) DEFAULT NULL COMMENT '创建人名称',
  PRIMARY KEY (`teacher_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- 读前
-- 这个是对点赞 评论 回复的疑问
-- 课程评论表 : 对于课程的评论只能进行官方回复， 这个时候可以参考京东的商品评论
DROP TABLE IF EXISTS `course_comment`;
CREATE TABLE `course_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int(10) DEFAULT NULL COMMENT '发表评论的用户的userId',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发表评论的用户的呢称',
  `star` int(11) DEFAULT '0' COMMENT '星级，一般最高为五星好评',
  `content` varchar(4096) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '评论的内容',
  `type` varchar(100) CHARACTER SET utf8  DEFAULT NULL COMMENT '被评标的所属的课程类型（训练营、精品小课、往期录播）',
  `course_id` int(11) DEFAULT NULL COMMENT '被评标的所属的课程ID',
  `course_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '课程名称',
  `course_title` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '课程标题',
  `address` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'http地址',
  `is_office_answer` int(11) DEFAULT 0 COMMENT '官方是否回复--回复类容存在答案表中',
  `course_time` int(11) NOT NULL DEFAULT '0' COMMENT '已上课时间（分）',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间，即评论的时间',
  `utime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0未删除，1已删除',
  `isGood` tinyint(4) DEFAULT NULL COMMENT '是否好评',
  `isBad` tinyint(4) DEFAULT NULL COMMENT '是否差评',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='课程评论表';


-- 课程提问表 ： 分割出： 回答表 ： 针对回答继续进行评论的表
-- 									点赞表 （针对每个回答进行点赞）
-- 视频提问问题记录表
-- 在视频某个时间点提问,（在视频上有提问按钮）
-- 这个提问本身又是一个答疑表 ： 按照理论来说， 要在后面的回复中存在互动 ： 因此就存在了针对问题的回答表
DROP TABLE IF EXISTS `course_ask_question`;
CREATE TABLE `course_ask_question` (
  `question_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '问题ID',
  `question_title` varchar(255) NOT NULL COMMENT '问题标题',
  `question_context` varchar(255) NOT NULL COMMENT '问题内容',
  `course_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '课程id',
  `video_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '视频id', -- 这个视频id ？？？ 现在不是每一个大纲包含了视频吗？
  `_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '视频id', -- 这个 _id 代表是什么?
  `answer_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '回答数',
  `view_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览人数 ',
  `user_id` int(10) unsigned NOT NULL COMMENT '提问人', -- 这个由问题人改成了提问人
  `status` tinyint(4) NOT NULL DEFAULT '0'COMMENT '状态(-1:审核不通过，0:审核中，1:审核通过)',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `play_at`  time DEFAULT null COMMENT '视频播放时间点(秒)',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24467 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='问题记录表';

-- 针对上面的问题进行的回答
-- 针对提问 回答问题
-- 针对回答问题继续解答
-- 针对评价
DROP TABLE IF EXISTS `course_ask_answers`;
CREATE TABLE `course_reply_answers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `question_title` varchar(255) NOT NULL COMMENT '问题标题',
  `question_id` int(10) unsigned NOT NULL DEFAULT '0'COMMENT '问题id',
  `reply_id` int(10) unsigned NOT NULL DEFAULT '0'COMMENT '回复ID:针对评论的回复: ', --
  `user_id` int(10) unsigned NOT NULL DEFAULT '0'COMMENT '用户id', -- 这个评论ID 和这个用户ID 应该是有上下级关系的???意思就是树级关系??然后看是否要加一个 回复的父级ID???
  `supports` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '点赞',
  `oppositions` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '反对数量',
  `is_office_answer` int(11) DEFAULT null COMMENT '是否官方回复', -- 这个字段应该可以不用??? 也可以用
  `answer_content` varchar(200) DEFAULT NULL COMMENT '回复内容',
  `device` tinyint(4) NOT NULL DEFAULT '1' COMMENT '设备',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '回复状态',
  `adopted_at` timestamp NULL DEFAULT NULL COMMENT '采纳时间',
  `ctime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `utime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `opt_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21235 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='答疑表(有答疑互动)';



-- 学员作业定义表： 记录每节录播课的作业 ： 需要记录每个学生的回答 ： 一个作业题目 对应多个学生的完成情况（提交的回答， 上传的图等信息）
-- 给每一节课发布一个作业 ： 一条记录表
-- 一个大纲发布了一个作业 ：
DROP TABLE IF EXISTS `course_homework_manager`;
CREATE TABLE `course_homework_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '作业标题id',
  `course_id` int(11) NOT NULL COMMENT '课程ID',
  `course_title` varchar(512) CHARACTER SET utf8  DEFAULT NULL COMMENT '科目主题，例如"并发编程从入门到入魔"下的子视频或者子大纲',
  `subject_id` int(11) DEFAULT NULL COMMENT '学科ID',
  `outline_id` int(11) NOT NULL  COMMENT '大纲ID',
  `homework_title` varchar(100) DEFAULT NULL COMMENT '作业标题',
  `topicMake` varchar(64) DEFAULT NULL COMMENT '出题人',
  `homework_context` varchar(128) DEFAULT NULL COMMENT '作业内容',
  `homework_num` int(4) NOT NULL DEFAULT '0' COMMENT '布置作业人数 : ',
  `commit_num` int(4) DEFAULT NULL COMMENT '提交作业人数',
  `commit_rate` varchar(500) DEFAULT NULL COMMENT '作业提交率',
  `reply_num` int(11) DEFAULT NULL COMMENT '已批改作业人数',
  `unreply_num` int(11) DEFAULT NULL COMMENT '未批改作业人数',
  `brower_num` int(11) DEFAULT NULL COMMENT '浏览人数 : 应该是让别人多看到一点问题 增加参与感',
  `reply_rate` int(11) DEFAULT NULL COMMENT '作业批改率',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='作业发布表';
-- 问题是： 一个作业怎么布置到一个学员身上去的？怎么的关联？
-- 不用先在表里面有记录 如果没有记录 默认就没有作业
-- 如果要把作业和学员进行权限控制的话？ 是不是vip能看到所有的？
-- 购买的只能够看到购买的作业？ 按理说应该是这样
-- 普通用户呢？ 权限控制 作业


-- 作业回复表 :针对作业的 评论 回复 同上面的 答疑评论
-- 根据作业ID : 提交作业的才进行回复 :
-- 查询所有的作业评论 点赞等
DROP TABLE IF EXISTS `course_reply_homework`;
CREATE TABLE `course_reply_homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '作业回复主键 ID',
  `question_title` varchar(255) NOT NULL COMMENT '作业标题',
  `homework_id` int(10) unsigned NOT NULL DEFAULT '0'COMMENT '提交作业ID',
  `reply_id` int(10) unsigned NOT NULL DEFAULT '0'COMMENT '回复ID:针对作业的回复: ',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0'COMMENT '用户id',
  `supports` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '点赞',
  `oppositions` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '反对数量',
  `is_office_answer` int(11) DEFAULT null COMMENT '是否官方回复', -- 这个字段应该可以不用??? 也可以用
  `answer_content` varchar(200) DEFAULT NULL COMMENT '回复内容',
  `device` tinyint(4) NOT NULL DEFAULT '1' COMMENT '设备',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '回复状态',
  `adopted_at` timestamp NULL DEFAULT NULL COMMENT '采纳时间',
  `ctime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `utime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `opt_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='作业回复表(可以互动讨论)';

-- 提交作业表 ： 学员对老师提的作业进行的回答
-- 通过大纲id 关联每一节课作业 同时可以关联 学员的回答
-- 是不是很多 冗余字段? 有点不太好看懂
DROP TABLE IF EXISTS `course_homework`;
CREATE TABLE `course_homework` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL COMMENT '课程ID',-- 应该关联一个作业ID就行了, 后面如果统计的话?统计一个课程完成多少作业?那可以
  `course_title` varchar(512) CHARACTER SET utf8  DEFAULT NULL COMMENT '科目主题，例如"并发编程从入门到入魔"下的子视频或者子大纲',
  `subject_id` int(11) DEFAULT NULL COMMENT '学科ID',-- 同上
  `outline_id` int(11) NOT NULL  COMMENT '大纲ID', -- 关联视频大纲 然后就知道了这个大纲下有一个作业
  `user_id` int(11) DEFAULT NULL COMMENT '学员用户ID',-- 完成作业用户ID
  `vip_openid` varchar(64) DEFAULT NULL COMMENT '微信ID',
  `homework_title` varchar(100) DEFAULT NULL COMMENT '作业标题',
  `topicMake` varchar(64) DEFAULT NULL COMMENT '出题人',
  `homework_url` varchar(100) DEFAULT NULL COMMENT '作业地址。',
  `homework_context` varchar(128) DEFAULT NULL COMMENT '作业内容',
  `grade` int(4) NOT NULL DEFAULT '0' COMMENT '作业得分',
  `grade_type` tinyint(1) DEFAULT NULL COMMENT '得分等级',
  `is_good` tinyint(1) DEFAULT NULL COMMENT '是否优秀作业',
  `reply_status` tinyint(1) DEFAULT '0' COMMENT '作业状态。0：未批阅，1：已批阅',
  `commit_status` tinyint(1) DEFAULT '0' COMMENT '当前学员作业是否已提交。0：未提交，1：已提交',
  `reply_context` varchar(500) DEFAULT NULL COMMENT '批阅内容。',
  `reply_teacherid` int(11) DEFAULT NULL COMMENT '批阅老师ID',
  `reply_teachername` varchar(64) DEFAULT NULL COMMENT '批阅老师名',
  `reply_time` timestamp NULL DEFAULT NULL COMMENT '批阅时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `version` int(11) DEFAULT '0' COMMENT '版本',
  `isdelete` tinyint(4) DEFAULT '0' COMMENT '是否已删除',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学员作业表';


-- 	讲师点评表 针对每一个学员的提问进行点评
create table teacher_comment(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_homework_id` int(11) NOT NULL COMMENT '学员作业ID',
  `subject_id` int(11) DEFAULT NULL COMMENT '学科ID', -- 都留着冗余字段
  `outline_id` int(11) NOT NULL  COMMENT '大纲ID', -- 都留着冗余字段
  `homework_title` varchar(100) DEFAULT NULL COMMENT '作业标题', -- 都留着冗余字段
  `topicMake` varchar(64) DEFAULT NULL COMMENT '出题人', -- 都留着冗余字段
  `homework_url` varchar(100) DEFAULT NULL COMMENT '作业地址',-- 都留着冗余字段
  `homework_context` varchar(128) DEFAULT NULL COMMENT '作业内容',
  `grade` int(4) NOT NULL DEFAULT '0' COMMENT '作业得分', -- 点评内容
  `grade_type` tinyint(1) DEFAULT NULL COMMENT '得分等级', -- 点评内容
  `is_good` tinyint(1) DEFAULT NULL COMMENT '是否优秀作业', -- 点评内容
  `reply_context` varchar(500) DEFAULT NULL COMMENT '批阅内容。', -- 点评内容
  `reply_teacherid` int(11) DEFAULT NULL COMMENT '批阅老师ID', -- 点评
  `reply_teachername` varchar(64) DEFAULT NULL COMMENT '批阅老师名',-- 点评
  `reply_time` timestamp NULL DEFAULT NULL COMMENT '批阅时间',-- 点评
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',-- 点评
  `version` int(11) DEFAULT '0' COMMENT '版本',
  `isdelete` tinyint(4) DEFAULT '0' COMMENT '是否已删除',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='讲师点评表';



-- 老师点评表
-- 	作业点赞表 ： 记录用户 对 某一个作业的回答的点赞的记录： id  用户id 作业回答id  ---》 记录点赞 --》 应该放到回答表中
create table homework_give_like(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`course_homework_id` int(11) NOT NULL COMMENT '学员作业ID',
	`user_id` int(11) comment '点赞用户ID: 自己可以对字节点赞 ： 如果控制不重复点赞 ： 建一个唯一索引',
	`status` tinyint(1) comment '点赞状态: 0: 未点赞 1： 点赞',
	`ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
	`utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'

) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='作业点赞表';

-- 	答疑点赞表
create table question_give_like(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`course_homework_id` int(11) NOT NULL COMMENT '学员作业ID',
	`user_id` int(11) comment '点赞用户ID: 自己可以对字节点赞 ： 如果控制不重复点赞 ： 建一个唯一索引',
	`status` tinyint(1) comment '点赞状态: 0: 未点赞 1： 点赞',
	`ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
	`utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='答疑点赞表';















