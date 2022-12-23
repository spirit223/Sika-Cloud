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

 Date: 23/12/2022 21:07:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `name`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'NULL' COMMENT '角色名称',
    `role_key`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'NULL' COMMENT '角色权限字符串',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      NULL DEFAULT '0' COMMENT '角色状态(0正常 1停用)',
    `del_flag`    int                                                           NULL DEFAULT 0 COMMENT '角色删除标志(0未删除 1已删除)',
    `create_by`   int                                                           NULL DEFAULT NULL COMMENT '创建人用户id',
    `create_time` datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   int                                                           NULL DEFAULT NULL COMMENT '修改人用户id',
    `update_time` datetime                                                      NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '管理员', 'cc:admin', '0', 0, -1, '2022-12-19 22:03:19', -1, '2022-12-20 16:37:11', '');
INSERT INTO `sys_role`
VALUES (2, '普通用户', 'cc:user', '0', 0, -1, '2022-12-19 22:04:55', -1, '2022-12-20 16:37:15', '');
INSERT INTO `sys_role`
VALUES (3, '学生', 'cc:student', '0', 0, -1, '2022-12-19 22:04:55', -1, '2022-12-20 16:37:18', '');
INSERT INTO `sys_role`
VALUES (4, '教师', 'cc:teacher', '0', 0, -1, '2022-12-19 22:04:55', -1, '2022-12-20 16:37:26', '');

SET FOREIGN_KEY_CHECKS = 1;
