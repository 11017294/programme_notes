SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_note
-- ----------------------------
DROP TABLE IF EXISTS `t_note`;
CREATE TABLE `t_note`  (
                            `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一uid',
                            `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记标题',
                            `summary` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记简介',
                            `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '笔记内容',
                            `tag_uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签uid',
                            `click_count` int(11) NULL DEFAULT 0 COMMENT '笔记点击数',
                            `collect_count` int(11) NULL DEFAULT 0 COMMENT '笔记收藏数',
                            `file_uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题图片uid',
                            `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                            `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                            `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                            `admin_uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员uid',
                            `is_original` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否原创（0:不是 1：是）',
                            `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
                            `articles_part` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章出处',
                            `note_sort_uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '笔记分类UID',
                            `is_publish` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '是否发布：0：否，1：是',
                            `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序字段',
                            `open_comment` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否开启评论(0:否 1:是)',
                            `user_uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投稿用户UID',
                            `article_source` tinyint(1) NOT NULL DEFAULT 0 COMMENT '文章来源【0 后台添加，1 用户投稿】',
                            PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '笔记表' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `t_collect`;

CREATE TABLE `t_collect` (
                             `uid` varchar(32) NOT NULL COMMENT '唯一uid',
                             `user_uid` varchar(32) NOT NULL COMMENT '用户的uid',
                             `note_uid` varchar(32) NOT NULL COMMENT '笔记的uid',
                             `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                             `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                             PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏表';


DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
                          `uid` varchar(32) NOT NULL COMMENT '唯一uid',
                          `user_name` varchar(255) NOT NULL COMMENT '用户名',
                          `pass_word` varchar(32) NOT NULL COMMENT '密码',
                          `sex` tinyint(1) unsigned DEFAULT NULL COMMENT '性别(1:男0:女)',
                          `avatar` varchar(100) DEFAULT NULL COMMENT '个人头像',
                          `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
                          `birthday` date DEFAULT NULL COMMENT '出生年月日',
                          `mobile` varchar(50) DEFAULT NULL COMMENT '手机',
    /* `valid_code` varchar(255) DEFAULT NULL COMMENT '邮箱验证码',*/
                          `summary` varchar(200) DEFAULT NULL COMMENT '自我简介最多150字',
                          `login_count` int(11) unsigned DEFAULT '0' COMMENT '登录次数',
                          `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
                          `last_login_ip` varchar(50) DEFAULT '127.0.0.1' COMMENT '最后登录IP',
                          `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                          `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                          `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                          `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
                          `uuid` varchar(255) DEFAULT NULL COMMENT '平台uuid',
                          `we_chat` varchar(255) DEFAULT NULL COMMENT '微信号',
                          `comment_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '评论状态 1:正常 0:禁言',
                          `ip_source` varchar(255) DEFAULT NULL COMMENT 'ip来源',
                          `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
                          `os` varchar(255) DEFAULT NULL COMMENT '操作系统',
                          `start_email_notification` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否开启邮件通知 1:开启 0:关闭',
                          `user_tag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户标签：0：普通用户，1：管理员，2：博主 等',
                          `loading_valid` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否通过加载校验【0 未通过，1 已通过】',
                          PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


DROP TABLE IF EXISTS `t_sys_dict_type`;

CREATE TABLE `t_sys_dict_type` (
                                   `uid` varchar(32) NOT NULL COMMENT '主键',
                                   `dict_name` varchar(255) DEFAULT NULL COMMENT '字典名称',
                                   `dict_type` varchar(255) DEFAULT NULL COMMENT '字典类型',
                                   `create_by_uid` varchar(32) NOT NULL COMMENT '创建人UID',
                                   `update_by_uid` varchar(32) NOT NULL COMMENT '最后更新人UID',
                                   `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                   `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                                   `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                   `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                   `is_publish` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否发布(1:是，0:否)',
                                   `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
                                   PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='字典类型表';


DROP TABLE IF EXISTS `t_sys_dict_data`;

CREATE TABLE `t_sys_dict_data` (
                                   `uid` varchar(32) NOT NULL COMMENT '主键',
                                   `dict_type_uid` varchar(255) DEFAULT NULL COMMENT '字典类型UID',
                                   `dict_label` varchar(255) DEFAULT NULL COMMENT '字典标签',
                                   `dict_value` varchar(255) DEFAULT NULL COMMENT '字典键值',
                                   `css_class` varchar(255) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
                                   `list_class` varchar(255) DEFAULT NULL COMMENT '表格回显样式',
                                   `is_default` tinyint(1) DEFAULT '0' COMMENT '是否默认（1是 0否）,默认为0',
                                   `create_by_uid` varchar(32) DEFAULT NULL COMMENT '创建人UID',
                                   `update_by_uid` varchar(32) DEFAULT NULL COMMENT '最后更新人UID',
                                   `remark` varchar(255) DEFAULT NULL COMMENT '备注',
                                   `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                                   `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                                   `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                                   `is_publish` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否发布(1:是，0:否)',
                                   `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
                                   PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8 COMMENT='字典数据表';



CREATE TABLE `t_note_sort` (
                               `uid` varchar(32) NOT NULL COMMENT '唯一uid',
                               `sort_name` varchar(255) DEFAULT NULL COMMENT '分类内容',
                               `content` varchar(255) DEFAULT NULL COMMENT '分类简介',
                               `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                               `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                               `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                               `sort` int(11) DEFAULT '0' COMMENT '排序字段，越大越靠前',
                               `click_count` int(11) DEFAULT '0' COMMENT '点击数',
                               PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='笔记分类表';

/*Data for the table `t_note_sort` */

insert  into `t_note_sort`(`uid`,`sort_name`,`content`,`create_time`,`update_time`,`is_delete`,`sort`,`click_count`) values ('01b604bb1be10b32847f6fc64e1111bf','测试2','测试2','2020-10-13 10:00:44','2020-10-13 10:00:52',0,0,0),('029d80ba36a04c96a89a80e2705031a2','测试分类','测试分类','2019-01-11 21:10:43','2019-01-11 21:10:43',0,0,0),('043e2062e18497fc283d30657e800dac','JVM','Java虚拟机','2020-03-10 07:28:04','2020-03-10 07:28:04',0,0,0),('093d8bdd01c84890a928e923d5c235fe','软件推荐','软件推荐','2018-09-25 16:14:59','2020-10-13 10:00:23',1,0,5),('2c93dfab0e754006866f8ed486923a41','慢生活','慢生活，不是懒惰，放慢速度不是拖延时间，而是让我们在生活中寻找到平衡','2018-09-25 15:29:33','2020-10-13 10:00:23',1,0,19),('337806254f9c42999043de5c5ee09e77','技术新闻','发现世界的每一天','2018-12-30 10:42:11','2020-10-13 10:00:23',1,0,58),('9d2019983d91490aaa758eddd7c07caf','机器学习','机器学习','2018-11-22 20:56:02','2020-10-13 10:00:23',1,0,3),('a03d7290b1c04b6eaf46659661b47032','后端开发','后端开发专题','2018-12-30 10:35:43','2020-10-13 10:00:23',1,6,362),('ca28ffc94ea94fbda5571e0b242021e2','前端开发','前端开发专题','2018-12-30 10:35:58','2020-10-13 10:00:23',1,0,56),('db0d64ea7df409de5d2d747927cfa1a5','学习笔记','学习笔记','2019-08-31 09:50:03','2020-10-13 10:00:23',1,3,111),('e4ccfe610a5d59538836ddbf4dcb31c7','分类名称','分类介绍','2020-10-13 10:00:08','2020-10-13 10:00:14',0,0,0),('e60df954efcd47c48463a504bb70bbe9','面试','面试专题','2018-12-20 21:16:30','2020-10-13 10:00:23',1,0,43);



DROP TABLE IF EXISTS `t_tag`;

CREATE TABLE `t_tag` (
                         `uid` varchar(32) NOT NULL COMMENT '唯一uid',
                         `content` varchar(1000) DEFAULT NULL COMMENT '标签内容',
                         `click_count` int(11) DEFAULT '0' COMMENT '标签简介',
                         `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                         `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                         `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                         `sort` int(11) DEFAULT '0' COMMENT '排序字段，越大越靠前',
                         PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

/*Data for the table `t_tag` */

insert  into `t_tag`(`uid`,`content`,`is_delete`,`click_count`,`create_time`,`update_time`,`sort`) values ('0b51c75ed5744cdcadefe0ad947be9b6','数据库',1,78,'2020-10-13 10:01:00','2020-12-26 11:24:33',0),('15721a34adba068763b5a2fb1991fc57','JVM',1,33,'2020-10-13 10:01:00','2021-01-24 10:00:58',3),('188396dc0efcae369856fe472df1ed09','标签2',0,0,'2020-10-13 10:01:10','2020-10-13 10:01:15',6),('1c76b9848f5f4d71a5e88b20dbaf38f4','RabbitMQ',1,106,'2020-10-13 10:01:00','2020-12-26 11:24:33',0),('1d1fd6d26c8e40a38637ef6126c45cd0','Linux',1,336,'2020-10-13 10:01:00','2020-10-13 10:01:03',1),('2a31dd6c2b1b464e9e222a1198bc739a','虚拟机',1,58,'2020-10-13 10:01:00','2020-12-26 11:24:34',0),('2f5779e877da48958c985d69b311d0d6','大数据',1,72,'2020-10-13 10:01:00','2020-12-26 11:24:34',0),('3c16b9093e9b1bfddbdfcb599b23d835','Nginx',1,32,'2020-10-13 10:01:00','2020-12-26 11:24:36',0),('53c5a0f3142e4f54820315936f78383b','Spring Boot',1,73,'2020-10-13 10:01:00','2020-12-26 11:24:35',0),('5626932d452c2ad863d9b3cb0b69d22d','学习笔记',1,237,'2020-10-13 10:01:00','2021-06-13 07:48:23',5),('5c939107ddb746b989156737805df625','机器学习',1,109,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('618346963de0fc724e44c6f9120aea9c','Github',1,17,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('6d35ddd5075f4c0e885ffb2e3b3a0365','Tomcat',1,58,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('7e0e93ea6cdb44ae92e58f48e6496ed7','Java',1,1644,'2020-10-13 10:01:00','2021-06-13 07:47:58',4),('8c9d43de144245eb8176854eca5ae244','AI',1,20,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('8d5ce3e0c0784b95adb7f9e7b76dca93','建站系统',1,100,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('a9a747d944c24845815356f72723ef8e','前端开发',1,114,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('b22b9bdc32a442dd65dee82cdc5cf800','计算机网络',1,16,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('ca928e8718654aa5a802e2f69277b137','面试',1,193,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('d3c3fc43f38445389c970ff0732a6586','NLP',1,39,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('dececd440fdc4fa28dffe6404e696dd4','Python',1,19,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('e2c7913050cf4ab9aa92902316aaf075','校园生活',1,169,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('e81bc2dca42c4031be7d66fef4a71e16','Spring Cloud',1,110,'2020-10-13 10:01:00','2020-12-26 11:24:32',2),('ebf63989f11741bc89494c52fc6bae4c','Docker',1,96,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('f5d458db6a044eaebc22232efa1e3b54','深度学习',1,66,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('f90d3c2fd9434302951130e897a89164','Vue',1,90,'2020-10-13 10:01:00','2020-10-13 10:01:03',0),('fb72516226474cf0bfa0f310bfa75426','Redis',1,61,'2020-10-13 10:01:00','2020-10-13 10:01:03',0);


DROP TABLE IF EXISTS `t_picture`;

CREATE TABLE `t_picture` (
                             `uid` varchar(32) NOT NULL COMMENT '唯一uid',
                             `file_uid` varchar(32) DEFAULT NULL COMMENT '图片uid',
                             `pic_name` varchar(255) DEFAULT NULL COMMENT '图片名',
                             `picture_sort_uid` varchar(32) DEFAULT NULL COMMENT '分类uid',
                             `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                             `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                             PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片表';


DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
                             `uid` varchar(32) NOT NULL COMMENT '唯一uid',
                             `content` varchar(32) DEFAULT NULL COMMENT '内容',
                             `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
                             `user_uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '投稿用户UID',
                             `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                             `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                             `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                             PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='留言表';

DROP TABLE IF EXISTS `t_web_visit`;

CREATE TABLE `t_web_visit` (
                               `uid` varchar(32) NOT NULL COMMENT '主键',
                               `user_uid` varchar(255) DEFAULT NULL COMMENT '用户uid',
                               `ip` varchar(255) DEFAULT NULL COMMENT '访问ip地址',
                               `behavior` varchar(255) DEFAULT NULL COMMENT '用户行为',
                               `module_uid` varchar(255) DEFAULT NULL COMMENT '模块uid（文章uid，标签uid，分类uid）',
                               `other_data` varchar(255) DEFAULT NULL COMMENT '附加数据(比如搜索内容)',
                               `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
                               `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
                               `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
                               `os` varchar(255) DEFAULT NULL COMMENT '操作系统',
                               `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
                               `ip_source` varchar(255) DEFAULT NULL COMMENT 'ip来源',
                               PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Web访问记录表';

ALTER TABLE  t_user ADD status tinyint(1)  unsigned NOT NULL DEFAULT '1' COMMENT '状态，2表示被拉黑';
