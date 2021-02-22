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

 Date: 22/02/2021 21:24:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for core_role
-- ----------------------------
DROP TABLE IF EXISTS `core_role`;
CREATE TABLE `core_role`  (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of core_role
-- ----------------------------
INSERT INTO `core_role` VALUES (100, '管理员');
INSERT INTO `core_role` VALUES (101, '操作员');

SET FOREIGN_KEY_CHECKS = 1;
