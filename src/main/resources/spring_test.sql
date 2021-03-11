/*
Navicat MySQL Data Transfer

Source Server         : localhost_bill99
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : spring_test

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-06-01 17:17:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '部门名称',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否删除:0.否，1.是',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'yyyy-MM-dd HH:mm:ss	创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'yyyy-MM-dd HH:mm:ss	更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of department
-- ----------------------------

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `last_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT '' COMMENT '名称',
  `email` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '邮箱',
  `gender` tinyint(1) DEFAULT '0' COMMENT '性别: 0.男;1.女',
  `depart_id` int(10) DEFAULT NULL COMMENT '部门id',
  `is_delete` tinyint(4) DEFAULT '0' COMMENT '是否删除:0.否,1.是',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'yyyy-MM-dd HH:mm:ss	创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'yyyy-MM-dd HH:mm:ss	更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'lastName', 'email', '0', '1', '0', '2020-05-28 15:00:55', '2020-05-28 15:00:55');
INSERT INTO `employee` VALUES ('2', 'lastName2', 'email2', '0', '2', '0', '2021-02-26 15:00:55', '2021-02-26 15:00:55');
