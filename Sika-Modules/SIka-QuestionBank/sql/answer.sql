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

 Date: 23/12/2022 21:05:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`
(
    `answer_id`      int                                                   NOT NULL AUTO_INCREMENT,
    `answer_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
    `answer_image`   mediumblob                                            NULL,
    `question_id`    int                                                   NULL DEFAULT NULL,
    PRIMARY KEY (`answer_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer`
VALUES (1, 'id为1的答案', NULL, NULL);
INSERT INTO `answer`
VALUES (2, '测试批量插入answer1', NULL, 0);
INSERT INTO `answer`
VALUES (3, '测试批量插入answer2', NULL, 10);
INSERT INTO `answer`
VALUES (4, '测试批量插入answer3', NULL, 10);
INSERT INTO `answer`
VALUES (5, '测试批量插入answer4', NULL, 10);
INSERT INTO `answer`
VALUES (6, '测试批量插入answer1', NULL, -1);
INSERT INTO `answer`
VALUES (7, '测试批量插入answer2', NULL, -1);

SET FOREIGN_KEY_CHECKS = 1;
