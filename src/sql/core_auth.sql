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

 Date: 22/02/2021 21:24:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for core_auth
-- ----------------------------
DROP TABLE IF EXISTS `core_auth`;
CREATE TABLE `core_auth`  (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of core_auth
-- ----------------------------
INSERT INTO `core_auth` VALUES (112, 'root权限');
INSERT INTO `core_auth` VALUES (113, '登录资格');
INSERT INTO `core_auth` VALUES (114, '增加管理员工');
INSERT INTO `core_auth` VALUES (115, '删除管理员工');

SET FOREIGN_KEY_CHECKS = 1;
