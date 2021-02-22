/*
 Navicat MySQL Data Transfer

 Source Server         : 192.168.248.140
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 192.168.248.140:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 22/02/2021 21:24:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for core_admin
-- ----------------------------
DROP TABLE IF EXISTS `core_admin`;
CREATE TABLE `core_admin`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '网址',
  `status` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户状态：1(已启用) 0(已禁用)',
  `is_deleted` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除：1(已删除) 0(未删除)',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_username`(`username`) USING BTREE,
  UNIQUE INDEX `uk_nickname`(`nickname`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of core_admin
-- ----------------------------
INSERT INTO `core_admin` VALUES (1, 'admin', '123456', 'admin', '', '', 0, 0, '2020-11-26 15:30:15', '2020-11-26 15:30:13');

SET FOREIGN_KEY_CHECKS = 1;
