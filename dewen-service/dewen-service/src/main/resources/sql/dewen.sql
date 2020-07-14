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

 Date: 14/07/2020 19:11:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `USER_NAME_UNIQUE`(`USER_NAME`) USING BTREE,
  UNIQUE INDEX `EMPLOYEE_ID_UNIQUE`(`EMPLOYEE_ID`) USING BTREE,
  UNIQUE INDEX `MOBILE_UNIQUE`(`MOBILE`) USING BTREE,
  UNIQUE INDEX `EMAIL_UNIQUE`(`EMAIL`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 324 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of common_user
-- ----------------------------
INSERT INTO `common_user` VALUES (1, 'hailin', 'hailin', NULL, NULL, 'hailin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, NULL, NULL, NULL);

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
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '单位地址',
  `legal_representative` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法定代表人',
  `organization_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码',
  `environmental_protection_officer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环保负责人',
  `contact_number` int(25) NULL DEFAULT NULL COMMENT '联系电话',
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
  `disposal_way_company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处置方式第三方',
  `disposal_way` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处置方式',
  `main_sound_source_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主要声源名称',
  `mmppc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '污染防治的主要措施',
  `sewerage_rain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '雨污分流',
  `row_to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排向',
  `enterprise_pretreatment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业预处理',
  `output` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产生量（月平均）',
  `the_sewage_to` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '污水排向',
  `stfds` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活污水化粪池',
  `life_produced` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生活产生量（月平均）',
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
  `breaks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产工艺描述',
  `official_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '环评时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '重点工业企业基本情况表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company_info
-- ----------------------------
INSERT INTO `company_info` VALUES (1, '大横琴', '横琴', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `company_info` VALUES (2, '科技公司', '横琴', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '主要产品生产情况表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监测项目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for company_record
-- ----------------------------
DROP TABLE IF EXISTS `company_record`;
CREATE TABLE `company_record`  (
  `id` int(15) NOT NULL,
  `company_id` int(15) NULL DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `record_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '记录类型:1巡查执法记录/2行政处罚记录',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '记录类型表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '排污种类' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
