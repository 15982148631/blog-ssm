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

 Date: 22/02/2021 21:24:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for core_post
-- ----------------------------
DROP TABLE IF EXISTS `core_post`;
CREATE TABLE `core_post`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `post_author` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '对应作者 ID',
  `post_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '正文',
  `post_title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `post_excerpt` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '摘要',
  `post_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'publish' COMMENT '文章状态：publish(发布) draft(草稿)',
  `comment_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'open' COMMENT '评论状态：open(开放) closed(关闭)',
  `post_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '文章缩略名',
  `post_content_filtered` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章内容过滤',
  `post_parent` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父文章',
  `guid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '唯一标识符(短链接)',
  `menu_order` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `post_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'post' COMMENT '文章类型：post(文章) technology(技术) tools(工具) books(书籍)',
  `post_mime_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'MIME 类型',
  `comment_count` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '评论总数',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_post_name`(`post_name`) USING BTREE,
  INDEX `idx_type_status_date`(`post_type`, `post_status`, `create_time`, `id`) USING BTREE,
  INDEX `idx_post_parent`(`post_parent`) USING BTREE,
  INDEX `idx_post_author`(`post_author`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of core_post
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
