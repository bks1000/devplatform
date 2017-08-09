/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : devplatform

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-07-28 17:24:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(40) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parentid` varchar(40) DEFAULT NULL,
  `idx` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `ts` varchar(30) DEFAULT NULL,
  `rs` char(1) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统管理', '0', '1', null, null, '1', '系统管理模块');
INSERT INTO `menu` VALUES ('1-1', '系统设置', '1', '10', null, null, '1', '系统设置');
INSERT INTO `menu` VALUES ('1-1-1', '菜单管理', '1-1', '100', null, null, '1', '菜单管理');
INSERT INTO `menu` VALUES ('1-1-2', '数据库连接管理', '1-1', '101', null, null, '1', '数据库连接管理');
