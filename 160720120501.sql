/*
MySQL Backup
Source Server Version: 5.1.73
Source Database: readdb
Date: 2016/7/20 12:05:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `f_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `f_role_permission`;
CREATE TABLE `f_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `f_user_hb`
-- ----------------------------
DROP TABLE IF EXISTS `f_user_hb`;
CREATE TABLE `f_user_hb` (
  `uh_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `h_id` int(11) NOT NULL,
  PRIMARY KEY (`uh_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_city`
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `path_code` varchar(255) DEFAULT NULL,
  `sort` int(4) DEFAULT '1',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_feed_back`
-- ----------------------------
DROP TABLE IF EXISTS `t_feed_back`;
CREATE TABLE `t_feed_back` (
  `fid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '意见反馈递增主键',
  `uname` varchar(255) DEFAULT NULL COMMENT '用户名',
  `comment` varchar(255) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deal_time` datetime DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_hb`
-- ----------------------------
DROP TABLE IF EXISTS `t_hb`;
CREATE TABLE `t_hb` (
  `hid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` int(11) NOT NULL,
  `sort` int(2) DEFAULT '1',
  PRIMARY KEY (`hid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_hb_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_hb_type`;
CREATE TABLE `t_hb_type` (
  `ht_id` int(11) NOT NULL AUTO_INCREMENT,
  `ht_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ht_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(30) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `sort` int(3) DEFAULT '1',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_photo`
-- ----------------------------
DROP TABLE IF EXISTS `t_photo`;
CREATE TABLE `t_photo` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  `upload_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `telphone` varchar(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `state` int(1) DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8890 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `t_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `user_id` int(11) NOT NULL,
  `nick_name` varchar(50) DEFAULT NULL,
  `city_id` int(5) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `qq_number` varchar(20) DEFAULT NULL,
  `wx_number` varchar(40) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `except` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `f_role_permission` VALUES ('1','1','1');
INSERT INTO `f_user_hb` VALUES ('1','8888','1'), ('2','8888','4'), ('3','8888','5'), ('4','8888','2');
INSERT INTO `t_city` VALUES ('1','四川',NULL,NULL,'1'), ('2','成都','1','1','1'), ('3','锦江','2','1,2','1'), ('4','双流','2','1,2','2'), ('6','眉山','1','1','2'), ('7','贵州',NULL,NULL,'2');
INSERT INTO `t_feed_back` VALUES ('8','xiaochangwei','testcomm',NULL,NULL), ('9','xiaochangwei','this is test',NULL,NULL);
INSERT INTO `t_hb` VALUES ('1','跑步','1','3'), ('2','爬山','1','1'), ('3','游泳','1','2'), ('4','英雄联盟','2','2'), ('5','魔兽世界','2','1');
INSERT INTO `t_hb_type` VALUES ('1','运动类'), ('2','游戏类');
INSERT INTO `t_permission` VALUES ('1','viewInfo',NULL,NULL,'1');
INSERT INTO `t_photo` VALUES ('1','8888','http://09.imgmini.eastday.com/mobile/20160712/20160712191443_b7a55523e3bfc750a28992a67b664332_1.jpeg','2016-07-13 19:49:27'), ('2','8888','http://img.mp.itc.cn/upload/20160713/21c7743985f943b387a9fb467e2b6c1d_th.jpg','2016-07-13 19:49:30');
INSERT INTO `t_role` VALUES ('1','普通用户','pt'), ('2','市级用户','sj'), ('3','省级用户','shengji'), ('4','全国用户','all');
INSERT INTO `t_user` VALUES ('8888','xiaochangwei','123456','18980958050','1','1'), ('8889','admin','123456','18888888888','2','1');
INSERT INTO `t_user_info` VALUES ('8888','肖哥哥','4','成都市天府新区','317409898','xgg923','317409898@qq.com','29','中国坚定维护在南海的领土主权和海洋权益，同时坚持通过谈判协商解决争议，坚持通过规则机制管控分歧，坚持通过互利合作实现共赢，致力于把南海建设成和平之海、友谊之海和合作之海','http://image5.huangye88.com/2013/07/19/0b53046cc95de9be.jpg','刘振民还特别强调称，南海仲裁案由菲律宾共和国时任政府单方面提起，有违中菲两国协议和地区国家共识，不符合国际法，注定要被抛弃。中方也注意到杜特尔特总统阁下和菲律宾新政府在南海仲裁问题上做出的积极表态，愿同中方就南海问题进行协商对话，中方对此表示欢迎。中方愿同菲律宾新政府共同努力，妥善处理南海问题，推动中菲关系早日重回正轨。双方越早移除仲裁案的障碍，就越有利于尽早全面启动合作，尽快为两国人民带来实实在在的合作成果。中方对中菲关系的明天抱有信心!'), ('8889','昵称','6','眉山市东坡区','qqhao','wxhao','sdfsdf@qq.com','30','签名','http://image5.huangye88.com/2013/07/19/0b53046cc95de9be.jpg','期望很高');
