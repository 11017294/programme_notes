/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : programme_notes

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 18/02/2022 17:48:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一uid',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `pass_word` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `sex` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '性别(1:男0:女)',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人头像',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `birthday` date NULL DEFAULT NULL COMMENT '出生年月日',
  `mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `summary` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自我简介最多150字',
  `login_count` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '登录次数',
  `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '127.0.0.1' COMMENT '最后登录IP',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台uuid',
  `we_chat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号',
  `comment_status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '评论状态 1:正常 0:禁言',
  `ip_source` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ip来源',
  `browser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `start_email_notification` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否开启邮件通知 1:开启 0:关闭',
  `user_tag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '用户标签：0：普通用户，1：管理员，2：博主 等',
  `loading_valid` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否通过加载校验【0 未通过，1 已通过】',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'chen', 'a1a8887793acfc199182a649e905daab', 0, '1111', '11@qq.com', '2022-01-01', '150', '123131321', 0, NULL, '127.0.0.1', 0, '2022-02-18 16:26:30', '2022-02-18 15:11:49', 'nic', '', '', 0, NULL, NULL, NULL, 0, 0, 0);
INSERT INTO `t_user` VALUES ('6c1af5e152646129202e0b8680a108', 'chen123', 'd30b6a4d9e94cbb9db971c6283cfcd9e', NULL, NULL, '10@qq.com', NULL, '15089406727', '1', 1, '2022-02-16 12:27:04', '127.0.0.1', 0, '2022-02-18 16:26:16', '2022-02-18 16:25:07', NULL, NULL, NULL, 1, NULL, NULL, NULL, 0, 0, 0);
INSERT INTO `t_user` VALUES ('6c1af5ee15asd6469202e0b8680a108', '666', 'fae0b27c451c728867a567e8c1bb4e53', 1, '1', '10@qq.com', '2022-02-15', '15089406727', '1', 25, '2022-02-17 07:29:45', '127.0.0.1', 0, '2022-02-18 16:26:03', '2022-02-18 16:25:17', NULL, NULL, NULL, 1, NULL, NULL, NULL, 0, 0, 0);
INSERT INTO `t_user` VALUES ('6c1af5ee1a45626469202e0b8680a108', 'password', '5f4dcc3b5aa765d61d8327deb882cf99', 0, '头像', '1101@qq.com', '2021-01-01', '150894', '自我介绍', 13, '2022-02-18 16:23:51', '127.0.0.1', 0, '2022-02-18 16:20:54', '2022-02-18 16:23:20', '昵称', '123', '3213', 1, NULL, NULL, NULL, 0, 0, 0);
INSERT INTO `t_user` VALUES ('6c1af5eesa6tfdg26469202e80a108', '123', '202cb962ac59075b964b07152d234b70', 1, '1', '10@qq.com', '2022-02-16', '15089406727', '1', 5, '2022-02-18 17:33:20', '127.0.0.1', 0, '2022-02-18 16:25:52', '2022-02-18 16:25:02', NULL, NULL, NULL, 1, NULL, NULL, NULL, 0, 0, 0);
INSERT INTO `t_user` VALUES ('df0549fd6c4f6c5fd1508c39258da35f', 'password', '5f4dcc3b5aa765d61d8327deb882cf99', 0, '头像', '1101@qq.com', '2021-01-01', '150894', '自我介绍', 0, NULL, '127.0.0.1', 0, '2022-02-18 12:21:12', '2022-02-18 16:23:16', '昵称', '123', '3213', 1, NULL, NULL, NULL, 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
