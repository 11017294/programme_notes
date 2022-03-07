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

 Date: 18/02/2022 17:48:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_collect
-- ----------------------------
DROP TABLE IF EXISTS `t_collect`;
CREATE TABLE `t_collect`  (
  `uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一uid',
  `user_uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户的uid',
  `note_uid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '笔记的uid',
  `is_delete` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '是否删除，1表示已删除',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_collect
-- ----------------------------
INSERT INTO `t_collect` VALUES ('1', '22', '3ee675014e465a3d3c2111127ffbd19a', 0, '2022-02-14 17:27:01', '2022-02-15 10:36:45');
INSERT INTO `t_collect` VALUES ('2', '22', '5b2128d96d1bb8b676152b23935f80e6', 0, '2022-02-14 17:27:22', '2022-02-15 10:36:45');
INSERT INTO `t_collect` VALUES ('3', '22', '61d1da75d4dda41ad2a73adde9c819a5', 0, '2022-02-14 17:28:54', '2022-02-15 10:36:45');
INSERT INTO `t_collect` VALUES ('4', '22', '84b8d889bba6b6979629a7853c7fee42', 0, '2022-02-15 10:34:38', '2022-02-15 10:36:45');
INSERT INTO `t_collect` VALUES ('5', '22', '9966294a4efc14c21d72cfddb8e432cc', 0, '2022-02-15 10:35:02', '2022-02-15 10:36:45');

SET FOREIGN_KEY_CHECKS = 1;
