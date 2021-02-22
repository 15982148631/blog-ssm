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

 Date: 22/02/2021 21:23:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for agency_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `agency_admin_role`;
CREATE TABLE `agency_admin_role`  (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `uid` int(30) NULL DEFAULT NULL,
  `rid` int(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1013 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agency_admin_role
-- ----------------------------
INSERT INTO `agency_admin_role` VALUES (1011, 1, 100);
INSERT INTO `agency_admin_role` VALUES (1012, 1, 101);

SET FOREIGN_KEY_CHECKS = 1;
