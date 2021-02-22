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

 Date: 22/02/2021 21:24:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for agency_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `agency_role_auth`;
CREATE TABLE `agency_role_auth`  (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `rid` int(30) NULL DEFAULT NULL,
  `aid` int(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agency_role_auth
-- ----------------------------
INSERT INTO `agency_role_auth` VALUES (101, 101, 113);
INSERT INTO `agency_role_auth` VALUES (102, 101, 114);
INSERT INTO `agency_role_auth` VALUES (103, 100, 112);

SET FOREIGN_KEY_CHECKS = 1;
