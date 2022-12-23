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

 Date: 23/12/2022 21:08:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`           int                                                           NOT NULL AUTO_INCREMENT COMMENT '用户主键',
    `username`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '用户名',
    `nick_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '昵称',
    `password`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT '123456' COMMENT '密码',
    `status`       char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      NULL DEFAULT '0' COMMENT '账号状态(0正常 1停用)',
    `email`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
    `phone_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '手机号',
    `sex`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      NULL DEFAULT '2' COMMENT '用户性别(0男 1女 2未知)',
    `char_head`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像, 存储图片路径',
    `user_type`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci      NULL DEFAULT '1' COMMENT '用户类型(0管理员 1普通用户)',
    `create_by`    int                                                           NULL DEFAULT NULL COMMENT '创建人用户id',
    `create_time`  datetime                                                      NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    int                                                           NULL DEFAULT NULL COMMENT '更新人',
    `update_time`  datetime                                                      NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `del_flag`     int                                                           NULL DEFAULT 0 COMMENT '删除标志(0未删除 1已删除)',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (2, 'spirit223', '小吴来哩', '$2a$10$Pj38DHDsBf7anY7C54tYX.45Q7TY0v0H3YKGt0InD1L2EavFYmOIa', '0',
        '2235569493@qq.com', '13553727721', '0', '', '0', -1, '2022-12-19 19:27:44', -1, '2022-12-20 20:08:11', 0);
INSERT INTO `sys_user`
VALUES (9, '测试更新用户1', '测试更新用户1', '$2a$10$s3JKf9EK9OjZbSKfva1ajOW0uLaMc0FdoYk5f1LgDqKaMrA3zeGWi', '0',
        'test@ccmail.com', '13553727721', '2', '', '1', -1, '2022-12-19 20:42:41', -1, '2022-12-20 20:08:11', 0);
INSERT INTO `sys_user`
VALUES (10, '测试更新用户2', '测试更新用户2', '$2a$10$ke0bJ/LXkyA9.Os74s5Yg.wmxkNFpghuzEFOJatls9tb1vpQ545ku', '0',
        'test@ccmail.com', '13553727721', '2', '', '1', -1, '2022-12-19 20:38:00', -1, '2022-12-20 20:08:11', 0);
INSERT INTO `sys_user`
VALUES (11, '测试用户3', '测试用户3', '$2a$10$CE5ZossduIDWCoajkvFPJ.HUfD.rSAYZP9eX/UpKOgfswnoexUNbK', '0',
        'test@ccmail.com', '13553727721', '2', '', '1', -1, '2022-12-19 20:38:00', -1, '2022-12-20 20:08:11', 0);

SET FOREIGN_KEY_CHECKS = 1;
