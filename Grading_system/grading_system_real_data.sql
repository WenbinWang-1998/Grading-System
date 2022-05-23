/*
 Navicat MySQL Data Transfer

 Source Server         : demo
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : grading_system

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 05/05/2022 23:02:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Course
-- ----------------------------
DROP TABLE IF EXISTS `Course`;
CREATE TABLE `Course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `credit` int DEFAULT NULL,
  `section` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `semester` varchar(255) DEFAULT NULL,
  `professorId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `professorId` (`professorId`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`professorId`) REFERENCES `Staff` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Course
-- ----------------------------
BEGIN;
INSERT INTO `Course` VALUES (1, 'cs611', 4, 'a1', '2022', 'Spring', 1);
INSERT INTO `Course` VALUES (2, 'ec530', 4, 'a2', '2022', 'Spring', 2);
INSERT INTO `Course` VALUES (3, 'cs660', 4, 'a3', '2022', 'Spring', 3);
INSERT INTO `Course` VALUES (4, 'CS600', 4, 'a1', '2022', 'Spring', 4);
COMMIT;

-- ----------------------------
-- Table structure for Credential
-- ----------------------------
DROP TABLE IF EXISTS `Credential`;
CREATE TABLE `Credential` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `authoriry` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Credential
-- ----------------------------
BEGIN;
INSERT INTO `Credential` VALUES (1, 'cpk@bu.edu', '123', 1);
INSERT INTO `Credential` VALUES (2, 'jh@bu.edu', '123', 0);
INSERT INTO `Credential` VALUES (3, 'manos@bu.edu', '123', 0);
INSERT INTO `Credential` VALUES (4, 'kw@bu.edu', '123', 0);
COMMIT;

-- ----------------------------
-- Table structure for Enrollment
-- ----------------------------
DROP TABLE IF EXISTS `Enrollment`;
CREATE TABLE `Enrollment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `studentId` int DEFAULT NULL,
  `courseId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `studentId` (`studentId`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `enrollment_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `Student` (`id`),
  CONSTRAINT `enrollment_ibfk_2` FOREIGN KEY (`courseId`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Enrollment
-- ----------------------------
BEGIN;
INSERT INTO `Enrollment` VALUES (1, 1, 1);
INSERT INTO `Enrollment` VALUES (2, 2, 1);
INSERT INTO `Enrollment` VALUES (3, 3, 2);
INSERT INTO `Enrollment` VALUES (4, 4, 2);
INSERT INTO `Enrollment` VALUES (5, 3, 3);
INSERT INTO `Enrollment` VALUES (6, 3, 1);
INSERT INTO `Enrollment` VALUES (7, 4, 1);
INSERT INTO `Enrollment` VALUES (8, 2, 4);
INSERT INTO `Enrollment` VALUES (9, 1, 3);
INSERT INTO `Enrollment` VALUES (11, 2, 3);
INSERT INTO `Enrollment` VALUES (12, 5, 3);
INSERT INTO `Enrollment` VALUES (13, 6, 3);
INSERT INTO `Enrollment` VALUES (14, 7, 3);
INSERT INTO `Enrollment` VALUES (15, 8, 3);
INSERT INTO `Enrollment` VALUES (16, 4, 3);
COMMIT;

-- ----------------------------
-- Table structure for Grade
-- ----------------------------
DROP TABLE IF EXISTS `Grade`;
CREATE TABLE `Grade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subTaskId` int DEFAULT NULL,
  `studentId` int DEFAULT NULL,
  `score` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `studentId` (`studentId`),
  KEY `subTaskId` (`subTaskId`),
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`studentId`) REFERENCES `Student` (`id`),
  CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`subTaskId`) REFERENCES `SubTask` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Grade
-- ----------------------------
BEGIN;
INSERT INTO `Grade` VALUES (1, 1, 1, 89);
INSERT INTO `Grade` VALUES (2, 1, 2, 93);
INSERT INTO `Grade` VALUES (3, 1, 3, 85);
INSERT INTO `Grade` VALUES (4, 1, 4, 80);
INSERT INTO `Grade` VALUES (5, 2, 4, 95);
INSERT INTO `Grade` VALUES (6, 2, 3, 93);
INSERT INTO `Grade` VALUES (7, 2, 2, 87);
INSERT INTO `Grade` VALUES (8, 2, 1, 98);
INSERT INTO `Grade` VALUES (9, 3, 1, 86);
INSERT INTO `Grade` VALUES (10, 3, 2, 75);
INSERT INTO `Grade` VALUES (11, 3, 3, 95);
INSERT INTO `Grade` VALUES (12, 3, 4, 99);
INSERT INTO `Grade` VALUES (13, 4, 1, 93);
INSERT INTO `Grade` VALUES (14, 4, 2, 98);
INSERT INTO `Grade` VALUES (15, 4, 3, 92);
INSERT INTO `Grade` VALUES (16, 4, 4, 97);
INSERT INTO `Grade` VALUES (17, 5, 3, 96);
INSERT INTO `Grade` VALUES (18, 5, 4, 100);
INSERT INTO `Grade` VALUES (19, 6, 3, 100);
INSERT INTO `Grade` VALUES (20, 6, 4, 98);
INSERT INTO `Grade` VALUES (21, 7, 3, 96);
INSERT INTO `Grade` VALUES (23, 10, 1, 89);
INSERT INTO `Grade` VALUES (24, 10, 2, 98);
INSERT INTO `Grade` VALUES (25, 10, 3, 70);
INSERT INTO `Grade` VALUES (26, 10, 4, 97);
INSERT INTO `Grade` VALUES (27, 11, 1, 97);
INSERT INTO `Grade` VALUES (28, 11, 2, 96);
INSERT INTO `Grade` VALUES (29, 11, 3, 95);
INSERT INTO `Grade` VALUES (30, 11, 4, 100);
INSERT INTO `Grade` VALUES (31, 12, 1, 98);
INSERT INTO `Grade` VALUES (32, 12, 2, 90);
INSERT INTO `Grade` VALUES (33, 12, 3, 85);
INSERT INTO `Grade` VALUES (34, 12, 4, 93);
INSERT INTO `Grade` VALUES (35, 13, 1, 95);
INSERT INTO `Grade` VALUES (36, 13, 2, 80);
INSERT INTO `Grade` VALUES (37, 13, 3, 85);
INSERT INTO `Grade` VALUES (38, 13, 4, 93);
INSERT INTO `Grade` VALUES (39, 14, 3, 95);
INSERT INTO `Grade` VALUES (41, 15, 2, 97);
INSERT INTO `Grade` VALUES (42, 16, 2, 97);
INSERT INTO `Grade` VALUES (43, 17, 2, 96);
INSERT INTO `Grade` VALUES (44, 18, 2, 95);
INSERT INTO `Grade` VALUES (45, 14, 1, 100);
INSERT INTO `Grade` VALUES (46, 14, 2, 96);
INSERT INTO `Grade` VALUES (47, 14, 6, 95);
INSERT INTO `Grade` VALUES (48, 14, 7, 70);
INSERT INTO `Grade` VALUES (49, 14, 8, 60);
INSERT INTO `Grade` VALUES (50, 7, 1, 93);
INSERT INTO `Grade` VALUES (51, 7, 2, 95);
INSERT INTO `Grade` VALUES (52, 7, 4, 100);
INSERT INTO `Grade` VALUES (53, 7, 5, 90);
INSERT INTO `Grade` VALUES (54, 7, 6, 89);
INSERT INTO `Grade` VALUES (55, 7, 7, 90);
INSERT INTO `Grade` VALUES (56, 7, 8, 85);
INSERT INTO `Grade` VALUES (57, 8, 1, 98);
INSERT INTO `Grade` VALUES (58, 8, 2, 90);
INSERT INTO `Grade` VALUES (59, 8, 3, 91);
INSERT INTO `Grade` VALUES (60, 8, 4, 92);
INSERT INTO `Grade` VALUES (61, 8, 5, 93);
INSERT INTO `Grade` VALUES (62, 8, 6, 78);
INSERT INTO `Grade` VALUES (63, 8, 7, 87);
INSERT INTO `Grade` VALUES (64, 8, 8, 70);
INSERT INTO `Grade` VALUES (65, 9, 1, 90);
INSERT INTO `Grade` VALUES (66, 9, 2, 79);
INSERT INTO `Grade` VALUES (67, 9, 3, 87);
INSERT INTO `Grade` VALUES (68, 9, 4, 89);
INSERT INTO `Grade` VALUES (69, 9, 5, 98);
INSERT INTO `Grade` VALUES (70, 9, 6, 90);
INSERT INTO `Grade` VALUES (71, 9, 7, 70);
INSERT INTO `Grade` VALUES (72, 9, 8, 65);
INSERT INTO `Grade` VALUES (73, 14, 4, 98);
INSERT INTO `Grade` VALUES (74, 14, 5, 93);
COMMIT;

-- ----------------------------
-- Table structure for Staff
-- ----------------------------
DROP TABLE IF EXISTS `Staff`;
CREATE TABLE `Staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `credentialId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `credentialId` (`credentialId`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`credentialId`) REFERENCES `Credential` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Staff
-- ----------------------------
BEGIN;
INSERT INTO `Staff` VALUES (1, 'Christine Papadakis', 'cpk@bu.edu', 1);
INSERT INTO `Staff` VALUES (2, 'John Hull', 'jh@bu.edu', 2);
INSERT INTO `Staff` VALUES (3, 'Manos', 'manos@bu.edu', 3);
INSERT INTO `Staff` VALUES (4, 'Kollin Wang', 'kw@bu.edu', 4);
COMMIT;

-- ----------------------------
-- Table structure for Student
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `buId` varchar(255) DEFAULT NULL,
  `isWithdrawn` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Student
-- ----------------------------
BEGIN;
INSERT INTO `Student` VALUES (1, 'dc', 'dc@bu.edu', 'U01', 0);
INSERT INTO `Student` VALUES (2, 'wenbin', 'wenbin@bu.edu', 'U02', 0);
INSERT INTO `Student` VALUES (3, 'kaiyin', 'kaiyin@bu.edu', 'U03', 0);
INSERT INTO `Student` VALUES (4, 'shunyao', 'shunyao@bu.edu', 'U04', 0);
INSERT INTO `Student` VALUES (5, 'Micheal', 'mi@bu.edu', 'U05', 0);
INSERT INTO `Student` VALUES (6, 'Amy', 'amy@bu.edu', 'U06', 0);
INSERT INTO `Student` VALUES (7, 'Jeff', 'jeff@bu.edu', 'U07', 0);
INSERT INTO `Student` VALUES (8, 'Jacy', 'jay@bu.edu', 'U08', 1);
COMMIT;

-- ----------------------------
-- Table structure for SubTask
-- ----------------------------
DROP TABLE IF EXISTS `SubTask`;
CREATE TABLE `SubTask` (
  `id` int NOT NULL AUTO_INCREMENT,
  `taskId` int DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `TotalPoints` float DEFAULT NULL,
  `releasedDate` varchar(255) DEFAULT NULL,
  `dueDate` varchar(255) DEFAULT NULL,
  `groupProject` tinyint(1) DEFAULT NULL,
  `maxBonusPoints` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `taskId` (`taskId`),
  CONSTRAINT `subtask_ibfk_1` FOREIGN KEY (`taskId`) REFERENCES `Task` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of SubTask
-- ----------------------------
BEGIN;
INSERT INTO `SubTask` VALUES (1, 1, 30, 'sub1_1', 100, '01/22/2022', '01/30/2022', 0, 10);
INSERT INTO `SubTask` VALUES (2, 1, 30, 'sub1_2', 100, '02/01/2022', '02/07/2022', 0, 0);
INSERT INTO `SubTask` VALUES (3, 1, 40, 'sub1_3', 100, '02/11/2022', '02/18/2022', 0, 0);
INSERT INTO `SubTask` VALUES (4, 2, 100, 'hw2', 100, '02/22/2022', '02/28/2022', 0, 0);
INSERT INTO `SubTask` VALUES (5, 3, 100, 'hw1', 100, '03/04/2022', '03/11/2022', 0, 20);
INSERT INTO `SubTask` VALUES (6, 4, 100, 'hw2', 100, '03/15/2022', '03/22/2022', 0, 20);
INSERT INTO `SubTask` VALUES (7, 5, 40, 'sub1_1', 100, '03/25/2022', '03/30/2022', 1, 20);
INSERT INTO `SubTask` VALUES (8, 5, 40, 'sub1_2', 100, '04/06/2022', '04/14/2022', 1, 20);
INSERT INTO `SubTask` VALUES (9, 5, 20, 'sub1_3', 100, '04/18/2022', '05/03/2022', 1, 10);
INSERT INTO `SubTask` VALUES (10, 11, 100, 'midterm', 100, '03/22/2022', '03/22/2022', 0, 0);
INSERT INTO `SubTask` VALUES (11, 12, 100, 'hw3', 100, '04/01/2022', '04/08/2022', 0, 10);
INSERT INTO `SubTask` VALUES (12, 13, 100, 'hw4', 100, '04/14/2022', '04/21/2022', 0, 10);
INSERT INTO `SubTask` VALUES (13, 14, 100, 'final', 100, '05/04/2022', '05/04/2022', 0, 0);
INSERT INTO `SubTask` VALUES (14, 6, 100, 'hw2', 100, '04/27/2022', '05/04/2022', 0, 0);
INSERT INTO `SubTask` VALUES (15, 7, 100, 'hw1', 100, '02/01/2022', '02/07/2022', 0, 5);
INSERT INTO `SubTask` VALUES (16, 8, 100, 'hw2', 100, '02/13/2022', '02/21/2022', 0, 5);
INSERT INTO `SubTask` VALUES (17, 9, 100, 'hw3', 100, '03/03/2022', '03/13/2022', 0, 5);
INSERT INTO `SubTask` VALUES (18, 10, 100, 'hw4', 100, '04/01/2022', '04/20/2022', 0, 5);
COMMIT;

-- ----------------------------
-- Table structure for Task
-- ----------------------------
DROP TABLE IF EXISTS `Task`;
CREATE TABLE `Task` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `courseId` int DEFAULT NULL,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `courseId` (`courseId`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`courseId`) REFERENCES `Course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of Task
-- ----------------------------
BEGIN;
INSERT INTO `Task` VALUES (1, 'hw1', 1, 10);
INSERT INTO `Task` VALUES (2, 'hw2', 1, 10);
INSERT INTO `Task` VALUES (3, 'hw1', 2, 50);
INSERT INTO `Task` VALUES (4, 'hw2', 2, 50);
INSERT INTO `Task` VALUES (5, 'hw1', 3, 50);
INSERT INTO `Task` VALUES (6, 'hw2', 3, 50);
INSERT INTO `Task` VALUES (7, 'hw1', 4, 25);
INSERT INTO `Task` VALUES (8, 'hw2', 4, 25);
INSERT INTO `Task` VALUES (9, 'hw3', 4, 25);
INSERT INTO `Task` VALUES (10, 'hw4', 4, 25);
INSERT INTO `Task` VALUES (11, 'midterm', 1, 20);
INSERT INTO `Task` VALUES (12, 'hw3', 1, 20);
INSERT INTO `Task` VALUES (13, 'hw4', 1, 20);
INSERT INTO `Task` VALUES (14, 'final', 1, 20);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
