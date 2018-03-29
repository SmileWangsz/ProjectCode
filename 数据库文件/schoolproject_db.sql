/*
Navicat MySQL Data Transfer

Source Server         : MySql5.5
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : schoolproject_db

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-06-09 14:57:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `course_Key` int(4) NOT NULL AUTO_INCREMENT,
  `course_Id` varchar(32) NOT NULL,
  `course_Name` varchar(32) NOT NULL,
  PRIMARY KEY (`course_Key`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '1000001', '数据库');
INSERT INTO `course` VALUES ('2', '1000002', '数学');
INSERT INTO `course` VALUES ('3', '1000003', '计算机组成原理');
INSERT INTO `course` VALUES ('4', '1000004', 'Java Web开发');
INSERT INTO `course` VALUES ('5', '1000005', '单片机');
INSERT INTO `course` VALUES ('6', '1000006', 'Android应用开发');
INSERT INTO `course` VALUES ('7', '1000007', '大学英语');

-- ----------------------------
-- Table structure for course_subject
-- ----------------------------
DROP TABLE IF EXISTS `course_subject`;
CREATE TABLE `course_subject` (
  `question_ID` int(4) NOT NULL AUTO_INCREMENT,
  `teacher_ID` varchar(32) NOT NULL,
  `newcourse_ID` varchar(64) NOT NULL,
  `zhang` int(4) NOT NULL,
  `jie` int(4) NOT NULL,
  `question_Type` int(4) NOT NULL,
  `question_Point` varchar(200) DEFAULT NULL,
  `question_Content` varchar(400) DEFAULT NULL,
  `A` varchar(200) DEFAULT NULL,
  `B` varchar(200) DEFAULT NULL,
  `C` varchar(200) DEFAULT NULL,
  `D` varchar(200) DEFAULT NULL,
  `question_Key` varchar(2) DEFAULT NULL,
  `release_ID` int(4) DEFAULT NULL,
  PRIMARY KEY (`question_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_subject
-- ----------------------------
INSERT INTO `course_subject` VALUES ('3', '001', '0011000001', '1', '1', '1', 'SQL语言', '数据库当中那一条不是SQL语句。', 'select', 'update', 'delete', 'DO', 'D', '0');

-- ----------------------------
-- Table structure for question_release
-- ----------------------------
DROP TABLE IF EXISTS `question_release`;
CREATE TABLE `question_release` (
  `release_ID` int(4) NOT NULL,
  `question_ID` int(4) NOT NULL,
  `start_Time` datetime NOT NULL,
  `people_Num` int(4) NOT NULL,
  PRIMARY KEY (`release_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_release
-- ----------------------------
INSERT INTO `question_release` VALUES ('91211288', '3', '2017-06-09 13:13:36', '1');
INSERT INTO `question_release` VALUES ('92996089', '3', '2017-05-20 14:41:17', '1');

-- ----------------------------
-- Table structure for school_class
-- ----------------------------
DROP TABLE IF EXISTS `school_class`;
CREATE TABLE `school_class` (
  `class_ID` int(4) NOT NULL AUTO_INCREMENT,
  `major_ID` int(4) NOT NULL,
  `class_Name` varchar(32) NOT NULL,
  PRIMARY KEY (`class_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school_class
-- ----------------------------
INSERT INTO `school_class` VALUES ('1', '1', '网工一班');
INSERT INTO `school_class` VALUES ('2', '1', '网工二班');
INSERT INTO `school_class` VALUES ('3', '2', '软工一班');
INSERT INTO `school_class` VALUES ('4', '2', '软工二班');
INSERT INTO `school_class` VALUES ('5', '3', '计科一班');
INSERT INTO `school_class` VALUES ('6', '3', '计科专升本');

-- ----------------------------
-- Table structure for school_level
-- ----------------------------
DROP TABLE IF EXISTS `school_level`;
CREATE TABLE `school_level` (
  `level_ID` int(4) NOT NULL AUTO_INCREMENT,
  `level_Name` varchar(32) NOT NULL,
  PRIMARY KEY (`level_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school_level
-- ----------------------------
INSERT INTO `school_level` VALUES ('1', '计算机科学与技术系');
INSERT INTO `school_level` VALUES ('2', '建筑系');
INSERT INTO `school_level` VALUES ('3', '材料工程系');
INSERT INTO `school_level` VALUES ('4', '教育系');
INSERT INTO `school_level` VALUES ('5', '中文系');
INSERT INTO `school_level` VALUES ('6', '电子机械工程系');
INSERT INTO `school_level` VALUES ('7', '数学系');
INSERT INTO `school_level` VALUES ('8', '化学工程系');
INSERT INTO `school_level` VALUES ('9', '物理系');
INSERT INTO `school_level` VALUES ('10', '艺术系');
INSERT INTO `school_level` VALUES ('11', '经济系');
INSERT INTO `school_level` VALUES ('12', '历史系');

-- ----------------------------
-- Table structure for school_major
-- ----------------------------
DROP TABLE IF EXISTS `school_major`;
CREATE TABLE `school_major` (
  `major_ID` int(4) NOT NULL AUTO_INCREMENT,
  `level_ID` int(4) NOT NULL,
  `major_Name` varchar(32) NOT NULL,
  PRIMARY KEY (`major_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school_major
-- ----------------------------
INSERT INTO `school_major` VALUES ('1', '1', '网工');
INSERT INTO `school_major` VALUES ('2', '1', '软工');
INSERT INTO `school_major` VALUES ('3', '1', '计算机科学与技术');
INSERT INTO `school_major` VALUES ('4', '1', '多媒体');
INSERT INTO `school_major` VALUES ('5', '2', '建筑材料');
INSERT INTO `school_major` VALUES ('6', '2', '建筑美化');
INSERT INTO `school_major` VALUES ('7', '2', '搬砖');
INSERT INTO `school_major` VALUES ('8', '3', '塑料管');
INSERT INTO `school_major` VALUES ('9', '3', '焊铁');

-- ----------------------------
-- Table structure for school_time
-- ----------------------------
DROP TABLE IF EXISTS `school_time`;
CREATE TABLE `school_time` (
  `time_ID` int(4) NOT NULL AUTO_INCREMENT,
  `time_Name` varchar(32) NOT NULL,
  PRIMARY KEY (`time_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school_time
-- ----------------------------
INSERT INTO `school_time` VALUES ('1', '12级');
INSERT INTO `school_time` VALUES ('2', '13级');
INSERT INTO `school_time` VALUES ('3', '14级');
INSERT INTO `school_time` VALUES ('4', '15级');
INSERT INTO `school_time` VALUES ('5', '16级');
INSERT INTO `school_time` VALUES ('6', '17级');
INSERT INTO `school_time` VALUES ('7', '18级');
INSERT INTO `school_time` VALUES ('8', '19级');
INSERT INTO `school_time` VALUES ('9', '20级');

-- ----------------------------
-- Table structure for select_course
-- ----------------------------
DROP TABLE IF EXISTS `select_course`;
CREATE TABLE `select_course` (
  `newcourse_ID` varchar(64) NOT NULL,
  `teacher_id` varchar(32) NOT NULL,
  `course_Id` varchar(32) NOT NULL,
  `course_Time` date DEFAULT NULL,
  `course_Type` int(4) NOT NULL,
  PRIMARY KEY (`newcourse_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of select_course
-- ----------------------------
INSERT INTO `select_course` VALUES ('0011000001', '001', '1000001', '2017-05-20', '1');

-- ----------------------------
-- Table structure for student_course_info
-- ----------------------------
DROP TABLE IF EXISTS `student_course_info`;
CREATE TABLE `student_course_info` (
  `newcourse_ID` varchar(32) NOT NULL,
  `student_ID` varchar(32) NOT NULL,
  `course_ID` int(4) NOT NULL,
  `teacher_ID` varchar(32) NOT NULL,
  `course_Time` date DEFAULT NULL,
  `course_Type` int(4) NOT NULL,
  PRIMARY KEY (`newcourse_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_course_info
-- ----------------------------
INSERT INTO `student_course_info` VALUES ('0011', '001', '1', '001', null, '1');

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `student_ID` varchar(32) NOT NULL,
  `student_Name` varchar(32) NOT NULL,
  `student_Sex` varchar(10) NOT NULL,
  `student_Level` varchar(32) NOT NULL,
  `student_Major` varchar(32) NOT NULL,
  `student_Class` varchar(32) NOT NULL,
  `student_Time` varchar(32) NOT NULL,
  `student_Tele` varchar(11) NOT NULL,
  `student_Email` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`student_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES ('001', '王世祝', '男', '计算机科学与技术系', '计算机科学与技术', '计科专升本', '15级', '18225514810', '');

-- ----------------------------
-- Table structure for student_record
-- ----------------------------
DROP TABLE IF EXISTS `student_record`;
CREATE TABLE `student_record` (
  `record_Id` varchar(64) NOT NULL,
  `student_Id` varchar(32) NOT NULL,
  `release_Id` int(4) NOT NULL,
  `answer` varchar(2) NOT NULL,
  PRIMARY KEY (`record_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_record
-- ----------------------------
INSERT INTO `student_record` VALUES ('91211288001', '001', '91211288', 'C');
INSERT INTO `student_record` VALUES ('92996089001', '001', '92996089', 'D');

-- ----------------------------
-- Table structure for student_reg
-- ----------------------------
DROP TABLE IF EXISTS `student_reg`;
CREATE TABLE `student_reg` (
  `student_ID` varchar(32) NOT NULL,
  `student_Pwd` varchar(32) NOT NULL,
  `student_Tele` varchar(11) NOT NULL,
  PRIMARY KEY (`student_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_reg
-- ----------------------------
INSERT INTO `student_reg` VALUES ('001', 'qwer', '18225514810');

-- ----------------------------
-- Table structure for teachar_reg
-- ----------------------------
DROP TABLE IF EXISTS `teachar_reg`;
CREATE TABLE `teachar_reg` (
  `teacher_id` varchar(32) NOT NULL,
  `user_pwd` varchar(32) NOT NULL,
  `user_tele` varchar(11) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teachar_reg
-- ----------------------------
INSERT INTO `teachar_reg` VALUES ('001', 'qwer', '18200000000');

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info` (
  `teacher_id` varchar(32) NOT NULL,
  `teacher_name` varchar(32) NOT NULL,
  `teacher_sex` varchar(10) NOT NULL,
  `teacher_time` date DEFAULT NULL,
  `teacher_tele` varchar(11) NOT NULL,
  `teacher_email` varchar(32) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_info
-- ----------------------------
INSERT INTO `teacher_info` VALUES ('001', 'wangsz', 'Male', '2017-05-09', '18000000000', '6666@qq.com');
