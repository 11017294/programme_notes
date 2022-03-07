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

 Date: 18/02/2022 17:48:48
*/

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '笔记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_note
-- ----------------------------
INSERT INTO `t_note` VALUES ('0ce997f22aff75b34d6ca9a0ea6770f7', 'test23', 'jj', 'asdtest', 'aa', 0, 0, '456', 1, '2022-02-10 17:15:27', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '22', 0);
INSERT INTO `t_note` VALUES ('0f24ddbffd74fc0ac57a673110fc132e', 'test22', 'jj', 'asdtest', 'aa', 0, 0, '456', 1, '2022-02-10 17:15:27', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '22', 0);
INSERT INTO `t_note` VALUES ('142fb94e1e7bf6e3fbbffbb894c148c2', 'test22', 'jj', 'asdtest', 'aa', 0, 0, '456', 1, '2022-02-10 17:15:29', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '22', 0);
INSERT INTO `t_note` VALUES ('1f7dea8ba56759bc61585a32c215351c', 'test5566', 'jj', 'asdtest', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:29', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '22', 0);
INSERT INTO `t_note` VALUES ('29cd65e0b5bca49e2f69b1f58ba40d66', 'test98', 'jj', 'asdtest', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:28', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '22', 0);
INSERT INTO `t_note` VALUES ('32a4409287124a3f2569e378fa205886', 'test-34', 'jj', 'asdtest', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:27', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '22', 0);
INSERT INTO `t_note` VALUES ('3588e7c5456136cd4d722d07ac57ec8d', 'test4376', 'jj', 'sadsatest', 'aa', 0, 0, '456', 0, '2022-02-10 16:31:39', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '22', 0);
INSERT INTO `t_note` VALUES ('3ee675014e465a3d3c2111127ffbd19a', 'test788', 'jj', 'sadsatest', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:28', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('5b2128d96d1bb8b676152b23935f80e6', '4似懂非懂', 'jj', 'sadsatest', 'aa', 0, 0, '456', 0, '2022-02-10 16:48:48', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('61d1da75d4dda41ad2a73adde9c819a5', '防守打法个人', 'jj', 'sadsatest', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:26', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('84b8d889bba6b6979629a7853c7fee42', '头发也很听话', 'jj', 'sadsatest', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:27', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('88d6d2bbaa318a2bd4f77bbd08618248', 'test5566', 'jj', 'asdtest', 'aa', 0, 0, '456', 0, '2022-02-18 17:37:35', '2022-02-18 17:37:35', NULL, '', 'i', 'apiTest', '1', '1', 0, 1, '22', 0);
INSERT INTO `t_note` VALUES ('9966294a4efc14c21d72cfddb8e432cc', '居居', 'jj', 'sadsatest', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:27', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('a1bc89288c16cc83f541cff07b9c8676', '很讨厌他', 'jj', 'sadsatest', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:28', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('a35b9796c345b90a8fc979f40a4f0b5e', '给他任何', 'jj', 'dwadawd ', 'aa', 0, 0, '456', 0, '2022-02-10 16:31:23', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('a8dcf7b288d672f21aa824f0065a1e1e', '发热发', 'gt', 'dwadawd ', '1', 0, 0, '1', 0, '2022-02-10 16:48:48', '2022-02-18 16:46:39', '11', '', '1', '11', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('aeb4b84fc9a9e7c25d1bb64735fc9f9b', '分色分色', 'gt', 'dwadawd ', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:28', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('c276d7c5f74f5acbc38b89299c87a069', '挖到大无', 'kjhktest,lk', 'dwadawd ', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:27', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);
INSERT INTO `t_note` VALUES ('d546f185ae9cdc6b5060d59cc4262ede', '', '', '11111', '', 0, 0, '', 0, '2022-02-10 17:15:28', '2022-02-18 15:03:08', '123213', '', '111', '22121', '', '', 0, 0, '', 1);
INSERT INTO `t_note` VALUES ('e10532ea83d2255452b0a3c47ac3c274', '', '', 'fsef', '', 0, 0, '', 1, '2022-02-16 10:08:42', '2022-02-18 16:46:39', '4324', '', '', '123', '', '1', 0, 1, 'asdas', 1);
INSERT INTO `t_note` VALUES ('ee61332dc1d8398b3b76ba78a5bc2fb6', 'test5566', 'jj', 'asdtest', 'aa', 0, 0, '456', 0, '2022-02-18 17:39:07', '2022-02-18 16:47:59', '4324', '', 'i', 'apiTest', '1', '1', 0, 1, '22', 0);
INSERT INTO `t_note` VALUES ('ff784e4a3f25f8218bb4cb13fdd49f53', '高铁', 'kjhktest,lk', 'dwadawd ', 'aa', 0, 0, '456', 0, '2022-02-10 17:15:26', '2022-02-18 16:46:39', '123555', '', 'i', 'apiTest', '1', '1', 0, 1, '33', 0);

SET FOREIGN_KEY_CHECKS = 1;
