/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50508
Source Host           : localhost:3306
Source Database       : devplatform

Target Server Type    : MYSQL
Target Server Version : 50508
File Encoding         : 65001

Date: 2017-08-09 11:17:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `formfieldmeta`
-- ----------------------------
DROP TABLE IF EXISTS `formfieldmeta`;
CREATE TABLE `formfieldmeta` (
  `ffid` varchar(40) NOT NULL,
  `fid` varchar(40) DEFAULT NULL,
  `note` varchar(100) DEFAULT NULL,
  `ffname` varchar(50) DEFAULT NULL,
  `required` varchar(1) DEFAULT NULL,
  `datatype` varchar(30) DEFAULT NULL,
  `datalength` int(11) DEFAULT NULL,
  `defaultvalue` varchar(50) DEFAULT NULL,
  `sn` int(11) DEFAULT NULL,
  PRIMARY KEY (`ffid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formfieldmeta
-- ----------------------------
INSERT INTO `formfieldmeta` VALUES ('3ef0caa1-88af-4263-b182-8ec9dc337aa1', '3997e353-3078-4351-88f4-c9a51f8a9b90', '姓名', 'xm', '1', 'varchar', '20', null, '0');
INSERT INTO `formfieldmeta` VALUES ('6b2c30ec-fcde-481d-9998-662ba6ccd745', '3997e353-3078-4351-88f4-c9a51f8a9b90', '天数', 'tianshu', '0', 'int', '3', null, '1');

-- ----------------------------
-- Table structure for `forminfo`
-- ----------------------------
DROP TABLE IF EXISTS `forminfo`;
CREATE TABLE `forminfo` (
  `fid` varchar(40) NOT NULL,
  `tid` int(11) DEFAULT NULL,
  `fname` varchar(50) DEFAULT NULL,
  `fmark` varchar(200) DEFAULT NULL,
  `ts` varchar(30) DEFAULT NULL,
  `rs` char(1) DEFAULT NULL,
  `iscreate` char(1) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forminfo
-- ----------------------------
INSERT INTO `forminfo` VALUES ('3997e353-3078-4351-88f4-c9a51f8a9b90', '1', 'qjt', '请假条', null, null, null);

-- ----------------------------
-- Table structure for `formtype`
-- ----------------------------
DROP TABLE IF EXISTS `formtype`;
CREATE TABLE `formtype` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tname` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of formtype
-- ----------------------------
INSERT INTO `formtype` VALUES ('1', '测试');
INSERT INTO `formtype` VALUES ('2', '业务');

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` varchar(40) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名',
  `parentid` varchar(40) DEFAULT NULL COMMENT '上级菜单',
  `idx` int(11) DEFAULT NULL COMMENT '序号',
  `url` varchar(100) DEFAULT NULL COMMENT 'url',
  `ts` varchar(30) DEFAULT NULL COMMENT '时间戳',
  `rs` char(1) DEFAULT NULL COMMENT '状态',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统管理', '0', '1', null, null, '1', '系统管理模块');
INSERT INTO `menu` VALUES ('1-1', '系统设置', '1', '10', null, null, '1', '系统设置');
INSERT INTO `menu` VALUES ('1-1-1', '菜单管理', '1-1', '100', '/menu/list', null, '1', '菜单管理');
INSERT INTO `menu` VALUES ('1-1-2', '数据库连接管理', '1-1', '101', null, null, '1', '数据库连接管理');
INSERT INTO `menu` VALUES ('2', '表单管理', '0', '2', '', '2017-08-02 16:40:59', '1', '表单管理模块');
INSERT INTO `menu` VALUES ('2-1', '表单管理', '2', '20', '', '2017-08-02 16:08:49', '1', '表单管理');
INSERT INTO `menu` VALUES ('2-1-1', '业务对象定义', '2-1', '200', '', '2017-08-02 16:20:58', '1', '业务对象定义');
INSERT INTO `menu` VALUES ('2-1-2', '实体管理', '2-1', '201', '/forminfo/list', '2017-08-07 15:15:25', '1', '数据库表元数据定义');
INSERT INTO `menu` VALUES ('2-1-3', '表单元数据定义', '2-1', '202', '', '2017-08-02 16:25:23', '1', '页面表单定义');
INSERT INTO `menu` VALUES ('2-1-4', '业务表单', '2-1', '203', '', '2017-08-02 16:28:57', '1', '选择表单元数据，选择表单模板，来设置表格');
INSERT INTO `menu` VALUES ('2-1-5', '表单模板', '2-1', '204', '', '2017-08-02 16:33:04', '1', '表单模板定义');
INSERT INTO `menu` VALUES ('3', '流程管理', '0', '3', '', '2017-08-02 16:41:45', '1', '流程管理模块');
INSERT INTO `menu` VALUES ('3-1', '流程管理', '3', '30', '', '2017-08-02 16:42:49', '1', '流程管理');
INSERT INTO `menu` VALUES ('3-1-1', '流程列表', '3-1', '300', '', '2017-08-02 16:44:35', '1', '流程设计，流程配置等');
INSERT INTO `menu` VALUES ('3-1-2', '流程实例管理', '3-1', '301', '', '2017-08-02 16:46:12', '1', '流程实例管理');
INSERT INTO `menu` VALUES ('3-1-3', '任务管理', '3-1', '302', '', '2017-08-02 16:48:52', '1', '任务管理');
INSERT INTO `menu` VALUES ('3-1-4', '分管授权', '3-1', '303', '', '2017-08-02 16:50:31', '1', '分管授权，给角色分权限');
INSERT INTO `menu` VALUES ('3-1-5', '代理设定', '3-1', '304', '', '2017-08-02 16:51:27', '1', '代理设定，设置谁可以代替谁办公');
INSERT INTO `menu` VALUES ('3-1-6', '流程消息模板', '3-1', '305', '', '2017-08-03 09:25:28', '1', '流程消息模板');
INSERT INTO `menu` VALUES ('4', '用户组织管理', '0', '4', '', '2017-08-02 16:54:47', '1', '用户组织管理');
