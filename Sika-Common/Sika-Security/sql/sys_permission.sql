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

 Date: 23/12/2022 21:07:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`
(
    `id`              int                                                           NOT NULL AUTO_INCREMENT COMMENT '权限id',
    `permission_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT 'NULL' COMMENT '权限名称',
    `permissions`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '权限标识',
    `create_by`       int                                                           NULL     DEFAULT NULL COMMENT '创建人用户id',
    `create_time`     datetime                                                      NULL     DEFAULT NULL COMMENT '创建时间',
    `update_by`       int                                                           NULL     DEFAULT NULL COMMENT '更新用户id',
    `update_time`     datetime                                                      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `del_flag`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      NULL     DEFAULT NULL COMMENT '是否删除(0未删除 1已删除)',
    `remark`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission`
VALUES (2, '管理员权限', 'cc:admin:all', -1, '2022-12-19 21:02:47', -1, '2022-12-20 16:31:45', '0', '');
INSERT INTO `sys_permission`
VALUES (3, '测试权限1', 'cc:common:all', -1, '2022-12-19 21:07:06', -1, '2022-12-20 16:32:02', '0', '');
INSERT INTO `sys_permission`
VALUES (4, '测试权限2', 'cc:question:all', -1, '2022-12-19 21:07:06', -1, '2022-12-20 16:31:04', '0', '');
INSERT INTO `sys_permission`
VALUES (5, '测试权限3', 'cc:question:get', -1, '2022-12-19 21:07:06', -1, '2022-12-20 16:31:06', '0', '');

SET FOREIGN_KEY_CHECKS = 1;
