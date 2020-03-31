/*
 Navicat Premium Data Transfer

 Source Server         : yjy
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : info

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 31/03/2020 20:36:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cfile
-- ----------------------------
DROP TABLE IF EXISTS `cfile`;
CREATE TABLE `cfile`  (
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fileid` int(10) NULL DEFAULT NULL,
  `time` timestamp(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cfile
-- ----------------------------
INSERT INTO `cfile` VALUES ('20180002', 8, '2019-04-23 23:54:55');
INSERT INTO `cfile` VALUES ('20180002', 12, '2019-04-29 09:42:24');
INSERT INTO `cfile` VALUES ('20180002', 27, '2019-05-02 22:52:52');
INSERT INTO `cfile` VALUES ('20180002', 33, '2019-05-05 20:31:55');
INSERT INTO `cfile` VALUES ('20180002', 78, '2019-05-06 10:05:41');
INSERT INTO `cfile` VALUES ('20180002', 103, '2019-05-10 19:12:05');
INSERT INTO `cfile` VALUES ('20180002', 104, '2019-05-10 19:12:29');
INSERT INTO `cfile` VALUES ('20180002', 105, '2019-05-10 20:44:01');
INSERT INTO `cfile` VALUES ('20180004', 109, '2019-07-22 22:24:33');
INSERT INTO `cfile` VALUES ('20180004', 110, '2019-07-22 22:24:55');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(10) NULL DEFAULT NULL,
  `class` int(10) NULL DEFAULT NULL,
  `avatar` int(10) NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('20180002', 2018, 2, 7, '无');
INSERT INTO `class` VALUES ('20180001', 2018, 1, 6, '交流群');
INSERT INTO `class` VALUES ('20180003', 2018, 3, 8, '我是一个新班级');
INSERT INTO `class` VALUES ('20180004', 2018, 4, -1, '我是一个新班级');
INSERT INTO `class` VALUES ('20180005', 2018, 5, -1, '我是一个新班级');
INSERT INTO `class` VALUES ('20170002', 2017, 2, -1, '我是一个新班级');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `fileid` int(9) UNSIGNED NOT NULL AUTO_INCREMENT,
  `owner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` timestamp(0) NULL DEFAULT NULL,
  `size` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`fileid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES (1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `file` VALUES (2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `file` VALUES (3, '2222222222', '-1.png', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (4, '2222222222', '-1.png', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (5, '222222222222', '-1.png', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (6, '22', '-1.png', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (7, '22', '-1df079fe23359fb5.jpg', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (8, '22', 'hwh.cpp', 'E:\\yjykeshe', '2019-05-02 16:54:26', 1024);
INSERT INTO `file` VALUES (9, '11', '0(OPN8UP}RL50B6TT7BHY2W.png', 'E:\\yjykeshe\\2019\\4', NULL, NULL);
INSERT INTO `file` VALUES (10, '11', '1.gif', 'E:\\yjykeshe\\2019\\4', NULL, NULL);
INSERT INTO `file` VALUES (11, '11', '51.gif', 'E:\\yjykeshe\\2019\\4', NULL, NULL);
INSERT INTO `file` VALUES (12, '22', 'D.exe', 'E:\\yjykeshe', '2019-04-28 16:54:35', 1088512);
INSERT INTO `file` VALUES (13, '11', '50.gif', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (14, '22', '0.gif', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (15, '11', '51.gif', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (16, '11', '61.gif', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (17, '11', '72.gif', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (18, '11', '98.gif', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (19, '11', '62.gif', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (20, '11', '20194030184045.jpg', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (21, '11', '20194130184101.jpg', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (22, '11', '20194530184542.jpg', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (23, '11', '42.gif', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (24, '11', '79.gif', 'E:\\yjykeshe', NULL, NULL);
INSERT INTO `file` VALUES (25, '11', '安装测试.png', 'E:\\yjykeshe', '2019-05-02 22:50:46', 68541);
INSERT INTO `file` VALUES (26, '11', 'Git学习笔记.txt', 'E:\\yjykeshe', '2019-05-02 22:51:38', 1543);
INSERT INTO `file` VALUES (27, '11', 'main.cpp', 'E:\\yjykeshe', '2019-05-02 22:52:51', 872);
INSERT INTO `file` VALUES (28, '11', '13.gif', 'E:\\yjykeshe', '2019-05-03 15:00:39', 1736);
INSERT INTO `file` VALUES (29, '22', '15f07142eb11b871.gif', 'E:\\yjykeshe', '2019-05-03 15:02:12', 157673);
INSERT INTO `file` VALUES (30, '22', '78.gif', 'E:\\yjykeshe', '2019-05-04 21:45:45', 1565);
INSERT INTO `file` VALUES (31, '22', '79.gif', 'E:\\yjykeshe', '2019-05-04 22:11:10', 1518);
INSERT INTO `file` VALUES (32, '22', '13.gif', 'E:\\yjykeshe', '2019-05-04 22:20:54', 1736);
INSERT INTO `file` VALUES (33, '11', 'C.cpp', 'E:\\yjykeshe', '2019-05-05 20:31:53', 537);
INSERT INTO `file` VALUES (34, '11', '20193205203227.jpg', 'E:\\yjykeshe', '2019-05-05 20:32:30', 34362);
INSERT INTO `file` VALUES (35, '11', '0.gif', 'E:\\yjykeshe', '2019-05-05 20:32:49', 1810);
INSERT INTO `file` VALUES (36, '22', '70.gif', 'E:\\yjykeshe', '2019-05-05 22:31:18', 1162);
INSERT INTO `file` VALUES (37, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 22:31:26', 1518);
INSERT INTO `file` VALUES (38, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 22:33:08', 1518);
INSERT INTO `file` VALUES (39, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 22:33:27', 1518);
INSERT INTO `file` VALUES (40, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 22:34:39', 1518);
INSERT INTO `file` VALUES (41, '11', '80.gif', 'E:\\yjykeshe', '2019-05-05 22:34:45', 1537);
INSERT INTO `file` VALUES (42, '22', '70.gif', 'E:\\yjykeshe', '2019-05-05 22:35:39', 1162);
INSERT INTO `file` VALUES (43, '11', '80.gif', 'E:\\yjykeshe', '2019-05-05 22:35:57', 1537);
INSERT INTO `file` VALUES (44, '22', '78.gif', 'E:\\yjykeshe', '2019-05-05 22:36:30', 1565);
INSERT INTO `file` VALUES (45, '22', '0.gif', 'E:\\yjykeshe', '2019-05-05 22:37:10', 1810);
INSERT INTO `file` VALUES (46, '11', '71.gif', 'E:\\yjykeshe', '2019-05-05 22:40:29', 824);
INSERT INTO `file` VALUES (47, '22', '70.gif', 'E:\\yjykeshe', '2019-05-05 22:40:48', 1162);
INSERT INTO `file` VALUES (48, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 22:42:05', 1518);
INSERT INTO `file` VALUES (49, '11', '70.gif', 'E:\\yjykeshe', '2019-05-05 22:44:05', 1162);
INSERT INTO `file` VALUES (50, '11', '61.gif', 'E:\\yjykeshe', '2019-05-05 22:44:50', 1136);
INSERT INTO `file` VALUES (51, '11', '87.gif', 'E:\\yjykeshe', '2019-05-05 22:49:56', 1558);
INSERT INTO `file` VALUES (52, '11', '69.gif', 'E:\\yjykeshe', '2019-05-05 22:51:11', 1015);
INSERT INTO `file` VALUES (53, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 22:53:04', 1518);
INSERT INTO `file` VALUES (54, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 22:53:34', 1518);
INSERT INTO `file` VALUES (55, '22', '71.gif', 'E:\\yjykeshe', '2019-05-05 22:54:07', 824);
INSERT INTO `file` VALUES (56, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 22:54:59', 1518);
INSERT INTO `file` VALUES (57, '22', '78.gif', 'E:\\yjykeshe', '2019-05-05 22:55:06', 1565);
INSERT INTO `file` VALUES (58, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:02:15', 1518);
INSERT INTO `file` VALUES (59, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:02:21', 1518);
INSERT INTO `file` VALUES (60, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:02:32', 1518);
INSERT INTO `file` VALUES (61, '22', '61.gif', 'E:\\yjykeshe', '2019-05-05 23:03:19', 1136);
INSERT INTO `file` VALUES (62, '22', '70.gif', 'E:\\yjykeshe', '2019-05-05 23:03:37', 1162);
INSERT INTO `file` VALUES (63, '22', '80.gif', 'E:\\yjykeshe', '2019-05-05 23:04:21', 1537);
INSERT INTO `file` VALUES (64, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:05:29', 1518);
INSERT INTO `file` VALUES (65, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:07:02', 1518);
INSERT INTO `file` VALUES (66, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:07:32', 1518);
INSERT INTO `file` VALUES (67, '22', '78.gif', 'E:\\yjykeshe', '2019-05-05 23:17:02', 1565);
INSERT INTO `file` VALUES (68, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:17:08', 1518);
INSERT INTO `file` VALUES (69, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:17:16', 1518);
INSERT INTO `file` VALUES (70, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:18:20', 1518);
INSERT INTO `file` VALUES (71, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:18:54', 1518);
INSERT INTO `file` VALUES (72, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:21:05', 1518);
INSERT INTO `file` VALUES (73, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:23:11', 1518);
INSERT INTO `file` VALUES (74, '22', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:23:20', 1518);
INSERT INTO `file` VALUES (75, '11', '80.gif', 'E:\\yjykeshe', '2019-05-05 23:23:27', 1537);
INSERT INTO `file` VALUES (76, '11', '79.gif', 'E:\\yjykeshe', '2019-05-05 23:23:59', 1518);
INSERT INTO `file` VALUES (77, '22', '70.gif', 'E:\\yjykeshe', '2019-05-06 09:08:01', 1162);
INSERT INTO `file` VALUES (78, '11', '111.png', 'E:\\yjykeshe', '2019-05-06 10:05:40', 694);
INSERT INTO `file` VALUES (79, '22', '20191506101500.jpg', 'E:\\yjykeshe', '2019-05-06 10:15:02', 9536);
INSERT INTO `file` VALUES (80, '22', '70.gif', 'E:\\yjykeshe', '2019-05-06 10:16:04', 1162);
INSERT INTO `file` VALUES (81, '11', '79.gif', 'E:\\yjykeshe', '2019-05-06 10:16:36', 1518);
INSERT INTO `file` VALUES (82, '11', '20192206102208.jpg', 'E:\\yjykeshe', '2019-05-06 10:22:10', 12406);
INSERT INTO `file` VALUES (83, '11', '20192706102753.jpg', 'E:\\yjykeshe', '2019-05-06 10:27:59', 73704);
INSERT INTO `file` VALUES (84, '11', '20191306111325.jpg', 'E:\\yjykeshe', '2019-05-06 11:13:27', 8344);
INSERT INTO `file` VALUES (85, '11', '20191606111604.jpg', 'E:\\yjykeshe', '2019-05-06 11:16:07', 9045);
INSERT INTO `file` VALUES (86, '11', '20191606111615.jpg', 'E:\\yjykeshe', '2019-05-06 11:16:17', 15375);
INSERT INTO `file` VALUES (87, '11', '20191706111700.jpg', 'E:\\yjykeshe', '2019-05-06 11:17:03', 18303);
INSERT INTO `file` VALUES (88, '11', '20191806111809.jpg', 'E:\\yjykeshe', '2019-05-06 11:18:11', 13740);
INSERT INTO `file` VALUES (89, '11', '51.gif', 'E:\\yjykeshe', '2019-05-06 11:21:11', 3731);
INSERT INTO `file` VALUES (90, '11', '71.gif', 'E:\\yjykeshe', '2019-05-06 11:22:29', 824);
INSERT INTO `file` VALUES (91, '22', '70.gif', 'E:\\yjykeshe', '2019-05-06 11:22:58', 1162);
INSERT INTO `file` VALUES (92, '11', '71.gif', 'E:\\yjykeshe', '2019-05-06 11:23:05', 824);
INSERT INTO `file` VALUES (93, '11', '70.gif', 'E:\\yjykeshe', '2019-05-06 11:23:10', 1162);
INSERT INTO `file` VALUES (94, '11', '20194306114347.jpg', 'E:\\yjykeshe', '2019-05-06 11:43:54', 6995);
INSERT INTO `file` VALUES (95, '11', '20192206202246.jpg', 'E:\\yjykeshe', '2019-05-06 20:22:59', 11848);
INSERT INTO `file` VALUES (96, '22', '61.gif', 'E:\\yjykeshe', '2019-05-06 20:32:53', 1136);
INSERT INTO `file` VALUES (97, '11', '70.gif', 'E:\\yjykeshe', '2019-05-06 20:33:05', 1162);
INSERT INTO `file` VALUES (98, '22', '79.gif', 'E:\\yjykeshe', '2019-05-06 20:33:15', 1518);
INSERT INTO `file` VALUES (99, '11', '62.gif', 'E:\\yjykeshe', '2019-05-09 17:36:02', 1269);
INSERT INTO `file` VALUES (103, '11', '捕获.PNG', 'E:\\yjykeshe', '2019-05-10 19:12:04', 362387);
INSERT INTO `file` VALUES (104, '11', 'T2.cpp', 'E:\\yjykeshe', '2019-05-10 19:12:28', 725);
INSERT INTO `file` VALUES (105, '11', '1.png', 'E:\\yjykeshe', '2019-05-10 20:43:59', 1460648);
INSERT INTO `file` VALUES (106, '22', '20194510204505.jpg', 'E:\\yjykeshe', '2019-05-10 20:45:07', 14796);
INSERT INTO `file` VALUES (107, '11', '51.gif', 'E:\\yjykeshe', '2019-07-22 22:17:10', 3731);
INSERT INTO `file` VALUES (108, '22', '20191722221728.jpg', 'E:\\yjykeshe', '2019-07-22 22:17:29', 29503);
INSERT INTO `file` VALUES (109, '22', '未命名7.cpp', 'E:\\yjykeshe', '2019-07-22 22:24:28', 1994);
INSERT INTO `file` VALUES (110, '22', '1.png', 'E:\\yjykeshe', '2019-07-22 22:24:53', 1460648);
INSERT INTO `file` VALUES (111, '22', '70.gif', 'E:\\yjykeshe', '2019-10-28 23:16:34', 1162);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` timestamp(0) NULL DEFAULT NULL,
  `type` int(10) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('20180002', '22', '2019-05-10 20:48:59', 0);
INSERT INTO `member` VALUES ('20180002', '1111', '2019-04-23 23:23:51', 2);
INSERT INTO `member` VALUES ('20180002', '11', '2019-05-10 20:49:38', 2);
INSERT INTO `member` VALUES ('20180001', '22', '2019-05-10 20:46:41', 0);
INSERT INTO `member` VALUES ('20180001', '11', '2019-05-10 20:46:41', 0);
INSERT INTO `member` VALUES ('20180003', '11', '2019-05-10 20:05:05', 0);
INSERT INTO `member` VALUES ('20180003', '22', '2019-05-10 19:53:08', 2);
INSERT INTO `member` VALUES ('20180004', '11', '2019-07-22 22:26:41', 0);
INSERT INTO `member` VALUES ('20180004', '22', '2019-07-22 22:23:13', 2);
INSERT INTO `member` VALUES ('20180005', '11', '2019-10-28 23:17:56', 0);
INSERT INTO `member` VALUES ('20170002', '11', '2019-11-08 21:29:21', 0);
INSERT INTO `member` VALUES ('20170002', '22', '2019-11-08 21:29:21', 2);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `sender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `msgid` int(9) NOT NULL AUTO_INCREMENT,
  `type` int(9) NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`msgid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('11', '20180002', 14, 1, 'QWQ', '2019-05-05 10:44:20');
INSERT INTO `message` VALUES ('11', '20180002', 15, 1, '<34>', '2019-05-05 20:32:30');
INSERT INTO `message` VALUES ('11', '20180002', 16, 1, '$发送了一个窗口抖动。', '2019-05-05 20:32:37');
INSERT INTO `message` VALUES ('11', '20180002', 17, 1, '<35>', '2019-05-05 20:32:49');
INSERT INTO `message` VALUES ('22', '20180002', 20, 1, '<45>', '2019-05-05 22:37:10');
INSERT INTO `message` VALUES ('22', '20180002', 21, 1, '嘻嘻', '2019-05-05 22:39:38');
INSERT INTO `message` VALUES ('11', '20180002', 22, 1, '<46>', '2019-05-05 22:40:29');
INSERT INTO `message` VALUES ('22', '20180002', 23, 1, '<47>', '2019-05-05 22:40:49');
INSERT INTO `message` VALUES ('22', '20180002', 24, 1, '<48>', '2019-05-05 22:42:05');
INSERT INTO `message` VALUES ('11', '20180002', 25, 1, 'skr', '2019-05-05 22:44:20');
INSERT INTO `message` VALUES ('11', '20180002', 26, 1, '<50>', '2019-05-05 22:44:50');
INSERT INTO `message` VALUES ('11', '20180002', 27, 1, '<51>', '2019-05-05 22:49:57');
INSERT INTO `message` VALUES ('11', '20180002', 28, 1, '<52>', '2019-05-05 22:51:11');
INSERT INTO `message` VALUES ('22', '20180002', 29, 1, '<53>', '2019-05-05 22:53:04');
INSERT INTO `message` VALUES ('11', '20180002', 32, 1, '<59>', '2019-05-05 23:02:21');
INSERT INTO `message` VALUES ('11', '20180002', 33, 1, '<64>', '2019-05-05 23:05:30');
INSERT INTO `message` VALUES ('11', '20180002', 34, 1, '', '2019-05-05 23:05:30');
INSERT INTO `message` VALUES ('11', '20180003', 37, 1, '大家好，我是创建了新班级。', '2019-05-06 00:12:35');
INSERT INTO `message` VALUES ('11', '20180002', 48, 1, '<84>', '2019-05-06 11:13:27');
INSERT INTO `message` VALUES ('11', '20180002', 49, 1, '<85>', '2019-05-06 11:16:07');
INSERT INTO `message` VALUES ('11', '20180002', 50, 1, '<86>', '2019-05-06 11:16:17');
INSERT INTO `message` VALUES ('11', '20180002', 51, 1, '<87>', '2019-05-06 11:17:03');
INSERT INTO `message` VALUES ('11', '20180002', 52, 1, '<88>', '2019-05-06 11:18:11');
INSERT INTO `message` VALUES ('11', '20180002', 53, 1, '的', '2019-05-06 11:20:44');
INSERT INTO `message` VALUES ('11', '20180002', 54, 1, '存储', '2019-05-06 11:20:51');
INSERT INTO `message` VALUES ('11', '20180002', 55, 1, '\n<89>', '2019-05-06 11:21:11');
INSERT INTO `message` VALUES ('11', '20180002', 56, 1, '<90>', '2019-05-06 11:22:29');
INSERT INTO `message` VALUES ('22', '20180002', 57, 1, '<91>', '2019-05-06 11:22:58');
INSERT INTO `message` VALUES ('11', '20180002', 58, 1, '<92>', '2019-05-06 11:23:05');
INSERT INTO `message` VALUES ('11', '20180002', 59, 1, '<93>', '2019-05-06 11:23:10');
INSERT INTO `message` VALUES ('11', '20180002', 60, 1, '<94>', '2019-05-06 11:43:54');
INSERT INTO `message` VALUES ('11', '20180002', 61, 1, '<95>', '2019-05-06 20:22:59');
INSERT INTO `message` VALUES ('11', '20180001', 62, 1, '<97>', '2019-05-06 20:33:05');
INSERT INTO `message` VALUES ('22', '20180002', 63, 1, '<98>', '2019-05-06 20:33:15');
INSERT INTO `message` VALUES ('22', '20180003', 65, 1, '大家好，我是新加入的成员。', '2019-05-07 23:11:36');
INSERT INTO `message` VALUES ('11', '20180002', 66, 1, '<99>', '2019-05-09 17:36:03');
INSERT INTO `message` VALUES ('11', '20180001', 67, 3, '有新公告！', '2019-05-09 21:45:19');
INSERT INTO `message` VALUES ('11', '20180003', 68, 4, '有新投票等待审议哦！', '2019-05-10 19:25:29');
INSERT INTO `message` VALUES ('11', '20180003', 69, 5, '有新投票哦。', '2019-05-10 19:25:58');
INSERT INTO `message` VALUES ('11', '20180002', 70, 1, '$发送了一个窗口抖动。', '2019-05-10 19:26:22');
INSERT INTO `message` VALUES ('22', '20180002', 71, 3, '有新公告！', '2019-05-10 20:44:31');
INSERT INTO `message` VALUES ('22', '20180001', 72, 3, '有新公告！', '2019-05-10 20:44:36');
INSERT INTO `message` VALUES ('22', '20180002', 73, 1, '$发送了一个窗口抖动。', '2019-05-10 20:45:00');
INSERT INTO `message` VALUES ('22', '20180002', 74, 1, '<106>', '2019-05-10 20:45:07');
INSERT INTO `message` VALUES ('11', '20180001', 75, 4, '有新投票等待审议哦！', '2019-05-10 20:45:28');
INSERT INTO `message` VALUES ('11', '20180001', 76, 5, '有新投票哦。', '2019-05-10 20:46:41');
INSERT INTO `message` VALUES ('22', '20180002', 77, 3, '有新公告！', '2019-05-10 20:48:59');
INSERT INTO `message` VALUES ('11', '20180004', 78, 1, '大家好，我创建了新班级。', '2019-07-22 22:22:24');
INSERT INTO `message` VALUES ('22', '20180004', 79, 1, '大家好，我是新加入的成员。', '2019-07-22 22:23:14');
INSERT INTO `message` VALUES ('11', '20180004', 80, 3, '有新公告！', '2019-07-22 22:23:36');
INSERT INTO `message` VALUES ('11', '20180004', 81, 4, '有新投票等待审议哦！', '2019-07-22 22:23:49');
INSERT INTO `message` VALUES ('11', '20180004', 82, 5, '有新投票哦。', '2019-07-22 22:24:09');
INSERT INTO `message` VALUES ('11', '20180005', 84, 1, '大家好，我创建了新班级。', '2019-10-28 23:17:49');
INSERT INTO `message` VALUES ('11', '20170002', 85, 1, '大家好，我创建了新班级。', '2019-11-08 21:26:10');
INSERT INTO `message` VALUES ('11', '20170002', 86, 1, '$发送了一个窗口抖动。', '2019-11-08 21:28:16');
INSERT INTO `message` VALUES ('22', '20170002', 87, 1, '大家好，我是新加入的成员。', '2019-11-08 21:29:06');
INSERT INTO `message` VALUES ('22', '20170002', 88, 1, '$发送了一个窗口抖动。', '2019-11-08 21:29:10');
INSERT INTO `message` VALUES ('22', '20170002', 89, 1, '$发送了一个窗口抖动。', '2019-11-08 21:29:20');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `noticeid` int(10) NOT NULL AUTO_INCREMENT,
  `sender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `text` varchar(2550) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` timestamp(0) NULL DEFAULT NULL,
  `status` int(255) NULL DEFAULT NULL,
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`noticeid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (4, '22', '投个票吧\r\n', '2019-04-23 23:51:21', NULL, '-1', '');
INSERT INTO `notice` VALUES (7, '22', 'JEditorPane继承JTextComponent类，因此它也可以使用JTextComponent抽象类里面的方法。JEditorPane的最主要功能在于展 现不同类型的文件格式内容。JEditorPane支持的文件类型有三种:第一种是纯文本类型，其类型的表示法为\"text/plain\",这种类型 的文件就是我们最常使用的txt文件，这类型的文件可以用记事本或WordPad等文书编辑软件来编辑。第二种是RTF类型，其表示法 为\"text/rtf\",这种类型的文件特色是能对文字内容做字体缩放、变形、上色', '2019-04-28 17:03:30', NULL, '20180002', 'JEditorPane讲解');
INSERT INTO `notice` VALUES (9, '11', '性能测试（Performance Test）\r\n通过测试工具和测试手段，监测和收集测试过程中的软件系统运行数据，度量系统与预定义目标的差距。而预定义目标就是通过性能需求来表示。\r\n\r\n怎样才能更有效的获得性能需求？以便更好设计、执行性能测试。可以按以下步骤：\r\n\r\n1. 收集，根据项目历史数据，或者根据经验\r\n\r\n2. 分析，比如业务人员很多，底层到中层、再到高层。\r\n\r\n分析历史数据、竞品、业务。业务需要分析业务常见、业务高峰（大的时间和小的时间段）\r\n\r\n性能问题还存在可以细分一下是场景遗漏、还是数据遗漏。场景遗漏常常由于需求传递变味导致。\r\n\r\n处理方法。 做好策略和设计，如果针对现在的问题：可以做一个checklist不断优化你的策略设计能力。\r\n\r\n关注点：how much和how fast\r\n\r\n测试阶段\r\n有些同事在测试几轮之后，功能稳定了开始介入性能测试，这时才发现性能根本支撑不了预期值。这个时候就再回头进行系统调优，如果事先选的架构能支撑就好，如果不能达到预期值，后面讨论或者请教高手发现原先的架构缺陷，再调整架构代价就非常大。基本导致前期的功能测试成果作废。其实各个阶段都有事情做：\r\n\r\n需求阶段可以整理，评审出性能需求，评审需求可行性时就要考虑好数据量和用户量。\r\n\r\n设计阶段--对预估的需求做设计，举个例子，背景：我们现在使用的是mysql数据库（公司去oracle化），我们要从一个5000W的一个数据表的6个不同查询维度查询数据，比如说城市、行业、地址类型、爱好、性别、时间范围。这样对于mysql的查询常见的优化设计可能是分表、建立索引，但对于这个场景就不好处理了，数据耦合强，没有办法分表，索引，组合索引太多。后面的处理办法是用mongodb、nosql的方法解决。对于编码和测试阶段可以这样去分不同阶段做不同事情：\r\n\r\n\r\n\r\n \r\n\r\n编码阶段，可以提出需要，让研发通过单元测试（开多线程）的方式进行压力测试。进行一些单元压力测试阶段也有策略的，建议先做一下单一场景单一用户的性能测试。常常会遇到有些同事在没有压单个场景的情况下，就进行负载测试，到处定位瓶颈，最后发现单一用户单一场景都是问题。这就是绕了一圈回到了起点。\r\n\r\n测试阶段，性能测试的频率根据业务场景需要就测、评估需求的时候，发现有性能需求就计划去做，但建议主要功能每隔3个小版本，关注一下业务量，业务量快达到预估值时进行一次，另外要考虑业务高峰期，比如双11、双12、618、春节等，建议之前都做一次。当然不同行业有不同的高峰期。\r\n\r\n对于测试阶段的不同测试类别，不细说，这里就说一下什么是基准测试和配置测试：\r\n\r\n基准测试：是一种可再现性的测试（对于软件产品来来说，同样的数据量标准、硬件标准下性能指标相对确定；对于硬件产品来说，基准测试标准更是固定而明确的），一般首次测试，会把单用户（如果是双节点负载均衡就要双用户）单场景作为基准测试，而对于多轮测试来说，上一轮测试的稳定基准配置，定为新的基准测试标准，因为基准测试的关键是要获得一致的、可再现的结果。那么什么时候要做基准测试呢，当软件增加了新模块或有大的迭代更新，当硬件环境做了优化提升时。\r\n\r\n配置测试：配置测试方法是指通过对被测系统软硬件环境的调整，了解各种不同环境对系统性能影响的程度，从而找到系统各项资源的最优分配原则。配置测试主要是针对硬件而言，其测试过程是测试目标软件在具体硬件配置情况下，出不出现问题，为的是发现硬件配置可能出现的问题，可能需要做的调优。\r\n\r\n基准测试是相对固定的方法，配置测试是验证性测试的目的。\r\n\r\n关注点：When and where', '2019-05-02 23:22:02', NULL, '20180001', '性能测试');
INSERT INTO `notice` VALUES (10, '11', '在工作中多使用docker、kubernetes等开源工具。工作中基本都是基于Linux系统进行操作的。记录一下工作中常用到的Linux命令，每个命令搭配一定的参数使用会更加方便。这里只记录常用到的命令以及参数\r\n\r\n1. 系统工作命令\r\n1. echo命令\r\necho命令用于在终端输出字符串或者变量提取后的值。格式为echo [字符串 | $变量]\r\n\r\n类似于java中的system.out.println\r\n[root@k8s-master ~]# echo \"hello world\"\r\nhello world\r\n[root@k8s-master ~]# str=\"hello world\"\r\n[root@k8s-master ~]# echo $str\r\nhello world\r\n1\r\n2\r\n3\r\n4\r\n5\r\n2. date命令\r\n用于显示及设置系统的时间，格式为 data [选项] [+指定格式]\r\n\r\ndata常见的参数\r\n\r\n%t 跳格[tab 键]\r\n%H 小时（0~23）\r\n%I 小时（0~12）\r\n%M 分钟（0~59）\r\n%S 秒（0~59）\r\n%J 一年中的第几天\r\n[root@k8s-master ~]# date\r\nThu Apr 11 13:42:20 CST 2019\r\n[root@k8s-master ~]# date \"+%Y-%m-%d %H:%M:%S\"\r\n2019-04-11 13:43:41\r\n[root@k8s-master ~]# date \"+%j\"\r\n101\r\n1\r\n2\r\n3\r\n4\r\n5\r\n6\r\n3. reboot重启命令\r\nreboot 命令用于重启系统，其格式为reboot。\r\n\r\n[root@k8s-master ~]# reboot\r\n1\r\n4. poweroff命令\r\npoweroff 命令用于关闭系统，其格式为 poweroff\r\n\r\n[root@k8s-master ~]# poweroff\r\n1\r\n5. wget命令\r\nwget命令在终端上下载网络文件，格式为 wget [参数] [下载地址]\r\n参数介绍\r\n\r\n-b 后台下载模式\r\n-P 下载到指定的目录\r\n-t 最大尝试次数\r\n-c 断点续传\r\n-p 下载页面内所有资源\r\n-r 递归下载\r\n[root@k8s-master ~]# wget http://www.linuxprobe.com/docs/LinuxProbe.pdf\r\n1\r\n6. ps命令\r\nps命令用于查看系统中的进程状态，格式为[ps 参数]\r\n参数介绍\r\n\r\n-a 显示所有进程\r\n-u 显示用户以及其他详细信息\r\n-x 显示没有控制终端的进程\r\n[root@k8s-master ~]# ps aux\r\n1\r\n7. top命令\r\ntop命令是用于动态监测进程情况和系统负载等详细信息。top命令很强大，相当于linux中“强化版的windows的任务管理器”\r\n\r\ntop - 10:41:34 up 12 days, 19:14,  2 users,  load average: 1.00, 1.06, 0.92\r\nTasks: 635 total,   2 running, 633 sleeping,   0 stopped,   0 zombie\r\n%Cpu(s):  4.6 us,  1.2 sy,  0.0 ni, 92.1 id,  1.7 wa,  0.0 hi,  0.1 si,  0.3 st\r\nKiB Mem : 16268340 total,  7407372 free,  4552160 used,  4308808 buff/cache\r\nKiB Swap:        0 total,        0 free,        0 used. 10456728 avail Mem \r\n\r\n  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND                                                                                                                \r\n32056 root      20   0  868460 790968  42808 S  25.2  4.9   1915:18 kube-apiserver  \r\n1\r\n2\r\n3\r\n4\r\n5\r\n6\r\n7\r\n8\r\ntop命令前五行是系统整体统计信息。分别代表系统负载、进程情况、cpu、物理内存、虚拟内存使用情况。\r\n', '2019-05-02 23:36:46', NULL, '20180001', '常用Linux命令');
INSERT INTO `notice` VALUES (11, '11', '这是一个投票', '2019-05-03 11:32:08', NULL, '-1', NULL);
INSERT INTO `notice` VALUES (12, '22', '有人想周六去网吧写课设吗？', '2019-05-03 13:14:34', NULL, '20180002', '网吧写课设');
INSERT INTO `notice` VALUES (13, '11', '周六去网吧写课设吗？', '2019-05-03 13:33:28', NULL, '-1', NULL);
INSERT INTO `notice` VALUES (14, '11', '周末去网吧打篮球吗？', '2019-05-03 13:42:52', NULL, '-1', NULL);
INSERT INTO `notice` VALUES (15, '11', '', '2019-05-03 14:02:14', NULL, '-1', NULL);
INSERT INTO `notice` VALUES (16, '11', '周六去网吧写课设吗？', '2019-05-03 14:15:37', NULL, '-1', NULL);
INSERT INTO `notice` VALUES (17, '11', 'xxs', '2019-05-09 21:45:17', NULL, '20180001', NULL);
INSERT INTO `notice` VALUES (18, '11', 'qwq', '2019-05-10 19:25:28', NULL, '-1', NULL);
INSERT INTO `notice` VALUES (19, '22', '', '2019-05-10 20:44:30', NULL, '20180002', NULL);
INSERT INTO `notice` VALUES (20, '22', '', '2019-05-10 20:44:35', NULL, '20180001', NULL);
INSERT INTO `notice` VALUES (21, '11', '啊啊啊啊 ', '2019-05-10 20:45:26', NULL, '-1', NULL);
INSERT INTO `notice` VALUES (22, '22', '', '2019-05-10 20:48:58', NULL, '20180002', NULL);
INSERT INTO `notice` VALUES (23, '11', 'qwq', '2019-07-22 22:23:35', NULL, '20180004', NULL);
INSERT INTO `notice` VALUES (24, '11', 'qwqwqwq', '2019-07-22 22:23:48', NULL, '-1', NULL);

-- ----------------------------
-- Table structure for suggestion
-- ----------------------------
DROP TABLE IF EXISTS `suggestion`;
CREATE TABLE `suggestion`  (
  `voteid` int(10) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`voteid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of suggestion
-- ----------------------------
INSERT INTO `suggestion` VALUES (1, '是的');
INSERT INTO `suggestion` VALUES (2, '带我 ');
INSERT INTO `suggestion` VALUES (3, '带我');
INSERT INTO `suggestion` VALUES (4, '改一改吧');
INSERT INTO `suggestion` VALUES (5, '不想去打篮球');
INSERT INTO `suggestion` VALUES (16, '不去，周六我去打篮球');
INSERT INTO `suggestion` VALUES (17, '1111');
INSERT INTO `suggestion` VALUES (18, '请问请问');
INSERT INTO `suggestion` VALUES (19, '请问强强强强');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `grade` int(2) NULL DEFAULT NULL,
  `userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `admin` bit(1) NULL DEFAULT NULL,
  `lastonline` date NULL DEFAULT NULL,
  `status` int(1) NOT NULL,
  `avatar` int(10) NULL DEFAULT NULL,
  `gamerecord` int(6) NULL DEFAULT NULL
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('yjy', '19991231', '2', 1, '1', b'1', '2019-04-22', 0, NULL, NULL);
INSERT INTO `user` VALUES ('nancheng58', '19991231', '2', 1, '2', b'1', '2019-04-24', 0, NULL, NULL);
INSERT INTO `user` VALUES ('LIAUAU', 'qwq', '6', 1, '3', b'0', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES ('qwq', '1', '1班', 1, '4', b'0', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES ('qwq', '1', '1班', 1, '5', b'0', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES ('lijinjin', '123', '6班', 1, '6', b'0', NULL, 0, NULL, NULL);
INSERT INTO `user` VALUES ('1', '1', '1', 1, '1', b'1', '2019-11-08', 1, NULL, 1111);
INSERT INTO `user` VALUES ('33', '33', NULL, NULL, '33', NULL, '2019-07-22', 0, 5, NULL);
INSERT INTO `user` VALUES ('11', '11', NULL, NULL, '11', NULL, '2019-11-08', 1, 4, 1428);
INSERT INTO `user` VALUES ('22', '22', NULL, NULL, '22', NULL, '2019-11-08', 1, 5, 2222);

-- ----------------------------
-- Table structure for vote
-- ----------------------------
DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote`  (
  `noticeid` int(10) UNSIGNED NULL DEFAULT NULL,
  `classname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(10) NULL DEFAULT NULL,
  `time` timestamp(0) NULL DEFAULT NULL,
  `agree` int(10) NULL DEFAULT NULL,
  `disagree` int(255) NULL DEFAULT NULL,
  `voteid` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`voteid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vote
-- ----------------------------
INSERT INTO `vote` VALUES (16, '20180001', 2, '2019-05-03 14:15:37', 2, 0, 16);
INSERT INTO `vote` VALUES (18, '20180003', 2, '2019-05-10 19:25:28', 1, 1, 17);
INSERT INTO `vote` VALUES (21, '20180001', 2, '2019-05-10 20:45:26', 1, 1, 18);
INSERT INTO `vote` VALUES (24, '20180004', 2, '2019-07-22 22:23:48', 2, 0, 19);

-- ----------------------------
-- Table structure for whovote
-- ----------------------------
DROP TABLE IF EXISTS `whovote`;
CREATE TABLE `whovote`  (
  `voteid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`voteid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of whovote
-- ----------------------------
INSERT INTO `whovote` VALUES (16, '22');
INSERT INTO `whovote` VALUES (17, '22');
INSERT INTO `whovote` VALUES (18, '11');
INSERT INTO `whovote` VALUES (19, '22');

SET FOREIGN_KEY_CHECKS = 1;
