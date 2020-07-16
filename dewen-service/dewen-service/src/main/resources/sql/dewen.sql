/*
 Navicat Premium Data Transfer

 Source Server         : localhost_db
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : dewen

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 16/07/2020 15:45:24
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
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件系统表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `USER_NAME_UNIQUE`(`USER_NAME`) USING BTREE,
  UNIQUE INDEX `EMPLOYEE_ID_UNIQUE`(`EMPLOYEE_ID`) USING BTREE,
  UNIQUE INDEX `MOBILE_UNIQUE`(`MOBILE`) USING BTREE,
  UNIQUE INDEX `EMAIL_UNIQUE`(`EMAIL`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 331 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of common_user
-- ----------------------------
INSERT INTO `common_user` VALUES (1, 'hailin', 'hailin', NULL, NULL, 'hailin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL, '1');
INSERT INTO `common_user` VALUES (324, 'pride', NULL, NULL, NULL, 'pride', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user` VALUES (326, 'flame', NULL, NULL, NULL, 'flame', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '0');
INSERT INTO `common_user` VALUES (328, 'user1', NULL, NULL, NULL, 'user1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user` VALUES (329, 'pride1', '123', NULL, NULL, 'pride1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `common_user` VALUES (330, 'admin', 'admin', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1');

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
) ENGINE = InnoDB AUTO_INCREMENT = 629 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company_info
-- ----------------------------
DROP TABLE IF EXISTS `company_info`;
CREATE TABLE `company_info`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位名称',
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
  `enterprise_pretreatment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业预处理',
  `output` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产生量（月平均）',
  `the_sewage_to_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '污水排向说明',
  `the_sewage_to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '污水排向',
  `stfds` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活污水化粪池',
  `life_produced` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活产生量（月平均）',
  `life_line_to_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活排向说明',
  `life_line_to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活排向',
  `environmental_protection_plan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保应急预案情况',
  `emission_permit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排污许可证',
  `eia_process` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环评工艺',
  `new_eia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新环评',
  `epep` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保应急预案情况',
  `supervisory_inspection_enterprise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监督性检测企业',
  `sicfwwo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '废水监督性检测周期',
  `sicfwwt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '废气监督性检测周期',
  `sicfwws` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '土壤监督性检测周期',
  `number_employees` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业在职人数',
  `eia` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环评',
  `official_reply` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环评批复',
  `official_reply_file_id` int(15) NULL DEFAULT NULL COMMENT '环评批复文件Id',
  `breaks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产工艺描述',
  `official_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环评时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '重点工业企业基本情况表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES (3, '大横琴科技AACC', '横琴', 'flame', '10010', 'pride', '12345678912', '科技', '202020', '行政部', '2020-07-15 00:00:00', '10', '20', 2, 2, NULL, NULL, NULL, '一般工业固体废物A', '1020', '2010', NULL, '2', '汽车', '隔音棉', '2', '', '1', '1', '1212', '我是其他', '3', '12', '123', '', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '1000', '1', '1', 11, '生产工艺描述', '2020-07-14T16:00:00.000Z');

-- ----------------------------
-- Table structure for company_product
-- ----------------------------
DROP TABLE IF EXISTS `company_product`;
CREATE TABLE `company_product`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品产量（计量单位）',
  `main_materials_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要原辅材料名称',
  `major_pollutants` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要污染物',
  `company_id` int(1) NULL DEFAULT NULL COMMENT '公司ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主要产品生产情况表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_product
-- ----------------------------
INSERT INTO `company_product` VALUES (31, 'A', 'A1', 'A2', 'A3', NULL);
INSERT INTO `company_product` VALUES (32, 'B', 'B1', 'B2', 'B3', NULL);
INSERT INTO `company_product` VALUES (33, 'A', 'A1', 'A2', 'A3', NULL);
INSERT INTO `company_product` VALUES (34, 'B', 'B1', 'B2', 'B3', NULL);
INSERT INTO `company_product` VALUES (35, 'A', 'A1', 'A2', 'A3', NULL);
INSERT INTO `company_product` VALUES (36, 'B', 'B1', 'B2', 'B3', NULL);
INSERT INTO `company_product` VALUES (37, 'A', 'A1', 'A2', 'A3', NULL);
INSERT INTO `company_product` VALUES (38, 'B', 'B1', 'B2', 'B3', NULL);
INSERT INTO `company_product` VALUES (39, 'A', 'A1', 'A2', 'A3', NULL);
INSERT INTO `company_product` VALUES (40, 'B', 'B1', 'B2', 'B3', NULL);
INSERT INTO `company_product` VALUES (41, 'A', 'A1', 'A2', 'A3', NULL);
INSERT INTO `company_product` VALUES (42, 'B', 'B1', 'B2', 'B3', NULL);
INSERT INTO `company_product` VALUES (43, 'A', 'A1', 'A2', 'A3', NULL);
INSERT INTO `company_product` VALUES (44, 'B', 'B1', 'B2', 'B3', NULL);
INSERT INTO `company_product` VALUES (45, '123', '222', '222', '222', NULL);
INSERT INTO `company_product` VALUES (46, '123', '222', '222', '222', NULL);
INSERT INTO `company_product` VALUES (47, 'eoms', '100', '111', '111', NULL);
INSERT INTO `company_product` VALUES (48, 'eoms11', '11', '123', '13123', NULL);
INSERT INTO `company_product` VALUES (49, 'eoms', '100', '1000', '1000', 3);
INSERT INTO `company_product` VALUES (50, 'eoms111', '102', '1111', '2222', 3);

-- ----------------------------
-- Table structure for company_project
-- ----------------------------
DROP TABLE IF EXISTS `company_project`;
CREATE TABLE `company_project`  (
  `id` int(15) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `monitor_project` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监测项目',
  `monitor_index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监测指标',
  `test_item` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测指标',
  `test_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测时间',
  `company_id` int(1) NULL DEFAULT NULL COMMENT '公司ID',
  `waste_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监测类型（1废水/2废气）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监测项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_project
-- ----------------------------
INSERT INTO `company_project` VALUES (66, 'E', 'E1', 'E2', 'E3', 3, '1');
INSERT INTO `company_project` VALUES (67, 'H1', 'H2', 'H3', 'H4', 3, '2');
INSERT INTO `company_project` VALUES (68, 'H1', 'H2', 'H3', 'H4', 3, '2');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '记录类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_record
-- ----------------------------
INSERT INTO `company_record` VALUES (55, 3, '巡查执法记录', NULL, '1', '2020-07-16 15:31:02');
INSERT INTO `company_record` VALUES (56, 3, '', 27, '1', '2020-07-16 15:31:04');

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
) ENGINE = InnoDB AUTO_INCREMENT = 139 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '排污种类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_sewage_waste
-- ----------------------------
INSERT INTO `company_sewage_waste` VALUES (133, 'C', 'C1', 'C2', 'C3', 3, 'C4', '1');
INSERT INTO `company_sewage_waste` VALUES (134, 'D', 'D1', 'D2', 'D3', 3, 'D4', '1');
INSERT INTO `company_sewage_waste` VALUES (135, 'C', 'C1', 'C2', 'C3', 3, 'C4', '1');
INSERT INTO `company_sewage_waste` VALUES (136, 'D', 'D1', 'D2', 'D3', 3, 'D4', '1');
INSERT INTO `company_sewage_waste` VALUES (137, 'G1', 'G2', 'G3', 'G4', 3, 'G5', '2');
INSERT INTO `company_sewage_waste` VALUES (138, 'G1', 'G2', 'G3', 'G4', 3, 'G5', '2');

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
) ENGINE = InnoDB AUTO_INCREMENT = 83 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_waste
-- ----------------------------
INSERT INTO `company_waste` VALUES (31, '工业危险废物名称和类别A', '1', '', '2', '2', 18);
INSERT INTO `company_waste` VALUES (32, '工业危险废物名称和类别B', '4', '', '4', '5', 18);
INSERT INTO `company_waste` VALUES (81, '工业危险废物名称和类别A', '1', '', '2', '2', 3);
INSERT INTO `company_waste` VALUES (82, '工业危险废物名称和类别B', '4', '', '4', '5', 3);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '排污种类检测项目' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
