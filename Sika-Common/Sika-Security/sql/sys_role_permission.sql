/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8
 Source Server Type    : MySQL
 Source Server Version : 80026 (8.0.26)
 Source Host           : localhost:3306
 Source Schema         : sika

 Target Server Type    : MySQL
 Target Server Version : 80026 (8.0.26)
 File Encoding         : 65001

 Date: 23/12/2022 21:07:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `role_id`       int NOT NULL COMMENT '角色id',
    `permission_id` int NOT NULL COMMENT '权限id',
    PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission`
VALUES (1, 2);
INSERT INTO `sys_role_permission`
VALUES (2, 2);
INSERT INTO `sys_role_permission`
VALUES (3, 5);
INSERT INTO `sys_role_permission`
VALUES (4, 4);

SET FOREIGN_KEY_CHECKS = 1;
