
DROP TABLE IF EXISTS `ask_questions`;
CREATE TABLE `ask_questions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `category_id` int(11) NOT NULL DEFAULT '0' COMMENT '类别ID',
  `title` varchar(255) NOT NULL COMMENT '问题标题',
  `description` text COMMENT '问题描述',
  `price` smallint(6) DEFAULT '0' COMMENT '价格',
  `hide` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否隐藏？',
  `answers` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '采纳回答ID',
  `views` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '查看次数',
  `followers` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '关注次数（？？？）',
  `collections` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '收藏次数',
  `comments` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '评论次数',
  `device` tinyint(4) NOT NULL DEFAULT '1' COMMENT '设备',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态 0   1   2',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `opt_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作类型',
  `istop` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否置顶',
  `top_at` timestamp NULL DEFAULT NULL COMMENT '置顶时间',
  `editorType` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'ueditor' COMMENT '内容类型（ueditor  markdown）',
  `mddescription` text COMMENT 'markdown内容',
  `supports` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '支持数量',
  `oppositions` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '反对数量',
  `open_area` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'all' COMMENT '公开区域(all：所有，vip：对vip)',
  `draft` tinyint(4) DEFAULT '0' COMMENT '是否保存为草稿（0：否，1：是）',
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
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `total_day` int(11) DEFAULT '0' COMMENT '累计提问天数',
  `total_questions` int(11) DEFAULT '0' COMMENT '累计提问次数',
  `total_answers` int(11) DEFAULT '0' COMMENT '累计回答次数',
  `total_shares` int(11) DEFAULT '0' COMMENT '累计分享次数',
  `total_number` int(11) DEFAULT '0' COMMENT '累计奖励次数',
  `opt_type` tinyint(4) DEFAULT '1' COMMENT '是否删除(0：已删除，1：未删除)',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `level` tinyint(4) DEFAULT '0' COMMENT '记录用户完成等级',
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
  `status` tinyint(4) NOT NULL DEFAULT '0' ,
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
  `device` tinyint(4) NOT NULL DEFAULT '1' COMMENT '设备',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `adopted_at` timestamp NULL DEFAULT NULL COMMENT '采用时间',
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `opt_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作类型',
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
  `category_id` int(11) DEFAULT '0',
  `title` varchar(255) DEFAULT NULL  COMMENT '文章标题',
  `summary` varchar(255) DEFAULT NULL  COMMENT '文章简介',
  `content` mediumtext COMMENT '文章内容',
  `views` int(10) unsigned DEFAULT '0' COMMENT '查看数量',
  `collections` int(10) unsigned DEFAULT '0' COMMENT '收藏数量',
  `comments` int(10) unsigned DEFAULT '0' COMMENT '评论数量',
  `supports` int(10) unsigned DEFAULT '0' COMMENT '支持数量',
  `status` tinyint(4) DEFAULT '0',
  `device` tinyint(4) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `opt_type` tinyint(4) DEFAULT '1' COMMENT '操作类型',
  `istop` tinyint(3) unsigned DEFAULT '0' COMMENT '是否置顶(1:置顶，0:否)',
  `top_at` timestamp NULL DEFAULT NULL COMMENT '置顶时间',
  `editorType` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'ueditor' COMMENT '编辑器类型  ueditor  markdown',
  `mdcontent` mediumtext  COMMENT '文章内容 markdown',
  `open_area` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT 'all' COMMENT '公开区域(all：所有，vip：对vip)',
  `oppositions` int(10) unsigned DEFAULT '0' COMMENT '不推荐数量',
  `article_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '文章类型（ origina：原创，repost：转载，translated：翻译）',
  `confidential` tinyint(4) DEFAULT '0' COMMENT '是否私密文章（0：否，1：是）',
  `draft` tinyint(4) DEFAULT '0' COMMENT '是否保存为草稿（0：否，1：是）',
  `source_id` int(11) DEFAULT NULL COMMENT '后台爬取来源文章id',
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
  `status` tinyint(4) NOT NULL DEFAULT '1'  COMMENT '评论状态',
  `supports` int(11) NOT NULL DEFAULT '0'  COMMENT '点赞数量（应该没用）',
  `device` tinyint(4) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `opt_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作类型',
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
  `opt_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `taggables_taggable_id_taggable_type_index` (`taggable_id`,`taggable_type`) USING BTREE,
  KEY `taggables_tag_id_index` (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23466 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='提问文章标签关联表';

DROP TABLE IF EXISTS `ask_tags`;
CREATE TABLE `ask_tags` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `category_id` int(11) NOT NULL DEFAULT '0' COMMENT '分类ID？？',
  `logo` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `description` text COMMENT '描述',
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '上级标签ID （没用）',
  `followers` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '支持数量',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `opt_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tags_name_unique` (`name`) USING BTREE,
  KEY `tags_parent_id_index` (`parent_id`) USING BTREE,
  KEY `tags_followers_index` (`followers`) USING BTREE,
  KEY `tags_category_id_index` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1426 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='标签表';

DROP TABLE IF EXISTS `ask_categories`;
CREATE TABLE `ask_categories` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `grade` int(11) NOT NULL DEFAULT '1' COMMENT '等级',
  `name` varchar(255) NOT NULL COMMENT '分类名称',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `slug` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sort` int(11) NOT NULL DEFAULT '0',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` smallint(6) NOT NULL DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `opt_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作类型',
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
  `opt_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `supports_supportable_id_supportable_type_index` (`supportable_id`,`supportable_type`) USING BTREE,
  KEY `supports_session_id_index` (`session_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11139 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='点赞表';


DROP TABLE IF EXISTS `search_hot_word`;
CREATE TABLE `search_hot_word` (
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '搜索热词ID',
   `hot_word` varchar(200) NOT NULL COMMENT '搜索热词',
   `sort_num` int(11) NOT NULL COMMENT '排序字段',
   `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '可用状态 1可用 0禁用',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT =DYNAMIC COMMENT='搜索热词表';

DROP TABLE IF EXISTS `search_history`;
CREATE TABLE `search_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '搜索历史ID',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `keyword` varchar(200) NOT NULL COMMENT '搜索关键词',
  `search_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '搜索时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='搜索历史表';
