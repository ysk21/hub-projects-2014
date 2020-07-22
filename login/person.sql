/*
 Navicat Premium Data Transfer

 Source Server         : kgbd1020
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 192.168.1.30:3306
 Source Schema         : person

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 03/02/2020 15:00:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for person_login
-- ----------------------------
DROP TABLE IF EXISTS `person_login`;
CREATE TABLE `person_login`  (
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of person_login
-- ----------------------------
INSERT INTO `person_login` VALUES ('root', '123456');
INSERT INTO `person_login` VALUES ('admin', '1234567');

SET FOREIGN_KEY_CHECKS = 1;
