/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost
 Source Database       : mybatis_plugin_test

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : utf-8

 Date: 06/05/2019 00:29:39 AM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户名',
  `user_name_sensitive` varchar(200) DEFAULT NULL COMMENT '用户名-脱敏后',
  `idcard` varchar(200) DEFAULT NULL COMMENT '身份证号',
  `idcard_sensitive` varchar(200) DEFAULT NULL COMMENT '脱敏的卡号',
  `json_str` varchar(500) DEFAULT '' COMMENT '一个json串，可能含有敏感信息',
  `age` int(11) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
