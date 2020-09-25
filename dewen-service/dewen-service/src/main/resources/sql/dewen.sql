/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : dewen

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 25/09/2020 17:02:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for common_file_system
-- ----------------------------
DROP TABLE IF EXISTS `common_file_system`;
CREATE TABLE `common_file_system`  (
  `ID` int(10) NOT NULL AUTO_INCREMENT COMMENT '文件系统ID',
  `FILE_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `UPL_MANAGER` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传负责人',
  `UPL_DATE` datetime(0) NULL DEFAULT NULL COMMENT '上传日期',
  `FILE_PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上传文件路径',
  `FILE_EXTENDS` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `SYSTEM_TYPE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统类型:1.workflow;2.CMDB',
  `TO_BE_FOLLOW_UP` int(1) NULL DEFAULT NULL COMMENT '后续跟进:1.只存储;2.需要进一步处理',
  `MODULE_TYPE` int(11) NULL DEFAULT NULL COMMENT '模块类型:1.户外;2.户内;3.库存;4.办公;5.信息',
  `PROCESS_STATUS` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理状态:1.已处理;2.未处理;3.处理失败;4.丢弃',
  `EMPLOYEE_ID` int(10) NULL DEFAULT NULL,
  `VISIT_URI` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问路径,不包括域名部分',
  `ENABLED` int(1) NULL DEFAULT NULL,
  `REMARK` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CREATE_STAFF` int(10) NULL DEFAULT NULL,
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL,
  `MODIFY_DATE` datetime(0) NULL DEFAULT NULL,
  `MODIFY_STAFF` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `ID_UNIQUE`(`ID`) USING BTREE,
  INDEX `idx_COM_FILE_SYSTEM_FILE_NAME`(`FILE_NAME`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 155 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件系统表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of common_file_system
-- ----------------------------
INSERT INTO `common_file_system` VALUES (1, '参数.txt', NULL, '2020-07-15 17:50:52', '\\d5690caeec3348128316c30ccc76e4ed\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/1', 1, NULL, NULL, '2020-07-15 17:50:52', '2020-07-15 17:50:52', NULL);
INSERT INTO `common_file_system` VALUES (2, '参数.txt', NULL, '2020-07-15 17:51:06', '\\c881f1e18e7042398d877b6e0be4a4a5\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/2', 1, NULL, NULL, '2020-07-15 17:51:06', '2020-07-15 17:51:06', NULL);
INSERT INTO `common_file_system` VALUES (3, 'QQ截图20200715164647.png', NULL, '2020-07-16 09:06:20', '\\b9d1ece3fb6f473b9f3bab4b67c703a6\\QQ截图20200715164647.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/3', 1, NULL, NULL, '2020-07-16 09:06:20', '2020-07-16 09:06:20', NULL);
INSERT INTO `common_file_system` VALUES (4, '044001900411-98473570-发票验伪.png', NULL, '2020-07-16 09:16:43', '\\ea48e3b2d1a3496cb9da1b9351cc8fff\\044001900411-98473570-发票验伪.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/4', 1, NULL, NULL, '2020-07-16 09:16:43', '2020-07-16 09:16:43', NULL);
INSERT INTO `common_file_system` VALUES (5, 'QQ截图20200715164647.png', NULL, '2020-07-16 10:48:12', '\\85114054b0e44fdcbfeee24ced4936ae\\QQ截图20200715164647.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/5', 1, NULL, NULL, '2020-07-16 10:48:12', '2020-07-16 10:48:12', NULL);
INSERT INTO `common_file_system` VALUES (6, 'QQ截图20200715164647.png', NULL, '2020-07-16 10:57:00', '\\e5db76e2bfaf491d93207d10a99c9223\\QQ截图20200715164647.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/6', 1, NULL, NULL, '2020-07-16 10:57:00', '2020-07-16 10:57:00', NULL);
INSERT INTO `common_file_system` VALUES (7, '参数.txt', NULL, '2020-07-16 11:17:21', '\\dacf8109bccc4c2d832de1f40a6f54a9\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/7', 1, NULL, NULL, '2020-07-16 11:17:21', '2020-07-16 11:17:21', NULL);
INSERT INTO `common_file_system` VALUES (8, '参数.txt', NULL, '2020-07-16 11:17:35', '\\404dc9f628c34dac83640ead21dd062f\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/8', 1, NULL, NULL, '2020-07-16 11:17:35', '2020-07-16 11:17:35', NULL);
INSERT INTO `common_file_system` VALUES (9, '参数.txt', NULL, '2020-07-16 11:27:18', '\\a9fc8082f97c49f5a1a84be8c63eef32\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/9', 1, NULL, NULL, '2020-07-16 11:27:18', '2020-07-16 11:27:18', NULL);
INSERT INTO `common_file_system` VALUES (10, '参数.txt', NULL, '2020-07-16 11:29:09', '\\493a5980c9974e48a86b0a4ab28447b5\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/10', 1, NULL, NULL, '2020-07-16 11:29:09', '2020-07-16 11:29:09', NULL);
INSERT INTO `common_file_system` VALUES (11, '参数.txt', NULL, '2020-07-16 11:52:50', '\\8f7d94fcbdcf40a48cae9f8434b269ba\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/11', 1, NULL, NULL, '2020-07-16 11:52:50', '2020-07-16 11:52:50', NULL);
INSERT INTO `common_file_system` VALUES (12, '参数.txt', NULL, '2020-07-16 14:19:59', '\\d8de47a5657f4f72b8e0bdeb6d74c66f\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/12', 1, NULL, NULL, '2020-07-16 14:19:59', '2020-07-16 14:19:59', NULL);
INSERT INTO `common_file_system` VALUES (13, '参数.txt', NULL, '2020-07-16 14:29:47', '\\33464618713246829276f1d7484b3002\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/13', 1, NULL, NULL, '2020-07-16 14:29:47', '2020-07-16 14:29:47', NULL);
INSERT INTO `common_file_system` VALUES (14, '参数.txt', NULL, '2020-07-16 14:30:12', '\\994ba1d5058c4308ba748e3c8b64555b\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/14', 1, NULL, NULL, '2020-07-16 14:30:12', '2020-07-16 14:30:12', NULL);
INSERT INTO `common_file_system` VALUES (15, '参数.txt', NULL, '2020-07-16 14:31:30', '\\85e0848f913b4f01aa31e369c706c02e\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/15', 1, NULL, NULL, '2020-07-16 14:31:30', '2020-07-16 14:31:30', NULL);
INSERT INTO `common_file_system` VALUES (16, '参数.txt', NULL, '2020-07-16 14:31:52', '\\c3e61ec1b1c64393ba93322946d7cfa7\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/16', 1, NULL, NULL, '2020-07-16 14:31:52', '2020-07-16 14:31:52', NULL);
INSERT INTO `common_file_system` VALUES (17, '参数.txt', NULL, '2020-07-16 14:32:52', '\\73712f2279c64dd39f2a7ee95c25d95e\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/17', 1, NULL, NULL, '2020-07-16 14:32:52', '2020-07-16 14:32:52', NULL);
INSERT INTO `common_file_system` VALUES (18, '参数.txt', NULL, '2020-07-16 14:33:17', '\\67fcf579c60c439e869d2b63a1db5bc2\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/18', 1, NULL, NULL, '2020-07-16 14:33:17', '2020-07-16 14:33:17', NULL);
INSERT INTO `common_file_system` VALUES (19, 'QQ截图20200715164647.png', NULL, '2020-07-16 14:34:13', '\\ffe56b9abc3a4edeb9fb39b62590f503\\QQ截图20200715164647.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/19', 1, NULL, NULL, '2020-07-16 14:34:13', '2020-07-16 14:34:13', NULL);
INSERT INTO `common_file_system` VALUES (20, '参数.txt', NULL, '2020-07-16 14:35:27', '\\ae79c0ff1be645b287be095a1b55daf9\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/20', 1, NULL, NULL, '2020-07-16 14:35:27', '2020-07-16 14:35:27', NULL);
INSERT INTO `common_file_system` VALUES (21, '参数.txt', NULL, '2020-07-16 15:07:28', '\\c1de362d18324cbcab9bb17fd08c7f6c\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/21', 1, NULL, NULL, '2020-07-16 15:07:28', '2020-07-16 15:07:28', NULL);
INSERT INTO `common_file_system` VALUES (22, '参数.txt', NULL, '2020-07-16 15:10:20', '\\5e7b0222e7984cd3ba73534602df1629\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/22', 1, NULL, NULL, '2020-07-16 15:10:20', '2020-07-16 15:10:20', NULL);
INSERT INTO `common_file_system` VALUES (23, '参数.txt', NULL, '2020-07-16 15:12:40', '\\3861549ce61f4362b8baa252cb01b879\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/23', 1, NULL, NULL, '2020-07-16 15:12:40', '2020-07-16 15:12:40', NULL);
INSERT INTO `common_file_system` VALUES (24, 'QQ截图20200715164647.png', NULL, '2020-07-16 15:13:18', '\\d6f16e1f329f4e45ae8a22ad51dde1dc\\QQ截图20200715164647.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/24', 1, NULL, NULL, '2020-07-16 15:13:18', '2020-07-16 15:13:18', NULL);
INSERT INTO `common_file_system` VALUES (25, '参数.txt', NULL, '2020-07-16 15:13:22', '\\a31ffa449527492dbfec96f196880755\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/25', 1, NULL, NULL, '2020-07-16 15:13:22', '2020-07-16 15:13:22', NULL);
INSERT INTO `common_file_system` VALUES (26, 'QQ截图20200715164647.png', NULL, '2020-07-16 15:19:52', '\\9a1ebe326c08473da2187e4980ee3c45\\QQ截图20200715164647.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/26', 1, NULL, NULL, '2020-07-16 15:19:52', '2020-07-16 15:19:52', NULL);
INSERT INTO `common_file_system` VALUES (27, '参数.txt', NULL, '2020-07-16 15:21:30', '\\0d993d5aa0c44b0cb3b872a8c9af5f01\\参数.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/27', 1, NULL, NULL, '2020-07-16 15:21:30', '2020-07-16 15:21:30', NULL);
INSERT INTO `common_file_system` VALUES (28, '044001900411-98473570-发票验伪.png', NULL, '2020-07-16 15:48:31', '\\cfc9efbf0b3b4d1e878a4bc7148914a4\\044001900411-98473570-发票验伪.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/28', 1, NULL, NULL, '2020-07-16 15:48:31', '2020-07-16 15:48:31', NULL);
INSERT INTO `common_file_system` VALUES (29, '044001900411-98473570-发票验伪.png', NULL, '2020-07-16 15:50:35', '\\6ba8431ac9684a24a146be539bce2e13\\044001900411-98473570-发票验伪.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/29', 1, NULL, NULL, '2020-07-16 15:50:35', '2020-07-16 15:50:35', NULL);
INSERT INTO `common_file_system` VALUES (30, '044001900411-98473570-发票验伪.png', NULL, '2020-07-16 15:54:10', '\\daecbc59c95142d385680b4130108556\\044001900411-98473570-发票验伪.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/30', 1, NULL, NULL, '2020-07-16 15:54:10', '2020-07-16 15:54:10', NULL);
INSERT INTO `common_file_system` VALUES (31, 'HengQing_大客户v2.mp', NULL, '2020-07-16 16:47:49', '\\1353eefd57a14827b743fc53c1f65f08\\HengQing_大客户v2.mp', 'mp', NULL, 1, 0, NULL, NULL, '/file/download/31', 1, NULL, NULL, '2020-07-16 16:47:49', '2020-07-16 16:47:49', NULL);
INSERT INTO `common_file_system` VALUES (32, 'HengQing_大客户v2.mp', NULL, '2020-07-16 16:49:55', '\\1b9080d1afcd42a5a00183c7e64e86bd\\HengQing_大客户v2.mp', 'mp', NULL, 1, 0, NULL, NULL, '/file/download/32', 1, NULL, NULL, '2020-07-16 16:49:55', '2020-07-16 16:49:55', NULL);
INSERT INTO `common_file_system` VALUES (33, 'idea快捷键.txt', NULL, '2020-07-16 17:05:07', '\\09b2e4af3fe14718a9a1947b06d8df7e\\idea快捷键.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/33', 1, NULL, NULL, '2020-07-16 17:05:07', '2020-07-16 17:05:07', NULL);
INSERT INTO `common_file_system` VALUES (34, 'idea快捷键.txt', NULL, '2020-07-16 17:11:50', '\\e914cb49261e485ba0ee24fae38f6d9b\\idea快捷键.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/34', 1, NULL, NULL, '2020-07-16 17:11:50', '2020-07-16 17:11:50', NULL);
INSERT INTO `common_file_system` VALUES (35, 'idea快捷键.txt', NULL, '2020-07-16 17:12:11', '\\0e697d321f5640ffb811a120968d48b5\\idea快捷键.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/35', 1, NULL, NULL, '2020-07-16 17:12:11', '2020-07-16 17:12:11', NULL);
INSERT INTO `common_file_system` VALUES (36, 'idea快捷键.txt', NULL, '2020-07-16 17:12:43', '\\4eb410a662554f18bd4499c95bbfee17\\idea快捷键.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/36', 1, NULL, NULL, '2020-07-16 17:12:43', '2020-07-16 17:12:43', NULL);
INSERT INTO `common_file_system` VALUES (37, '044001900411-98473570-发票验伪.png', NULL, '2020-07-16 17:18:44', '\\9874877248584bb3ae00c4f41aa8ea98\\044001900411-98473570-发票验伪.png', 'png', NULL, 1, 0, NULL, NULL, '/file/download/37', 1, NULL, NULL, '2020-07-16 17:18:44', '2020-07-16 17:18:44', NULL);
INSERT INTO `common_file_system` VALUES (38, 'idea快捷键.txt', NULL, '2020-07-16 17:38:05', '\\77ad48553eee4b17ac0e3e554fe6fd79\\idea快捷键.txt', 'txt', NULL, 1, 0, NULL, NULL, '/file/download/38', 1, NULL, NULL, '2020-07-16 17:38:05', '2020-07-16 17:38:05', NULL);
INSERT INTO `common_file_system` VALUES (39, 'test.pdf', NULL, '2020-07-17 10:27:58', '\\bb4ab1481df046079a4f5f2ae20b6878\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/39', 1, NULL, NULL, '2020-07-17 10:27:58', '2020-07-17 10:27:58', NULL);
INSERT INTO `common_file_system` VALUES (40, 'test.pdf', NULL, '2020-07-17 11:04:32', '\\ce0074910e354dc2865a368dca3a07d8\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/40', 1, NULL, NULL, '2020-07-17 11:04:32', '2020-07-17 11:04:32', NULL);
INSERT INTO `common_file_system` VALUES (41, 'test.pdf', NULL, '2020-07-17 11:05:18', '\\2721907080384d04bf24c1c2a59907d4\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/41', 1, NULL, NULL, '2020-07-17 11:05:18', '2020-07-17 11:05:18', NULL);
INSERT INTO `common_file_system` VALUES (42, 'test.pdf', NULL, '2020-07-17 11:06:45', '\\6ec3d72681f84e4b8aea0c6dc02a7ec5\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/42', 1, NULL, NULL, '2020-07-17 11:06:45', '2020-07-17 11:06:45', NULL);
INSERT INTO `common_file_system` VALUES (43, 'test.pdf', NULL, '2020-07-17 11:08:14', '\\e731f816e8b1426fa99b7dae1dd0ccfa\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/43', 1, NULL, NULL, '2020-07-17 11:08:14', '2020-07-17 11:08:14', NULL);
INSERT INTO `common_file_system` VALUES (44, 'test.pdf', NULL, '2020-07-17 11:11:08', '\\20bfe38a52404e2fa668375edee5034b\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/44', 1, NULL, NULL, '2020-07-17 11:11:08', '2020-07-17 11:11:08', NULL);
INSERT INTO `common_file_system` VALUES (45, 'test.pdf', NULL, '2020-07-17 11:12:16', '\\c8c87ff9c85f435c97a8a0e20ebb11d0\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/45', 1, NULL, NULL, '2020-07-17 11:12:16', '2020-07-17 11:12:16', NULL);
INSERT INTO `common_file_system` VALUES (46, 'test.pdf', NULL, '2020-07-17 11:13:32', '\\8b9596c147e8481ea911159ec8c57e48\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/46', 1, NULL, NULL, '2020-07-17 11:13:32', '2020-07-17 11:13:32', NULL);
INSERT INTO `common_file_system` VALUES (47, 'test.pdf', NULL, '2020-07-17 11:14:25', '\\2a0be1e9be114dafbbda0d086560ea8c\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/47', 1, NULL, NULL, '2020-07-17 11:14:25', '2020-07-17 11:14:25', NULL);
INSERT INTO `common_file_system` VALUES (48, '044001900411-98473570.pdf', NULL, '2020-07-17 11:23:01', '\\cbb371f103234160bc1022a4f20ebf0a\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/48', 1, NULL, NULL, '2020-07-17 11:23:01', '2020-07-17 11:23:01', NULL);
INSERT INTO `common_file_system` VALUES (49, '044001933111-13523520.pdf', NULL, '2020-07-17 11:23:10', '\\8d06d770e28c4870953a4607bd7f1866\\044001933111-13523520.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/49', 1, NULL, NULL, '2020-07-17 11:23:10', '2020-07-17 11:23:10', NULL);
INSERT INTO `common_file_system` VALUES (50, '044001900411-98830786.pdf', NULL, '2020-07-17 11:23:16', '\\fef9394035a84595a35f0feb466cfc8b\\044001900411-98830786.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/50', 1, NULL, NULL, '2020-07-17 11:23:16', '2020-07-17 11:23:16', NULL);
INSERT INTO `common_file_system` VALUES (51, '044001900411-98473570.pdf', NULL, '2020-07-17 11:23:27', '\\a8aa4dc1822d4bb283644d1f8a9db6f2\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/51', 1, NULL, NULL, '2020-07-17 11:23:27', '2020-07-17 11:23:27', NULL);
INSERT INTO `common_file_system` VALUES (52, 'test.pdf', NULL, '2020-07-17 15:24:25', '\\765cfa2f0f00403d9233ae9029606130\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/52', 1, NULL, NULL, '2020-07-17 15:24:25', '2020-07-17 15:24:25', NULL);
INSERT INTO `common_file_system` VALUES (53, 'test.pdf', NULL, '2020-07-17 15:25:03', '\\bc6a8e23746e4bb3a4b9371b51f854e7\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/53', 1, NULL, NULL, '2020-07-17 15:25:03', '2020-07-17 15:25:03', NULL);
INSERT INTO `common_file_system` VALUES (54, 'test.pdf', NULL, '2020-07-17 15:28:10', '\\9fd1dbf499704c11a751817dd442f441\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/54', 1, NULL, NULL, '2020-07-17 15:28:10', '2020-07-17 15:28:10', NULL);
INSERT INTO `common_file_system` VALUES (55, 'test.pdf', NULL, '2020-07-17 15:28:12', '\\b8a6abbb64da4e8da4a39c36e5dadf96\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/55', 1, NULL, NULL, '2020-07-17 15:28:12', '2020-07-17 15:28:12', NULL);
INSERT INTO `common_file_system` VALUES (56, 'test.pdf', NULL, '2020-07-17 15:29:36', '\\7930568610614156b0e00c81ab8e8e7f\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/56', 1, NULL, NULL, '2020-07-17 15:29:36', '2020-07-17 15:29:36', NULL);
INSERT INTO `common_file_system` VALUES (57, 'test.pdf', NULL, '2020-07-17 15:30:59', '\\569ed3e5bbf44302ac9e58e7ff47f443\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/57', 1, NULL, NULL, '2020-07-17 15:30:59', '2020-07-17 15:30:59', NULL);
INSERT INTO `common_file_system` VALUES (58, 'test.pdf', NULL, '2020-07-17 15:31:52', '\\1d7aee18dd0d4e60888e203cc157ecba\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/58', 1, NULL, NULL, '2020-07-17 15:31:52', '2020-07-17 15:31:52', NULL);
INSERT INTO `common_file_system` VALUES (59, 'test.pdf', NULL, '2020-07-17 15:31:56', '\\094456e1ad3a46a8b81d3d0ddab790fd\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/59', 1, NULL, NULL, '2020-07-17 15:31:56', '2020-07-17 15:31:56', NULL);
INSERT INTO `common_file_system` VALUES (60, 'test.pdf', NULL, '2020-07-17 15:34:06', '\\18a5f4e093464a938cf9e6bd7adc3840\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/60', 1, NULL, NULL, '2020-07-17 15:34:06', '2020-07-17 15:34:06', NULL);
INSERT INTO `common_file_system` VALUES (61, 'test.pdf', NULL, '2020-07-17 15:34:09', '\\462307b18d9a4beea2f5034710e9be97\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/61', 1, NULL, NULL, '2020-07-17 15:34:09', '2020-07-17 15:34:09', NULL);
INSERT INTO `common_file_system` VALUES (62, 'test.pdf', NULL, '2020-07-17 15:34:16', '\\e79f83ac05a343e4935637c99e018346\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/62', 1, NULL, NULL, '2020-07-17 15:34:16', '2020-07-17 15:34:16', NULL);
INSERT INTO `common_file_system` VALUES (63, '044001900411-98473570.pdf', NULL, '2020-07-17 15:45:16', '\\c40b6ba3f3e14c718a03151753f1187e\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/63', 1, NULL, NULL, '2020-07-17 15:45:16', '2020-07-17 15:45:16', NULL);
INSERT INTO `common_file_system` VALUES (64, '044001900411-98473570.pdf', NULL, '2020-07-17 15:45:38', '\\8600bf538950442eb078ea19d47e4254\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/64', 1, NULL, NULL, '2020-07-17 15:45:38', '2020-07-17 15:45:38', NULL);
INSERT INTO `common_file_system` VALUES (65, '044001900411-98473570.pdf', NULL, '2020-07-17 15:59:28', '\\a13ffbbf5e0c4309acb708495aa00508\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/65', 1, NULL, NULL, '2020-07-17 15:59:28', '2020-07-17 15:59:28', NULL);
INSERT INTO `common_file_system` VALUES (66, '044001900411-98473570.pdf', NULL, '2020-07-17 16:02:29', '\\77db2309a8184fcf85c0a7242d036161\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/66', 1, NULL, NULL, '2020-07-17 16:02:29', '2020-07-17 16:02:29', NULL);
INSERT INTO `common_file_system` VALUES (67, 'test.pdf', NULL, '2020-07-17 16:14:47', '\\2ab5deaa282b4aefbe0c1c027fd99b16\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/67', 1, NULL, NULL, '2020-07-17 16:14:47', '2020-07-17 16:14:47', NULL);
INSERT INTO `common_file_system` VALUES (68, 'test.pdf', NULL, '2020-07-17 16:14:51', '\\88c016b1ac7d411c8d39e57f017d15d7\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/68', 1, NULL, NULL, '2020-07-17 16:14:51', '2020-07-17 16:14:51', NULL);
INSERT INTO `common_file_system` VALUES (69, 'test.pdf', NULL, '2020-07-20 11:55:30', '\\784f54627b3b44e1bf5b2f93b4810ee5\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/69', 1, NULL, NULL, '2020-07-20 11:55:30', '2020-07-20 11:55:30', NULL);
INSERT INTO `common_file_system` VALUES (70, 'test.pdf', NULL, '2020-07-20 11:55:35', '\\24bb0387345f4997b83617c78f9354a1\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/70', 1, NULL, NULL, '2020-07-20 11:55:35', '2020-07-20 11:55:35', NULL);
INSERT INTO `common_file_system` VALUES (71, 'test.pdf', NULL, '2020-07-20 11:56:01', '\\20ac06e873fa4708ade44b92667deaa6\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/71', 1, NULL, NULL, '2020-07-20 11:56:01', '2020-07-20 11:56:01', NULL);
INSERT INTO `common_file_system` VALUES (72, 'test.pdf', NULL, '2020-07-20 11:57:11', '\\bfc3f0f498a54a6ca7b6663e0b3ac863\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/72', 1, NULL, NULL, '2020-07-20 11:57:11', '2020-07-20 11:57:11', NULL);
INSERT INTO `common_file_system` VALUES (73, 'test.pdf', NULL, '2020-07-20 11:58:15', '\\5065acbb3b1f4f4391d2eed0d3f1098e\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/73', 1, NULL, NULL, '2020-07-20 11:58:15', '2020-07-20 11:58:15', NULL);
INSERT INTO `common_file_system` VALUES (74, 'test.pdf', NULL, '2020-07-22 11:22:25', '\\01a78162d730439d800d38bdb5eb15b6\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/74', 1, NULL, NULL, '2020-07-22 11:22:25', '2020-07-22 11:22:25', NULL);
INSERT INTO `common_file_system` VALUES (75, 'test.pdf', NULL, '2020-08-03 09:09:00', '\\ef3ecead8d2b4e4185005f02d2c774da\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/75', 1, NULL, NULL, '2020-08-03 09:09:00', '2020-08-03 09:09:00', NULL);
INSERT INTO `common_file_system` VALUES (76, 'test.pdf', NULL, '2020-08-03 09:37:33', '\\ed2ee8b075a146ada0ce3dae2745e4fa\\test.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/76', 1, NULL, NULL, '2020-08-03 09:37:33', '2020-08-03 09:37:33', NULL);
INSERT INTO `common_file_system` VALUES (77, '服务台.pdf', NULL, '2020-08-03 10:01:57', '\\c0bb16a53ee14850831c3c9e59755cf8\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/77', 1, NULL, NULL, '2020-08-03 10:01:57', '2020-08-03 10:01:57', NULL);
INSERT INTO `common_file_system` VALUES (78, '服务台.pdf', NULL, '2020-08-03 10:04:59', '\\6273f894079c4ec09725139763e31202\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/78', 1, NULL, NULL, '2020-08-03 10:04:59', '2020-08-03 10:04:59', NULL);
INSERT INTO `common_file_system` VALUES (79, '服务台.pdf', NULL, '2020-08-03 10:33:43', '\\c413853365904cb585436865e2542710\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/79', 1, NULL, NULL, '2020-08-03 10:33:43', '2020-08-03 10:33:43', NULL);
INSERT INTO `common_file_system` VALUES (80, '服务台.pdf', NULL, '2020-08-03 10:43:44', '\\508606b06204404890d5c053445d4cae\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/80', 1, NULL, NULL, '2020-08-03 10:43:44', '2020-08-03 10:43:44', NULL);
INSERT INTO `common_file_system` VALUES (81, '桌面.jpg', NULL, '2020-08-03 10:52:15', '\\9d5554b4b9bb47dcb9bbcbb87d8ea6d8\\桌面.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/81', 1, NULL, NULL, '2020-08-03 10:52:15', '2020-08-03 10:52:15', NULL);
INSERT INTO `common_file_system` VALUES (82, '服务台.pdf', NULL, '2020-08-03 10:52:28', '\\a95b60ebf321473a91d91e60330d5897\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/82', 1, NULL, NULL, '2020-08-03 10:52:28', '2020-08-03 10:52:28', NULL);
INSERT INTO `common_file_system` VALUES (83, '服务台.pdf', NULL, '2020-08-03 11:15:56', '\\68a4fa7d624e48b7a93c0f9775c3626e\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/83', 1, NULL, NULL, '2020-08-03 11:15:56', '2020-08-03 11:15:56', NULL);
INSERT INTO `common_file_system` VALUES (84, '桌面.jpg', NULL, '2020-08-03 11:16:04', '\\7452ef7532f7453984c21718a90d7d08\\桌面.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/84', 1, NULL, NULL, '2020-08-03 11:16:04', '2020-08-03 11:16:04', NULL);
INSERT INTO `common_file_system` VALUES (85, '服务台.pdf', NULL, '2020-08-03 11:17:08', '\\18c947fc28d64ffebeebb500acd0e1fa\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/85', 1, NULL, NULL, '2020-08-03 11:17:08', '2020-08-03 11:17:08', NULL);
INSERT INTO `common_file_system` VALUES (86, '桌面.jpg', NULL, '2020-08-03 11:17:17', '\\d42bec1c0e6443e4968128a766470b74\\桌面.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/86', 1, NULL, NULL, '2020-08-03 11:17:17', '2020-08-03 11:17:17', NULL);
INSERT INTO `common_file_system` VALUES (87, '桌面.jpg', NULL, '2020-08-03 11:32:11', '\\fd55184874c342b3889043dfecb810fb\\桌面.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/87', 1, NULL, NULL, '2020-08-03 11:32:11', '2020-08-03 11:32:11', NULL);
INSERT INTO `common_file_system` VALUES (88, '桌面.jpg', NULL, '2020-08-03 11:33:06', '\\a9a29fd377dd40278ac22dcd3849b37a\\桌面.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/88', 1, NULL, NULL, '2020-08-03 11:33:06', '2020-08-03 11:33:06', NULL);
INSERT INTO `common_file_system` VALUES (89, '桌面.jpg', NULL, '2020-08-03 11:35:45', '\\5f0516f76cb84d5faed7678b8d327de9\\桌面.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/89', 1, NULL, NULL, '2020-08-03 11:35:45', '2020-08-03 11:35:45', NULL);
INSERT INTO `common_file_system` VALUES (90, '服务台.pdf', NULL, '2020-08-03 11:36:15', '\\ad0c62dadcb642129d524b9140fa2590\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/90', 1, NULL, NULL, '2020-08-03 11:36:15', '2020-08-03 11:36:15', NULL);
INSERT INTO `common_file_system` VALUES (91, '桌面.jpg', NULL, '2020-08-03 11:36:32', '\\2ad226ebd27f46b5a64bffbbf3440264\\桌面.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/91', 1, NULL, NULL, '2020-08-03 11:36:32', '2020-08-03 11:36:32', NULL);
INSERT INTO `common_file_system` VALUES (92, '桌面.jpg', NULL, '2020-08-03 11:37:09', '\\3edde94f97224ba79883bd1e17580a88\\桌面.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/92', 1, NULL, NULL, '2020-08-03 11:37:09', '2020-08-03 11:37:09', NULL);
INSERT INTO `common_file_system` VALUES (93, '1.jpg', NULL, '2020-08-03 15:52:26', '\\ad7d1c529d15416cb220facffe4b366c\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/93', 1, NULL, NULL, '2020-08-03 15:52:26', '2020-08-03 15:52:26', NULL);
INSERT INTO `common_file_system` VALUES (94, '1.jpg', NULL, '2020-08-03 15:54:40', '\\93ad8ce2fa1a43fda25fa84a36c20d14\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/94', 1, NULL, NULL, '2020-08-03 15:54:40', '2020-08-03 15:54:40', NULL);
INSERT INTO `common_file_system` VALUES (95, '1.jpg', NULL, '2020-08-04 10:18:57', '\\36c9d96cd4af4dcab95139ab947a36e8\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/95', 1, NULL, NULL, '2020-08-04 10:18:57', '2020-08-04 10:18:57', NULL);
INSERT INTO `common_file_system` VALUES (96, '1.jpg', NULL, '2020-08-04 10:52:57', '\\ecfa4896bba2452b90231b2e9717a8b6\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/96', 1, NULL, NULL, '2020-08-04 10:52:57', '2020-08-04 10:52:57', NULL);
INSERT INTO `common_file_system` VALUES (97, '1.jpg', NULL, '2020-08-04 10:54:33', '\\2db93abf7bca4fecb84869454678e6b2\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/97', 1, NULL, NULL, '2020-08-04 10:54:33', '2020-08-04 10:54:33', NULL);
INSERT INTO `common_file_system` VALUES (98, '1.jpg', NULL, '2020-08-04 10:55:44', '\\df317d8f3a7e4c17b8696fcae946164d\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/98', 1, NULL, NULL, '2020-08-04 10:55:44', '2020-08-04 10:55:44', NULL);
INSERT INTO `common_file_system` VALUES (99, '1.jpg', NULL, '2020-08-04 10:56:27', '\\3cbaba4885434b54b94dfa151f34873f\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/99', 1, NULL, NULL, '2020-08-04 10:56:27', '2020-08-04 10:56:27', NULL);
INSERT INTO `common_file_system` VALUES (100, '1.jpg', NULL, '2020-08-04 11:02:57', '\\2d91247751984083b91b2578e9fa4e02\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/100', 1, NULL, NULL, '2020-08-04 11:02:57', '2020-08-04 11:02:57', NULL);
INSERT INTO `common_file_system` VALUES (101, '1.jpg', NULL, '2020-08-04 11:03:44', '\\948a8ecc009f4a37b171b08a617d7394\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/101', 1, NULL, NULL, '2020-08-04 11:03:44', '2020-08-04 11:03:44', NULL);
INSERT INTO `common_file_system` VALUES (102, '1.jpg', NULL, '2020-08-04 11:12:49', '\\510aff8cc7fb4a32a0314a93703f3d3d\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/102', 1, NULL, NULL, '2020-08-04 11:12:49', '2020-08-04 11:12:49', NULL);
INSERT INTO `common_file_system` VALUES (103, '1.jpg', NULL, '2020-08-04 11:19:04', '\\effc85d729444872b72703bf9d400b7e\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/103', 1, NULL, NULL, '2020-08-04 11:19:04', '2020-08-04 11:19:04', NULL);
INSERT INTO `common_file_system` VALUES (104, '1.jpg', NULL, '2020-08-04 14:21:44', '\\2d738143b0454e2aa0e896dddb4ec1a7\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/104', 1, NULL, NULL, '2020-08-04 14:21:44', '2020-08-04 14:21:44', NULL);
INSERT INTO `common_file_system` VALUES (105, '1.jpg', NULL, '2020-08-04 14:23:45', '\\48513606b2b54517aea9cac378f2343a\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/105', 1, NULL, NULL, '2020-08-04 14:23:45', '2020-08-04 14:23:45', NULL);
INSERT INTO `common_file_system` VALUES (106, '服务台.pdf', NULL, '2020-08-04 14:41:54', '\\70853e8233e449d194da3f9e1c7b0017\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/106', 1, NULL, NULL, '2020-08-04 14:41:54', '2020-08-04 14:41:54', NULL);
INSERT INTO `common_file_system` VALUES (107, '1.jpg', NULL, '2020-08-04 15:10:04', '\\26644f926671462989c2f2fa592bf1c7\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/107', 1, NULL, NULL, '2020-08-04 15:10:04', '2020-08-04 15:10:04', NULL);
INSERT INTO `common_file_system` VALUES (108, '1.jpg', NULL, '2020-08-04 15:10:35', '\\f761515bf5844bc5967780c358de3e40\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/108', 1, NULL, NULL, '2020-08-04 15:10:35', '2020-08-04 15:10:35', NULL);
INSERT INTO `common_file_system` VALUES (109, '044001900411-98473570.pdf', NULL, '2020-08-05 08:58:51', '\\281db92947b7448dbd1f30943f1a938f\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/109', 1, NULL, NULL, '2020-08-05 08:58:51', '2020-08-05 08:58:51', NULL);
INSERT INTO `common_file_system` VALUES (110, '1.jpg', NULL, '2020-08-05 09:15:56', '\\7b5c484b1ae74aeb98672edc7fb3f51d\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/110', 1, NULL, NULL, '2020-08-05 09:15:56', '2020-08-05 09:15:56', NULL);
INSERT INTO `common_file_system` VALUES (111, '服务台.pdf', NULL, '2020-08-05 11:19:16', '\\bba9b87f2bd74cc699fd49f0161f6eac\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/111', 1, NULL, NULL, '2020-08-05 11:19:16', '2020-08-05 11:19:16', NULL);
INSERT INTO `common_file_system` VALUES (112, '服务台.pdf', NULL, '2020-08-05 11:19:22', '\\3e7fddd98240432e99ab5af0606c19ec\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/112', 1, NULL, NULL, '2020-08-05 11:19:22', '2020-08-05 11:19:22', NULL);
INSERT INTO `common_file_system` VALUES (113, '服务台.pdf', NULL, '2020-08-05 11:19:25', '\\e1202c3253334c4db0d127a2f194d0af\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/113', 1, NULL, NULL, '2020-08-05 11:19:25', '2020-08-05 11:19:25', NULL);
INSERT INTO `common_file_system` VALUES (114, '1.jpg', NULL, '2020-08-05 11:23:02', '\\257727b7397442bc8b92d865a3a77398\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/114', 1, NULL, NULL, '2020-08-05 11:23:02', '2020-08-05 11:23:02', NULL);
INSERT INTO `common_file_system` VALUES (115, '1.jpg', NULL, '2020-08-05 11:24:21', '\\764cf85d8b364d42becb3a8e66fa6752\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/115', 1, NULL, NULL, '2020-08-05 11:24:21', '2020-08-05 11:24:21', NULL);
INSERT INTO `common_file_system` VALUES (116, '服务台.pdf', NULL, '2020-08-05 11:26:04', '\\12530da1b9344a93804e22c06a2c852c\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/116', 1, NULL, NULL, '2020-08-05 11:26:04', '2020-08-05 11:26:04', NULL);
INSERT INTO `common_file_system` VALUES (117, '服务台.pdf', NULL, '2020-08-05 11:26:06', '\\3cc1af5615c540e6a2a4716a942c9101\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/117', 1, NULL, NULL, '2020-08-05 11:26:06', '2020-08-05 11:26:06', NULL);
INSERT INTO `common_file_system` VALUES (118, '服务台.pdf', NULL, '2020-08-05 11:26:24', '\\333bea71fef646c88c0309062894f919\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/118', 1, NULL, NULL, '2020-08-05 11:26:24', '2020-08-05 11:26:24', NULL);
INSERT INTO `common_file_system` VALUES (119, '服务台.pdf', NULL, '2020-08-05 11:31:03', '\\82aa6076e4c94282bfa5e9860789e734\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/119', 1, NULL, NULL, '2020-08-05 11:31:03', '2020-08-05 11:31:03', NULL);
INSERT INTO `common_file_system` VALUES (120, '服务台.pdf', NULL, '2020-08-05 11:35:20', '\\91722ee1ab294979bd0edba46afceea9\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/120', 1, NULL, NULL, '2020-08-05 11:35:20', '2020-08-05 11:35:20', NULL);
INSERT INTO `common_file_system` VALUES (121, '服务台.pdf', NULL, '2020-08-05 11:37:24', '\\3603441923ef4f02bbbd0ade2d92a26b\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/121', 1, NULL, NULL, '2020-08-05 11:37:24', '2020-08-05 11:37:24', NULL);
INSERT INTO `common_file_system` VALUES (122, '服务台.pdf', NULL, '2020-08-05 11:37:44', '\\f3e01646ca9145429a03667354297aa9\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/122', 1, NULL, NULL, '2020-08-05 11:37:44', '2020-08-05 11:37:44', NULL);
INSERT INTO `common_file_system` VALUES (123, '044001900411-98473570.pdf', NULL, '2020-08-05 11:44:02', '\\6592ca7416004b1da2d683e1d5915ee1\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/123', 1, NULL, NULL, '2020-08-05 11:44:02', '2020-08-05 11:44:02', NULL);
INSERT INTO `common_file_system` VALUES (124, '服务台.pdf', NULL, '2020-08-05 11:49:12', '\\b7598efedba248a08d30f7dd61e066e9\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/124', 1, NULL, NULL, '2020-08-05 11:49:12', '2020-08-05 11:49:12', NULL);
INSERT INTO `common_file_system` VALUES (125, '044001900411-98473570.pdf', NULL, '2020-08-05 11:53:43', '\\0d2e4dc6314b475580c79dd7e66d1918\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/125', 1, NULL, NULL, '2020-08-05 11:53:43', '2020-08-05 11:53:43', NULL);
INSERT INTO `common_file_system` VALUES (126, '044001900411-98473570.pdf', NULL, '2020-08-05 12:08:15', '\\9ee571ab18534c628e6c4006f2cec949\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/126', 1, NULL, NULL, '2020-08-05 12:08:15', '2020-08-05 12:08:15', NULL);
INSERT INTO `common_file_system` VALUES (127, '044001900411-98830786.pdf', NULL, '2020-08-05 12:11:32', '\\2bc56f7bd3394f3a9bf019ceed500861\\044001900411-98830786.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/127', 1, NULL, NULL, '2020-08-05 12:11:32', '2020-08-05 12:11:32', NULL);
INSERT INTO `common_file_system` VALUES (128, '044001900411-98473570.pdf', NULL, '2020-08-05 14:18:27', '\\b266dd69cd0b43fcaee1cabe2c62e4dd\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/128', 1, NULL, NULL, '2020-08-05 14:18:27', '2020-08-05 14:18:27', NULL);
INSERT INTO `common_file_system` VALUES (129, '044001900411-98830786.pdf', NULL, '2020-08-05 14:18:48', '\\55e2f3da0ced419aade1a4694162afac\\044001900411-98830786.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/129', 1, NULL, NULL, '2020-08-05 14:18:48', '2020-08-05 14:18:48', NULL);
INSERT INTO `common_file_system` VALUES (130, '044001900411-98473570.pdf', NULL, '2020-08-05 19:11:49', '\\7ec96e6b007845b78301b972c155d5d9\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/130', 1, NULL, NULL, '2020-08-05 19:11:49', '2020-08-05 19:11:49', NULL);
INSERT INTO `common_file_system` VALUES (131, '044001900411-98830786.pdf', NULL, '2020-08-05 19:15:59', '\\376ff1fe6d3b404aad8d2a950a25ab6d\\044001900411-98830786.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/131', 1, NULL, NULL, '2020-08-05 19:15:59', '2020-08-05 19:15:59', NULL);
INSERT INTO `common_file_system` VALUES (132, '044001900411-98830786.pdf', NULL, '2020-08-05 19:16:12', '\\de3e7513a77343e79fe09e83fa3683f7\\044001900411-98830786.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/132', 1, NULL, NULL, '2020-08-05 19:16:12', '2020-08-05 19:16:12', NULL);
INSERT INTO `common_file_system` VALUES (133, '044001900411-98830786.pdf', NULL, '2020-08-05 19:17:08', '\\eb60a73589d846379db4d2860306ff72\\044001900411-98830786.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/133', 1, NULL, NULL, '2020-08-05 19:17:08', '2020-08-05 19:17:08', NULL);
INSERT INTO `common_file_system` VALUES (134, '044001900411-99697948.pdf', NULL, '2020-08-05 19:17:51', '\\a93538d688364d67b3cdd374b96bcefc\\044001900411-99697948.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/134', 1, NULL, NULL, '2020-08-05 19:17:51', '2020-08-05 19:17:51', NULL);
INSERT INTO `common_file_system` VALUES (135, '044001900411-98473570.pdf', NULL, '2020-08-05 19:20:44', '\\361c5726d46a46a2a87153cdd6d52e02\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/135', 1, NULL, NULL, '2020-08-05 19:20:44', '2020-08-05 19:20:44', NULL);
INSERT INTO `common_file_system` VALUES (136, '044001900411-98473570.pdf', NULL, '2020-08-05 23:53:50', '\\f4f4c90b78f5480e9479d6705c62ae2a\\044001900411-98473570.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/136', 1, NULL, NULL, '2020-08-05 23:53:50', '2020-08-05 23:53:50', NULL);
INSERT INTO `common_file_system` VALUES (137, '044001900411-98830786.pdf', NULL, '2020-08-06 00:01:32', '\\ec2ebd870c654f1c86b9a4d60ed6c9e4\\044001900411-98830786.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/137', 1, NULL, NULL, '2020-08-06 00:01:32', '2020-08-06 00:01:32', NULL);
INSERT INTO `common_file_system` VALUES (138, '1.jpg', NULL, '2020-08-06 09:29:21', '\\bcd788ca4bf24407986c8efaf03eb6c8\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/138', 1, NULL, NULL, '2020-08-06 09:29:21', '2020-08-06 09:29:21', NULL);
INSERT INTO `common_file_system` VALUES (139, '服务台.pdf', NULL, '2020-08-06 09:29:43', '\\80ca109e17f54e5f98d46a548e147ea1\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/139', 1, NULL, NULL, '2020-08-06 09:29:43', '2020-08-06 09:29:43', NULL);
INSERT INTO `common_file_system` VALUES (140, '1.jpg', NULL, '2020-08-06 09:30:06', '\\57036504f0fc48bdb80862bd28ce1f09\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/140', 1, NULL, NULL, '2020-08-06 09:30:06', '2020-08-06 09:30:06', NULL);
INSERT INTO `common_file_system` VALUES (141, '1.jpg', NULL, '2020-08-06 09:31:31', '\\c840816587cc4b9a9a23674d545675aa\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/141', 1, NULL, NULL, '2020-08-06 09:31:31', '2020-08-06 09:31:31', NULL);
INSERT INTO `common_file_system` VALUES (142, '1.jpg', NULL, '2020-08-06 09:31:50', '\\e560ce56b44c42859be9bf69959087a4\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/142', 1, NULL, NULL, '2020-08-06 09:31:50', '2020-08-06 09:31:50', NULL);
INSERT INTO `common_file_system` VALUES (143, '1.jpg', NULL, '2020-08-06 09:34:13', '\\e1871b3538eb4a11a3cda1367b4dc053\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/143', 1, NULL, NULL, '2020-08-06 09:34:13', '2020-08-06 09:34:13', NULL);
INSERT INTO `common_file_system` VALUES (144, '1.jpg', NULL, '2020-08-06 09:34:21', '\\a3fde6d90b544528a189d30f6e4f5070\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/144', 1, NULL, NULL, '2020-08-06 09:34:21', '2020-08-06 09:34:21', NULL);
INSERT INTO `common_file_system` VALUES (145, '1.jpg', NULL, '2020-08-06 09:36:57', '\\42c14669a5f246d69adebf508bef1b74\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/145', 1, NULL, NULL, '2020-08-06 09:36:57', '2020-08-06 09:36:57', NULL);
INSERT INTO `common_file_system` VALUES (146, '1.jpg', NULL, '2020-08-06 09:38:10', '\\0fe26d3d02cb42569f305c1c2d2aba07\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/146', 1, NULL, NULL, '2020-08-06 09:38:10', '2020-08-06 09:38:10', NULL);
INSERT INTO `common_file_system` VALUES (147, '1.jpg', NULL, '2020-08-06 09:41:18', '\\7dd5192898cb434d8c4eb28ac38d6cf9\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/147', 1, NULL, NULL, '2020-08-06 09:41:18', '2020-08-06 09:41:18', NULL);
INSERT INTO `common_file_system` VALUES (148, '服务台.pdf', NULL, '2020-08-06 09:41:34', '\\7a3e89c4d58b418eb49a1535098ed514\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/148', 1, NULL, NULL, '2020-08-06 09:41:34', '2020-08-06 09:41:34', NULL);
INSERT INTO `common_file_system` VALUES (149, '044001900411-99100397.pdf', NULL, '2020-08-06 09:41:57', '\\1283cb21273b4a17a8a0b16c57a3b7c2\\044001900411-99100397.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/149', 1, NULL, NULL, '2020-08-06 09:41:57', '2020-08-06 09:41:57', NULL);
INSERT INTO `common_file_system` VALUES (150, '044001900411-99100397.pdf', NULL, '2020-08-06 09:44:52', '\\64e5fb899b764ecd8e05bc7e1a676715\\044001900411-99100397.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/150', 1, NULL, NULL, '2020-08-06 09:44:52', '2020-08-06 09:44:52', NULL);
INSERT INTO `common_file_system` VALUES (151, '1.jpg', NULL, '2020-08-06 09:49:44', '\\7883160a416f4506aa0dd0adba641d8a\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/151', 1, NULL, NULL, '2020-08-06 09:49:44', '2020-08-06 09:49:44', NULL);
INSERT INTO `common_file_system` VALUES (152, '服务台.pdf', NULL, '2020-08-06 09:49:54', '\\3a1937b5b23b44fe819798342ac3e117\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/152', 1, NULL, NULL, '2020-08-06 09:49:54', '2020-08-06 09:49:54', NULL);
INSERT INTO `common_file_system` VALUES (153, '1.jpg', NULL, '2020-08-06 09:50:10', '\\2698e31c3aec4d648c5fad4b4864192e\\1.jpg', 'jpg', NULL, 1, 0, NULL, NULL, '/file/download/153', 1, NULL, NULL, '2020-08-06 09:50:10', '2020-08-06 09:50:10', NULL);
INSERT INTO `common_file_system` VALUES (154, '服务台.pdf', NULL, '2020-08-06 09:50:19', '\\e5f84b2ec5b045f28b5547a13e44bc2a\\服务台.pdf', 'pdf', NULL, 1, 0, NULL, NULL, '/file/download/154', 1, NULL, NULL, '2020-08-06 09:50:19', '2020-08-06 09:50:19', NULL);

-- ----------------------------
-- Table structure for common_model_file
-- ----------------------------
DROP TABLE IF EXISTS `common_model_file`;
CREATE TABLE `common_model_file`  (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `company_record_id` int(15) NOT NULL,
  `company_file_Id` int(15) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of common_model_file
-- ----------------------------
INSERT INTO `common_model_file` VALUES (4, 132, 66);
INSERT INTO `common_model_file` VALUES (5, 133, 67);
INSERT INTO `common_model_file` VALUES (6, 133, 68);
INSERT INTO `common_model_file` VALUES (7, 132, 66);
INSERT INTO `common_model_file` VALUES (8, 155, 74);
INSERT INTO `common_model_file` VALUES (9, 155, 74);
INSERT INTO `common_model_file` VALUES (10, 133, 67);
INSERT INTO `common_model_file` VALUES (11, 133, 68);
INSERT INTO `common_model_file` VALUES (12, 187, 85);
INSERT INTO `common_model_file` VALUES (13, 187, 86);
INSERT INTO `common_model_file` VALUES (14, 187, 85);
INSERT INTO `common_model_file` VALUES (15, 187, 86);
INSERT INTO `common_model_file` VALUES (16, 192, 90);
INSERT INTO `common_model_file` VALUES (17, 193, 91);
INSERT INTO `common_model_file` VALUES (18, 194, 92);
INSERT INTO `common_model_file` VALUES (19, 192, 90);
INSERT INTO `common_model_file` VALUES (20, 193, 91);
INSERT INTO `common_model_file` VALUES (21, 194, 92);
INSERT INTO `common_model_file` VALUES (22, 209, 103);
INSERT INTO `common_model_file` VALUES (23, 209, 103);
INSERT INTO `common_model_file` VALUES (24, 304, 119);
INSERT INTO `common_model_file` VALUES (25, 304, 119);
INSERT INTO `common_model_file` VALUES (26, 321, 120);
INSERT INTO `common_model_file` VALUES (27, 328, 121);
INSERT INTO `common_model_file` VALUES (28, 328, 121);
INSERT INTO `common_model_file` VALUES (29, 339, 123);
INSERT INTO `common_model_file` VALUES (30, 339, 123);
INSERT INTO `common_model_file` VALUES (31, 354, 126);
INSERT INTO `common_model_file` VALUES (32, 354, 126);
INSERT INTO `common_model_file` VALUES (33, 363, 127);
INSERT INTO `common_model_file` VALUES (34, 363, 127);
INSERT INTO `common_model_file` VALUES (35, 406, 128);
INSERT INTO `common_model_file` VALUES (36, 406, 128);
INSERT INTO `common_model_file` VALUES (37, 463, 130);
INSERT INTO `common_model_file` VALUES (38, 463, 130);
INSERT INTO `common_model_file` VALUES (39, 476, 131);
INSERT INTO `common_model_file` VALUES (40, 476, 131);
INSERT INTO `common_model_file` VALUES (41, 491, 133);
INSERT INTO `common_model_file` VALUES (42, 491, 133);
INSERT INTO `common_model_file` VALUES (43, 508, 136);
INSERT INTO `common_model_file` VALUES (44, 508, 136);
INSERT INTO `common_model_file` VALUES (45, 530, 138);
INSERT INTO `common_model_file` VALUES (46, 531, 140);
INSERT INTO `common_model_file` VALUES (47, 532, 142);
INSERT INTO `common_model_file` VALUES (48, 534, 143);
INSERT INTO `common_model_file` VALUES (49, 541, 147);
INSERT INTO `common_model_file` VALUES (50, 542, 149);
INSERT INTO `common_model_file` VALUES (51, 543, 151);
INSERT INTO `common_model_file` VALUES (52, 544, 153);

-- ----------------------------
-- Table structure for common_right
-- ----------------------------
DROP TABLE IF EXISTS `common_right`;
CREATE TABLE `common_right`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `PARENT_ID` int(11) NOT NULL COMMENT '父编号',
  `RIGHT_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `SYS_TYPE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统类型',
  `RIGHT_CODE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `EMPLOYEE_ID` int(10) NULL DEFAULT NULL COMMENT '职员编号',
  `ENABLED` int(1) NULL DEFAULT NULL COMMENT '回收站',
  `REMARK` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_STAFF` int(10) NULL DEFAULT NULL COMMENT '创建职员',
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime(0) NULL DEFAULT NULL COMMENT '修改职员',
  `MODIFY_STAFF` int(10) NULL DEFAULT NULL COMMENT '修改日期',
  `RIGHT_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限路径',
  `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由图标',
  `COMPONENT` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由部件',
  `SORT_NUM` int(11) NULL DEFAULT NULL COMMENT '排序',
  `RESOURCE_TYPE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of common_right
-- ----------------------------
INSERT INTO `common_right` VALUES (2, 0, '首页', NULL, 'tableList', NULL, 1, NULL, NULL, NULL, NULL, NULL, '/sys', 'menu', 'Layout', 1, 'menu');
INSERT INTO `common_right` VALUES (3, 0, '用户管理', NULL, 'userManager', NULL, 1, NULL, NULL, NULL, NULL, NULL, '/sys', 's-custom', 'Layout', 1, 'menu');
INSERT INTO `common_right` VALUES (4, 2, '编辑', NULL, 'edit', NULL, 1, NULL, NULL, NULL, NULL, NULL, '/sys', 'example', 'Layout', 1, 'button');
INSERT INTO `common_right` VALUES (8, 0, '权限管理', NULL, 'userAuth', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 's-tools', NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (9, 0, '角色管理', NULL, 'userRole', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 's-help', NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (11, 2, '附件下载', NULL, 'download', NULL, 1, NULL, NULL, '2020-07-23 14:22:34', '2020-07-23 14:22:34', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (12, 2, '导出', NULL, 'export', NULL, 1, NULL, NULL, '2020-07-23 14:23:41', '2020-07-23 14:23:41', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (13, 3, '审核', NULL, 'audit', NULL, 1, NULL, NULL, '2020-07-23 14:24:31', '2020-07-23 14:24:31', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (14, 3, '关联角色', NULL, 'relation', NULL, 1, NULL, NULL, '2020-07-23 14:24:48', '2020-07-23 14:24:48', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (15, 8, '添加', NULL, 'create', NULL, 1, NULL, NULL, '2020-07-23 14:25:08', '2020-07-23 14:25:08', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (16, 8, '删除', NULL, 'delete', NULL, 1, NULL, NULL, '2020-07-23 14:25:17', '2020-07-23 14:25:17', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (17, 2, '删除', NULL, 'delete', NULL, 1, NULL, NULL, '2020-07-23 14:25:29', '2020-07-23 14:25:29', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (18, 9, '编辑', NULL, 'edit', NULL, 1, NULL, NULL, '2020-07-23 14:26:08', '2020-07-23 14:26:08', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (19, 9, '删除', NULL, 'delete', NULL, 1, NULL, NULL, '2020-07-23 14:26:30', '2020-07-23 14:26:30', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (20, 9, '关联权限', NULL, 'relation', NULL, 1, NULL, NULL, '2020-07-23 14:26:39', '2020-07-23 14:26:39', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (21, 2, '查看', NULL, 'list', NULL, 1, NULL, NULL, '2020-07-23 14:27:59', '2020-07-23 14:27:59', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (22, 3, '查看', NULL, 'list', NULL, 1, NULL, NULL, '2020-07-23 14:28:12', '2020-07-23 14:28:12', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (23, 8, '查看', NULL, 'list', NULL, 1, NULL, NULL, '2020-07-23 14:28:20', '2020-07-23 14:28:20', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (24, 9, '查看', NULL, 'list', NULL, 1, NULL, NULL, '2020-07-23 14:28:27', '2020-07-23 14:28:27', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (25, 0, '消息列表', NULL, 'notifyCenter', NULL, 1, NULL, NULL, '2020-08-04 15:33:41', '2020-08-04 15:33:44', NULL, NULL, 'message-solid', NULL, NULL, NULL);
INSERT INTO `common_right` VALUES (26, 25, '查看', NULL, 'see', NULL, 1, NULL, NULL, '2020-08-04 15:34:42', '2020-08-04 15:34:42', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for common_role
-- ----------------------------
DROP TABLE IF EXISTS `common_role`;
CREATE TABLE `common_role`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `PARENT_ID` int(11) NULL DEFAULT NULL COMMENT '父编号',
  `ROLE_NAME` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `ROLE_CODE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色代号',
  `ORDER_TYPE_ID` int(11) NULL DEFAULT NULL,
  `EMPLOYEE_ID` int(10) NULL DEFAULT NULL COMMENT '职员编号',
  `ENABLED` int(1) NULL DEFAULT NULL COMMENT '回收站',
  `REMARK` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_STAFF` int(10) NULL DEFAULT NULL COMMENT '创建职员',
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime(0) NULL DEFAULT NULL COMMENT '修改职员',
  `MODIFY_STAFF` int(10) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of common_role
-- ----------------------------
INSERT INTO `common_role` VALUES (1, NULL, '系统管理员', 'admin', NULL, NULL, 1, '系统管理员', NULL, NULL, NULL, NULL);
INSERT INTO `common_role` VALUES (2, NULL, '开发者', 'dev', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role` VALUES (4, NULL, '普通用户', 'publicUser', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role` VALUES (8, NULL, '普通用户2', 'test02', NULL, NULL, 1, '', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for common_role_right_relationship
-- ----------------------------
DROP TABLE IF EXISTS `common_role_right_relationship`;
CREATE TABLE `common_role_right_relationship`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `RIGHT_TYPE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限类型',
  `COM_RIGHT_ID` int(11) NULL DEFAULT NULL COMMENT 'FK_COMMON_RIGHT',
  `COM_ROLE_ID` int(11) NULL DEFAULT NULL COMMENT 'FK_COMMON_ROLE',
  `EMPLOYEE_ID` int(10) NULL DEFAULT NULL COMMENT '职员编号',
  `ENABLED` int(1) NULL DEFAULT NULL COMMENT '回收站',
  `REMARK` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_STAFF` int(10) NULL DEFAULT NULL COMMENT '创建职员',
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime(0) NULL DEFAULT NULL COMMENT '修改职员',
  `MODIFY_STAFF` int(10) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `fk_com_role_right_relationship_com_right1_idx`(`COM_RIGHT_ID`) USING BTREE,
  INDEX `fk_com_role_right_relationship_com_role1_idx`(`COM_ROLE_ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of common_role_right_relationship
-- ----------------------------
INSERT INTO `common_role_right_relationship` VALUES (5, '1', 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (6, '1', 3, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (8, '1', 8, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (9, '1', 9, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (14, '1', 15, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (15, '1', 16, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (19, '1', 20, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (20, '1', 21, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (21, '1', 22, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (22, '1', 23, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (23, '1', 24, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (24, '1', 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (26, '1', 21, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (33, '1', 11, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (34, '1', 12, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (35, '1', 17, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (36, '1', 13, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (37, '1', 14, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (38, '1', 18, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (39, '1', 19, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (40, '1', 2, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (41, '1', 21, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (42, '1', 3, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (43, '1', 22, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (44, '1', 23, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (45, '1', 24, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (46, '1', 8, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (47, '1', 9, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (48, '1', 2, 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (49, '1', 3, 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (50, '1', 4, 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (51, '1', 22, 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (52, '1', 4, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (53, '1', 25, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_role_right_relationship` VALUES (54, '1', 26, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for common_user
-- ----------------------------
DROP TABLE IF EXISTS `common_user`;
CREATE TABLE `common_user`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `USER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `HASH_PASSWORD` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `SECRET` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密钥',
  `EMAIL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `LOGIN_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆名称',
  `LOGIN_TIME` datetime(0) NULL DEFAULT NULL COMMENT '登陆时间',
  `LAST_LOGINTIME` datetime(0) NULL DEFAULT NULL COMMENT '最后登陆',
  `COUNT` int(10) NULL DEFAULT NULL COMMENT '次数',
  `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NICK_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `WE_CHAT_OPENID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号标识',
  `WE_CHAT_STATUS` int(11) NULL DEFAULT NULL COMMENT '微信号核对状态 1:已核对 2:未核对',
  `EMPLOYEE_ID` int(10) NULL DEFAULT NULL COMMENT '职员编号',
  `ENABLED` int(1) NULL DEFAULT NULL COMMENT '回收站',
  `REMARK` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_STAFF` int(10) NULL DEFAULT NULL COMMENT '创建职员',
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime(0) NULL DEFAULT NULL COMMENT '修改职员',
  `MODIFY_STAFF` int(10) NULL DEFAULT NULL COMMENT '修改日期',
  `HEAD_IMG` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `WE_CHAT_USER` int(1) NULL DEFAULT 2 COMMENT '微信用户 1:微信端用户 2:非微信端用户',
  `BELONG_COMPANY` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属单位',
  `DEPARTMENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `EXTERNAL_ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'idaas,外部id',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `expir_date` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `USER_NAME_UNIQUE`(`USER_NAME`) USING BTREE,
  UNIQUE INDEX `EMPLOYEE_ID_UNIQUE`(`EMPLOYEE_ID`) USING BTREE,
  UNIQUE INDEX `MOBILE_UNIQUE`(`MOBILE`) USING BTREE,
  UNIQUE INDEX `EMAIL_UNIQUE`(`EMAIL`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of common_user
-- ----------------------------
INSERT INTO `common_user` VALUES (1, 'admin', 'admin', NULL, NULL, 'admin', '2020-07-23 15:24:12', NULL, NULL, NULL, 'admin', NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, '1', '2020-09-02 15:15:27');
INSERT INTO `common_user` VALUES (2, 'pride', '123123', NULL, NULL, 'pride', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-09-23 15:15:31');
INSERT INTO `common_user` VALUES (3, 'flame', 'flame', NULL, NULL, 'flame', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', '2020-09-23 15:15:34');

-- ----------------------------
-- Table structure for common_user_role_relationship
-- ----------------------------
DROP TABLE IF EXISTS `common_user_role_relationship`;
CREATE TABLE `common_user_role_relationship`  (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `COM_USER_ID` int(11) NULL DEFAULT NULL COMMENT 'FK_COMMON_USER',
  `COM_ROLE_ID` int(11) NULL DEFAULT NULL COMMENT 'FK_COMMON_ROLE',
  `EMPLOYEE_ID` int(10) NULL DEFAULT NULL COMMENT '职员编号',
  `ENABLED` int(1) NULL DEFAULT NULL COMMENT '回收站',
  `REMARK` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CREATE_STAFF` int(10) NULL DEFAULT NULL COMMENT '创建职员',
  `CREATE_DATE` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `MODIFY_DATE` datetime(0) NULL DEFAULT NULL COMMENT '修改职员',
  `MODIFY_STAFF` int(10) NULL DEFAULT NULL COMMENT '修改日期',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of common_user_role_relationship
-- ----------------------------
INSERT INTO `common_user_role_relationship` VALUES (1, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user_role_relationship` VALUES (3, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user_role_relationship` VALUES (4, 324, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user_role_relationship` VALUES (5, 324, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user_role_relationship` VALUES (6, 330, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user_role_relationship` VALUES (7, 330, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user_role_relationship` VALUES (8, 330, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user_role_relationship` VALUES (9, 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user_role_relationship` VALUES (10, 2, 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user_role_relationship` VALUES (11, 3, 8, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位名称',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `store_area` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区域',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位地址',
  `legal_representative` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法定代表人',
  `organization_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码',
  `environmental_protection_officer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保负责人',
  `contact_number` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `industry_category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业类别',
  `industry_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业代码',
  `industry_dept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业主管部门',
  `completion_date` datetime(0) NULL DEFAULT NULL COMMENT '建成时间',
  `fixed_assets` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定资产（万元）',
  `envir_prot_fixed_assets` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保设施固定资产（万元）',
  `enterprise_size` int(15) NULL DEFAULT NULL COMMENT '企业规模：1大型/2中型/3小型；备注：用枚举',
  `pollution_source_management_level` int(15) NULL DEFAULT NULL COMMENT '污染源管理级别：1国控/2市控/3区控/4一般；备注：用枚举',
  `sewage_type` int(15) NULL DEFAULT NULL COMMENT '排污种类：1废水/废气/噪声/固废/其它',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `general_industrial_solid_waste` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一般工业固体废物',
  `year_production` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年产生量',
  `year_process` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年处理量',
  `disposal_way_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处置方式第三方',
  `disposal_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处置方式',
  `main_sound_source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要声源名称',
  `mmppc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '污染防治的主要措施',
  `sewerage_rain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '雨污分流',
  `row_to_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `row_to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排向',
  `enterprise_pretreatment_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业预处理备注',
  `enterprise_pretreatment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业预处理',
  `output` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产生量（月平均）',
  `the_sewage_to_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '污水排向说明',
  `the_sewage_to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '污水排向',
  `stfds` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活污水化粪池',
  `life_produced` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活产生量（月平均）',
  `life_line_to_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活排向说明',
  `life_line_to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活排向',
  `environmental_protection_plan_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保应急预案情况备注',
  `environmental_protection_plan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保应急预案情况',
  `emission_permit_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排污许可证备注',
  `emission_permit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排污许可证',
  `eia_process_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环评工艺备注',
  `eia_process` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环评工艺',
  `new_eia_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `new_eia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新环评',
  `epep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `epep_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保应急预案情况备注',
  `supervisory_inspection_enterprise_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监督性检测企业备注',
  `supervisory_inspection_enterprise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监督性检测企业',
  `sicfwwo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '废水监督性检测周期',
  `sicfwwt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '废气监督性检测周期',
  `sicfwws` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '土壤监督性检测周期',
  `number_employees` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业在职人数',
  `eia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环评',
  `official_reply_date` datetime(0) NULL DEFAULT NULL COMMENT '环评批复时间',
  `official_reply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环评批复',
  `official_reply_file_id` int(15) NULL DEFAULT NULL COMMENT '环评批复文件Id',
  `breaks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产工艺描述',
  `official_time` datetime(0) NULL DEFAULT NULL COMMENT '环评时间',
  `mouth_inflow` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '月进水量（平均）',
  `swage_generation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '污水产生量',
  `consumption` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消耗量',
  `water_balance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '水平衡',
  `sewerage_rain_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '雨污分流备注',
  `record_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '重点工业企业基本情况表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES (30, '大横琴科技', NULL, '香洲', '横琴', 'flame', '10010', 'pride', '12345678912', '科技', '202020', '行政部', '2020-07-15 00:00:00', '10', '20', 2, 2, NULL, NULL, NULL, '一般工业固体废物A', '1020', '2010', NULL, '2', '汽车', '隔音棉', '1', '', '1', '123123', '1', '1212', '我是其他', '3', '12', '123', '123123', '3', '12312', '1', '123123', '1', '123123', '1', '123123', '1', '1', '2123', '121', '2', '2', '2', '2', '1000', '1', NULL, '1', 78, '生产工艺描述1', NULL, '12', '12', '12', '1', '123123', '2');
INSERT INTO `company_info` VALUES (32, '电讯盈科', NULL, NULL, '123', '', '', '', '', '', '', '', NULL, '', '', NULL, NULL, NULL, NULL, NULL, '', '', '', NULL, '', '', '', '', '', '', NULL, '', '', '', '', '', '', '', '', NULL, '', NULL, '', NULL, '', NULL, '', NULL, '', NULL, '', '', '', '', '', '', NULL, '', NULL, '', NULL, '', '', '', '', '', '2');
INSERT INTO `company_info` VALUES (33, 'AAA', NULL, NULL, 'BBB', '', '', '', '', '', '', '', NULL, '', '', NULL, NULL, NULL, '2020-07-30 09:57:24', NULL, '', '', '', NULL, '', '', '', '', '', '', NULL, '', '', '', '', '', '', '', '', NULL, '', NULL, '', NULL, '', NULL, '', NULL, '', NULL, '', '', '', '', '', '', NULL, '', NULL, '', NULL, '', '', '', '', '', '1');
INSERT INTO `company_info` VALUES (34, '', NULL, NULL, '', '', '', '', '', '', '', '', NULL, '', '', NULL, NULL, NULL, '2020-07-30 16:04:38', NULL, '', '', '', NULL, '', '', '', '', '', '', NULL, '', '', '', '', '', '', '', '', NULL, '', NULL, '', NULL, '', NULL, '', NULL, '', NULL, '', '', '', '', '', '', NULL, '', NULL, '', NULL, '', '', '', '', '', '2');

-- ----------------------------
-- Table structure for company_product
-- ----------------------------
DROP TABLE IF EXISTS `company_product`;
CREATE TABLE `company_product`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `number` int(15) NULL DEFAULT NULL COMMENT '产品产量数量',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品产量（计量单位）',
  `main_materials_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要原辅材料名称',
  `major_pollutants` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要污染物',
  `company_id` int(1) NULL DEFAULT NULL COMMENT '公司ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 199 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主要产品生产情况表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company_product
-- ----------------------------
INSERT INTO `company_product` VALUES (111, '', 12312, '1', '', '', 34);
INSERT INTO `company_product` VALUES (156, '11', 22, '兆', '33', '44', 33);
INSERT INTO `company_product` VALUES (157, '22', 33, '1', '44', '55', 33);
INSERT INTO `company_product` VALUES (197, '', NULL, NULL, '', '', 32);
INSERT INTO `company_product` VALUES (198, '地方阿斯蒂芬阿斯蒂芬阿斯蒂芬阿斯蒂芬撒旦法撒旦法', NULL, '1', '1', '1', 30);

-- ----------------------------
-- Table structure for company_project
-- ----------------------------
DROP TABLE IF EXISTS `company_project`;
CREATE TABLE `company_project`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `monitor_project` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监测项目',
  `monitor_index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监测指标',
  `test_item` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测指标',
  `test_time` datetime(0) NULL DEFAULT NULL COMMENT '检测时间',
  `company_id` int(1) NULL DEFAULT NULL COMMENT '公司ID',
  `waste_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监测类型（1废水/2废气）',
  `monitor_file_id` int(15) NULL DEFAULT NULL COMMENT '监测文件Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 509 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监测项目表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company_project
-- ----------------------------
INSERT INTO `company_project` VALUES (188, '', '', '', NULL, 34, '1', NULL);
INSERT INTO `company_project` VALUES (189, '', '', '', NULL, 34, '2', NULL);
INSERT INTO `company_project` VALUES (351, '', '', '', NULL, 33, '1', NULL);
INSERT INTO `company_project` VALUES (352, '', '', '', NULL, 33, '2', NULL);
INSERT INTO `company_project` VALUES (503, '1', '1', '1', '2020-07-20 00:00:00', 32, '1', 47);
INSERT INTO `company_project` VALUES (504, '1', '1', '1', '2020-07-27 00:00:00', 32, '2', NULL);
INSERT INTO `company_project` VALUES (505, '1', '1', '1', '2020-07-20 00:00:00', 30, '1', 82);
INSERT INTO `company_project` VALUES (506, '3', '3', '3', NULL, 30, '1', 81);
INSERT INTO `company_project` VALUES (507, '1', '1', '1', '2020-07-27 00:00:00', 30, '2', 83);
INSERT INTO `company_project` VALUES (508, '2', '2', '2', NULL, 30, '2', 84);

-- ----------------------------
-- Table structure for company_record
-- ----------------------------
DROP TABLE IF EXISTS `company_record`;
CREATE TABLE `company_record`  (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `company_id` int(15) NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `file_url` int(15) NULL DEFAULT NULL,
  `record_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '记录类型:1巡查执法记录/2行政处罚记录',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `complete_date` datetime(0) NULL DEFAULT NULL COMMENT '完成时间',
  `complete_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '完成时的描述',
  `complete_file_id` int(15) NULL DEFAULT NULL COMMENT '完成时的文件',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 546 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '记录类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_record
-- ----------------------------
INSERT INTO `company_record` VALUES (411, 32, '是的法规', NULL, '1', '2020-08-05 14:36:15', '2020-08-05 14:26:39', '是的法规', NULL, '1');
INSERT INTO `company_record` VALUES (412, 32, '阿斯蒂芬', NULL, '1', '2020-08-05 14:36:15', '2020-08-05 14:36:20', '撒旦法', NULL, '1');
INSERT INTO `company_record` VALUES (432, 33, '请问请问', NULL, '1', '2020-08-05 15:30:29', '2020-08-04 00:00:00', NULL, NULL, '0');
INSERT INTO `company_record` VALUES (527, 30, '1212311111', NULL, '2', '2020-08-06 00:03:58', '2020-08-04 15:28:31', '123', NULL, '1');
INSERT INTO `company_record` VALUES (528, 30, '123123', NULL, '2', '2020-08-06 00:03:58', '2020-08-05 11:10:40', '阿斯蒂芬', NULL, '1');
INSERT INTO `company_record` VALUES (529, 30, '123321', NULL, '2', '2020-08-06 00:03:58', '2020-08-27 00:00:00', NULL, NULL, '0');
INSERT INTO `company_record` VALUES (539, 30, '1', NULL, '1', '2020-08-06 09:38:03', '2020-08-06 09:38:11', '123', 146, '1');
INSERT INTO `company_record` VALUES (540, 30, '2', NULL, '1', '2020-08-06 09:38:19', '2020-08-06 09:38:22', '2', NULL, '1');
INSERT INTO `company_record` VALUES (541, 30, '3', NULL, '1', '2020-08-06 09:41:20', '2020-08-06 09:41:35', '3', 148, '1');
INSERT INTO `company_record` VALUES (542, 30, '123123', NULL, '2', '2020-08-06 09:42:01', '2020-08-06 09:44:54', '123123123', 150, '1');
INSERT INTO `company_record` VALUES (543, 32, '1', NULL, '2', '2020-08-06 09:49:49', '2020-08-06 09:49:55', '12', 152, '1');
INSERT INTO `company_record` VALUES (544, 32, '2', NULL, '2', '2020-08-06 09:50:14', '2020-08-06 09:50:21', '123', 154, '1');
INSERT INTO `company_record` VALUES (545, 30, '1', NULL, '1', '2020-08-06 09:50:46', '2020-08-03 00:00:00', NULL, NULL, '0');

-- ----------------------------
-- Table structure for company_sewage_waste
-- ----------------------------
DROP TABLE IF EXISTS `company_sewage_waste`;
CREATE TABLE `company_sewage_waste`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `craft` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工艺',
  `wu_yuan_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '污源物',
  `environmental_protection_facilities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保治理设施',
  `company_id` int(1) NULL DEFAULT NULL COMMENT '公司ID',
  `drain_outlet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排污口',
  `waste_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 689 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '排污种类' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company_sewage_waste
-- ----------------------------
INSERT INTO `company_sewage_waste` VALUES (286, '', '', '', '', 34, '', '1');
INSERT INTO `company_sewage_waste` VALUES (287, '', '', '', '', 34, '', '2');
INSERT INTO `company_sewage_waste` VALUES (494, '', '', '', '', 33, '', '1');
INSERT INTO `company_sewage_waste` VALUES (495, '', '', '', '', 33, '', '2');
INSERT INTO `company_sewage_waste` VALUES (682, '1', '1', '1', '1', 32, '1', '1');
INSERT INTO `company_sewage_waste` VALUES (683, '1', '1', '1', '1', 32, '1', '2');
INSERT INTO `company_sewage_waste` VALUES (684, '1', '1', '1', '1', 30, '1', '1');
INSERT INTO `company_sewage_waste` VALUES (685, '2', '2', '2', '2', 30, '2', '1');
INSERT INTO `company_sewage_waste` VALUES (686, '3', '3', '3', '3', 30, '3', '1');
INSERT INTO `company_sewage_waste` VALUES (687, '1', '1', '1', '1', 30, '1', '2');
INSERT INTO `company_sewage_waste` VALUES (688, 'a', 'a', 'a', 'a', 30, '啊', '2');

-- ----------------------------
-- Table structure for company_waste
-- ----------------------------
DROP TABLE IF EXISTS `company_waste`;
CREATE TABLE `company_waste`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称与类别',
  `year_pro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年生成量',
  `process_methods_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处置方式第三方说明',
  `process_methods` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '处理方式',
  `year_process` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '年处理量',
  `company_id` int(11) NULL DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 217 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of company_waste
-- ----------------------------
INSERT INTO `company_waste` VALUES (131, '', '', '', '', '', 34);
INSERT INTO `company_waste` VALUES (175, '', '', '', '', '', 33);
INSERT INTO `company_waste` VALUES (215, '', '', '', '', '', 32);
INSERT INTO `company_waste` VALUES (216, '1', '1', '1', '1', '1', 30);

-- ----------------------------
-- Table structure for customer_notice
-- ----------------------------
DROP TABLE IF EXISTS `customer_notice`;
CREATE TABLE `customer_notice`  (
  `id` int(15) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `send_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送人员',
  `receiver_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '接收人员',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送内容',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `enabled` int(1) NULL DEFAULT NULL,
  `record_id` int(15) NULL DEFAULT NULL,
  `is_read` int(15) NULL DEFAULT NULL COMMENT '是否已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_notice
-- ----------------------------
INSERT INTO `customer_notice` VALUES (17, '巡查记录审批完成提示', '', '', '2020-08-05 14:52:30', '2020-08-05 14:52:30', '【大横琴科技】的巡查执法记录【418】已超过完成时间，请及时审批', NULL, 1, 418, 1);
INSERT INTO `customer_notice` VALUES (20, '巡查记录审批完成提示', '', '', '2020-08-05 15:03:00', '2020-08-05 15:03:00', '【大横琴科技】的巡查执法记录【427】已超过完成时间，请及时审批', NULL, 1, 427, 1);
INSERT INTO `customer_notice` VALUES (21, '巡查记录审批完成提示', '', '', '2020-08-05 15:30:30', '2020-08-05 15:30:30', '【AAA】的巡查执法记录【432】已超过完成时间，请及时审批', NULL, 1, 432, 1);
INSERT INTO `customer_notice` VALUES (22, '巡查记录审批完成提示', '', '', '2020-08-05 15:32:00', '2020-08-05 15:32:00', '【大横琴科技】的巡查执法记录【438】已超过完成时间，请及时审批', NULL, 1, 438, 1);
INSERT INTO `customer_notice` VALUES (23, '巡查记录审批完成提示', '', '', '2020-08-05 15:32:00', '2020-08-05 15:32:00', '【大横琴科技】的巡查执法记录【439】已超过完成时间，请及时审批', NULL, 1, 439, 1);
INSERT INTO `customer_notice` VALUES (24, '巡查记录审批完成提示', '', '', '2020-08-05 15:43:30', '2020-08-05 15:43:30', '【大横琴科技】的巡查执法记录【454】已超过完成时间，请及时审批', NULL, 1, 454, 1);
INSERT INTO `customer_notice` VALUES (25, '巡查记录审批完成提示', '', '', '2020-08-05 19:12:00', '2020-08-05 19:12:00', '【大横琴科技】的巡查执法记录【462】已超过完成时间，请及时审批', NULL, 1, 462, 1);
INSERT INTO `customer_notice` VALUES (26, '巡查记录审批完成提示', '', '', '2020-08-05 19:12:00', '2020-08-05 19:12:00', '【大横琴科技】的巡查执法记录【463】已超过完成时间，请及时审批', NULL, 1, 463, 1);
INSERT INTO `customer_notice` VALUES (27, '巡查记录审批完成提示', '', '', '2020-08-05 19:15:30', '2020-08-05 19:15:30', '【大横琴科技】的巡查执法记录【468】已超过完成时间，请及时审批', NULL, 1, 468, 1);
INSERT INTO `customer_notice` VALUES (28, '巡查记录审批完成提示', '', '', '2020-08-05 19:16:30', '2020-08-05 19:16:30', '【大横琴科技】的巡查执法记录【481】已超过完成时间，请及时审批', NULL, 1, 481, 1);
INSERT INTO `customer_notice` VALUES (29, '巡查记录审批完成提示', '', '', '2020-08-05 19:18:00', '2020-08-05 19:18:00', '【大横琴科技】的巡查执法记录【496】已超过完成时间，请及时审批', NULL, 1, 496, 1);
INSERT INTO `customer_notice` VALUES (30, '巡查记录审批完成提示', '', '', '2020-08-05 23:54:00', '2020-08-05 23:54:00', '【大横琴科技】的巡查执法记录【504】已超过完成时间，请及时审批', NULL, 1, 504, 1);
INSERT INTO `customer_notice` VALUES (31, '巡查记录审批完成提示', '', '', '2020-08-05 23:54:00', '2020-08-05 23:54:00', '【大横琴科技】的巡查执法记录【508】已超过完成时间，请及时审批', NULL, 1, 508, 1);
INSERT INTO `customer_notice` VALUES (32, '巡查记录审批完成提示', '', '', '2020-08-06 00:03:00', '2020-08-06 00:03:00', '【大横琴科技】的巡查执法记录【513】已超过完成时间，请及时审批', NULL, 1, 513, 1);
INSERT INTO `customer_notice` VALUES (35, '巡查记录审批完成提示', '', '', '2020-08-06 09:51:00', '2020-08-06 09:51:00', '【大横琴科技】的巡查执法记录【545】已超过完成时间，请及时审批', NULL, 1, 545, 0);

-- ----------------------------
-- Table structure for sewage_waste_project
-- ----------------------------
DROP TABLE IF EXISTS `sewage_waste_project`;
CREATE TABLE `sewage_waste_project`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `monitor_project` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监测项目',
  `monitor_index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监测指标',
  `test_item` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测指标',
  `test_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测时间',
  `sewage_waste_id` int(1) NULL DEFAULT NULL COMMENT '公司排污种类废气ID',
  `waste_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '排污种类检测项目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sewage_waste_project
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
