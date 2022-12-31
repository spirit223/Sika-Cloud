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

 Date: 23/12/2022 21:06:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`
(
    `question_id`          int                                                          NOT NULL AUTO_INCREMENT,
    `question_type`        char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL DEFAULT NULL,
    `question_content`     TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci        NOT NULL,
    `question_image`       mediumblob                                                   NULL,
    `question_description` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci        NULL DEFAULT NULL,
    `answer_id`            int                                                          NULL DEFAULT NULL,
    `question_topic`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
    PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question`
VALUES (7, '2', 'testHttpEnum', NULL, 'testHttpEnum', 3, 'testHttpEnum');
INSERT INTO `question`
VALUES (9, '1', '给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度', NULL, '', 2, '字符串');
INSERT INTO `question`
VALUES (10, '2', '给你一个链表数组，每个链表都已经按升序排列', NULL,
        '请你将所有链表合并到一个升序链表中，返回合并后的链表', 3, '链表');
INSERT INTO `question`
VALUES (11, '1',
        '给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表',
        NULL, '可以假设除了数字 0 之外，这两个数都不会以 0 开头', 1, '算法题');
INSERT INTO `question`
VALUES (12, '1', '给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度', NULL, '', 2, '字符串');
INSERT INTO `question`
VALUES (13, '2', '给你一个链表数组，每个链表都已经按升序排列', NULL,
        '请你将所有链表合并到一个升序链表中，返回合并后的链表', 3, '链表');
INSERT INTO `question`
VALUES (14, '1',
        '给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表',
        NULL, '可以假设除了数字 0 之外，这两个数都不会以 0 开头', 1, '算法题');
INSERT INTO `question`
VALUES (15, '1', '给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度', NULL, '', 2, '字符串');
INSERT INTO `question`
VALUES (16, '2', '给你一个链表数组，每个链表都已经按升序排列', NULL,
        '请你将所有链表合并到一个升序链表中，返回合并后的链表', 3, '链表');
INSERT INTO `question`
VALUES (17, '1',
        '给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表',
        NULL, '可以假设除了数字 0 之外，这两个数都不会以 0 开头', 1, '算法题');

SET FOREIGN_KEY_CHECKS = 1;
