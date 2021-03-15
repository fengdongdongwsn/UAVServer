/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : uavserver

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 12/03/2021 11:07:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accessdata
-- ----------------------------
DROP TABLE IF EXISTS `accessdata`;
CREATE TABLE `accessdata`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accessname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accessobject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accesslevel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accessscene` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accessdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accesspath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accessdata
-- ----------------------------
INSERT INTO `accessdata` VALUES (1, 'adapt', '无人机机群', '环境适应', '城市搜索场景', '无人机机群在不同的环境当中得出的环境适应能力的数据', 'E:\\IDEAProject\\UavServer\\src\\main\\resources\\static\\sources\\adapt.txt');
INSERT INTO `accessdata` VALUES (2, 'robus', '无人机机群', '环境适应', '城市搜索场景', '无人机机群在城市环境下执行搜索任务，实现对集群鲁棒性特性的评估，本数据源是鲁棒性评估数据源', 'E:\\IDEAProject\\UavServer\\src\\main\\resources\\static\\sources\\robus.txt');
INSERT INTO `accessdata` VALUES (3, 'robus', '无人机机群', '环境适应', '城市搜索场景', '无人机机群在城市环境下执行搜索任务，实现对集群鲁棒性特性的评估，本数据源是鲁棒性评估数据源', 'E:\\IDEAProject\\UavServer\\src\\main\\resources\\static\\sources\\robus.txt');

-- ----------------------------
-- Table structure for accessenv
-- ----------------------------
DROP TABLE IF EXISTS `accessenv`;
CREATE TABLE `accessenv`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `envname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `envimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accessenv
-- ----------------------------
INSERT INTO `accessenv` VALUES (1, '城市环境', 'E:\\IDEAProject\\UavServer\\src\\main\\resources\\static\\env\\01.png');
INSERT INTO `accessenv` VALUES (2, 'test', 'E:\\IDEAProject\\UavServer\\out\\artifacts\\uavserver_jar\\src\\main\\resources\\static\\env\\微信图片_20201218095028.jpg');

-- ----------------------------
-- Table structure for accesslevel
-- ----------------------------
DROP TABLE IF EXISTS `accesslevel`;
CREATE TABLE `accesslevel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `levelaccess` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `levelname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `leveldesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accesslevel
-- ----------------------------
INSERT INTO `accesslevel` VALUES (1, '等级1', '信息交互', '群体在执行任务过程中能够通过相互通信进行信息交流');
INSERT INTO `accesslevel` VALUES (2, '等级2', '能力聚合', '群体在执行任务过程中能够通过个体任务执行能力的整合实现整个群体任务执行能力的提升');
INSERT INTO `accesslevel` VALUES (3, '等级3', '环境适应', '群体在执行任务过程中能够不断的调整自身的行为以适应环境变化');
INSERT INTO `accesslevel` VALUES (4, '等级4', '合作博弈', '群体能够在对抗性的环境中通过个体之间的协同实现生存并与其他群体进行竞争');
INSERT INTO `accesslevel` VALUES (5, '等级5', '协同认知', '群体在执行任务的过程中沟通过协同学习，积累经验或者是知识，获取整体认知能力的提升');
INSERT INTO `accesslevel` VALUES (6, '等级6', '智能涌现', '群体在执行任务的过程中具备主动发现非系统既定新规则、新知识的能力');

-- ----------------------------
-- Table structure for accessmethod
-- ----------------------------
DROP TABLE IF EXISTS `accessmethod`;
CREATE TABLE `accessmethod`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `methodname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `methodscene` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `methoddesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accessmethod
-- ----------------------------
INSERT INTO `accessmethod` VALUES (1, 'topsis', '适用于环境适应性相关场景', '多指标评估方式，可以把所有指标数据全部计算在内，没有数量限制。是一种典型的定量评估方法');
INSERT INTO `accessmethod` VALUES (2, 'entropy', '适用于计算复杂度相关场景', '多指标评估方式，主要是计算数据的聚合程度，是一种典型的定量评估方法');

-- ----------------------------
-- Table structure for accessobject
-- ----------------------------
DROP TABLE IF EXISTS `accessobject`;
CREATE TABLE `accessobject`  (
  `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `accessname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accessscale` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `accessdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `imgpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accessobject
-- ----------------------------
INSERT INTO `accessobject` VALUES (1, '无人机机群', '3架', '多架大疆无人机组成的无人机编队', 'E:\\IDEAProject\\UavServer\\src\\main\\resources\\static\\objectimg\\无人机机群');

-- ----------------------------
-- Table structure for accessscene
-- ----------------------------
DROP TABLE IF EXISTS `accessscene`;
CREATE TABLE `accessscene`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `scenename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `taskname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `taskdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `envname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `envimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accessscene
-- ----------------------------
INSERT INTO `accessscene` VALUES (1, '城市搜索场景', '搜索任务', '无人机机群执行搜索任务', '城市环境', 'E:\\IDEAProject\\UavServer\\src\\main\\resources\\static\\scenes\\城市搜索场景');
INSERT INTO `accessscene` VALUES (2, '校园覆盖场景', '覆盖任务', '无人机机群执行覆盖任务', '校园环境', 'E:\\IDEAProject\\UavServer\\src\\main\\resources\\static\\scenes\\校园覆盖场景');

-- ----------------------------
-- Table structure for accesstask
-- ----------------------------
DROP TABLE IF EXISTS `accesstask`;
CREATE TABLE `accesstask`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `taskname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `taskdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accesstask
-- ----------------------------
INSERT INTO `accesstask` VALUES (1, '搜索任务', '无人机机群执行搜索任务');
INSERT INTO `accesstask` VALUES (2, '覆盖任务', '无人机机群执行覆盖任务');

SET FOREIGN_KEY_CHECKS = 1;
