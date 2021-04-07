/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 07/04/2021 22:18:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `name` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '管理员用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('li', '123456', 1);
INSERT INTO `admin` VALUES ('admin', '123456', 2);

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `msg` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '公告内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES (2, '提示尽量在白天公众场合交易');
INSERT INTO `announcement` VALUES (20, '买家点击 已收货 按钮，本单交易即完成');
INSERT INTO `announcement` VALUES (21, '私下交易，请注意规避风险');
INSERT INTO `announcement` VALUES (22, '未收到货以前不要点击 已收货 按钮');

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `storage` int(11) NOT NULL COMMENT '书籍库存数',
  `describes` varchar(60) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '书籍描述',
  `buyDate` date NULL DEFAULT NULL COMMENT '购入时间',
  `price` float NULL DEFAULT NULL COMMENT '设定价格',
  `type_` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '种类（分类）',
  `userId` int(11) NULL DEFAULT NULL COMMENT '卖家的id',
  `pic` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '书籍图片',
  `saleNow` tinyint(1) NULL DEFAULT 1,
  `author` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '书籍作者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '书籍列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (7, '我信仰阅读', 5, '我信仰阅读', '2020-12-02', 16.8, '自传', 3, '5415a541-2cda-49d4-a0e3-f271f2dfaf83.jpg', 1, 'NORA ');
INSERT INTO `books` VALUES (13, '关汉卿元曲集', 3, '元曲又称夹心，', '2020-12-06', 3.99, '出版 / 非虚构', 4, 'a4602dd0-3865-40bf-996f-b29776895be0.jpg', 1, '关汉卿');
INSERT INTO `books` VALUES (14, '二刻拍案惊奇', 3, '拟话本小说集', '2020-11-05', 3.99, '虚构', 4, 'ad875afe-4ac7-4554-bec9-dda501b2a320.jpg', 1, '凌濛初');
INSERT INTO `books` VALUES (15, '黑箱', 2, '黑箱——日本之耻', '2020-11-06', 7.39, '出版 / 非虚构', 1, 'da292b4a-deae-479b-a0f2-a1c7d57f3eed.jpg', 1, '伊藤纱织');
INSERT INTO `books` VALUES (16, '黄庭坚诗词全集', 1, '黄庭坚诗词全集', '2020-12-02', 3.99, '诗词', 1, 'aafd2944-3748-41ac-86e1-ae84016a35e4.jpg', 0, '黄庭坚');
INSERT INTO `books` VALUES (17, '人间词话', 2, '王国维国学', '2020-11-06', 9.99, '诗词', 6, 'e720e55a-fe93-4d4a-b3c4-0f1bd4425b71.jpg', 1, '王国维');
INSERT INTO `books` VALUES (18, '我与地坛', 1, '我已不在地坛，地坛在我', '2020-08-07', 6.88, '小说、散文', 6, '1f387e59-0442-40f8-afe0-2dff6a02a462.jpg', 1, '史铁生');
INSERT INTO `books` VALUES (25, '可是', 4, '可是我偏偏不喜欢', '2021-01-14', 3.98, '出版 / 非虚构', 13, '1ffdb0cc-c7d9-490e-b729-25d4d0b27ce7.jpeg', 1, '吴晓乐');
INSERT INTO `books` VALUES (26, '可是', 4, '可是我偏偏不喜欢', '2021-01-07', 3.98, '出版 / 非虚构', 13, 'f5d6a0ba-5f01-458b-8409-8e439dd2807d.jpeg', 1, '吴晓乐');
INSERT INTO `books` VALUES (27, '一座城池', 2, '可是我偏偏不喜欢', '2021-01-01', 3.98, '出版 / 非虚构', 13, '837cd9c5-6f49-4358-ae5d-1a40f9ea8f1d.jpeg', 1, '吴晓乐');
INSERT INTO `books` VALUES (28, '长安乱', 7, '可是我偏偏不喜欢', '2021-01-02', 4.43, '出版 / 非虚构', 13, '45c064a5-9b32-4ea7-b882-61ae17dc7e2d.jpeg', 1, '韩寒');
INSERT INTO `books` VALUES (32, '张其', 5, '11111111', '2021-01-08', 6.88, '小说', 4, '1574a372-b6d4-438d-9cb1-248f43d25fb7.jpg', 1, '刘浩');

-- ----------------------------
-- Table structure for carts
-- ----------------------------
DROP TABLE IF EXISTS `carts`;
CREATE TABLE `carts`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyerId` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `bookName` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `smallImg` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `ownerId` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `bookId`(`bookId`, `buyerId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '购物车表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of carts
-- ----------------------------
INSERT INTO `carts` VALUES (18, 6, 15, '黑箱', 'da292b4a-deae-479b-a0f2-a1c7d57f3eed.jpg', 7.39, 1, 1);
INSERT INTO `carts` VALUES (23, 1, 8, '皮囊', '7d718b5d-0a1a-4a20-a664-4dedc41499eb.jpg', 13.9, 1, 3);
INSERT INTO `carts` VALUES (24, 4, 7, '我信仰阅读', '5415a541-2cda-49d4-a0e3-f271f2dfaf83.jpg', 22.1, 1, 3);
INSERT INTO `carts` VALUES (26, 11, 17, '人间词话', 'e720e55a-fe93-4d4a-b3c4-0f1bd4425b71.jpg', 9.99, 3, 6);
INSERT INTO `carts` VALUES (31, 13, 7, '我信仰阅读', '5415a541-2cda-49d4-a0e3-f271f2dfaf83.jpg', 22.1, 1, 3);
INSERT INTO `carts` VALUES (32, 13, 17, '人间词话', 'e720e55a-fe93-4d4a-b3c4-0f1bd4425b71.jpg', 9.99, 1, 6);
INSERT INTO `carts` VALUES (34, 4, 15, '黑箱', 'da292b4a-deae-479b-a0f2-a1c7d57f3eed.jpg', 7.39, 2, 1);
INSERT INTO `carts` VALUES (37, 3, 18, '我与地坛', '1f387e59-0442-40f8-afe0-2dff6a02a462.jpg', 6.88, 2, 6);

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `c_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '评论主键',
  `b_id` int(2) NOT NULL COMMENT '书籍id',
  `u_id` int(2) NOT NULL COMMENT '用户id',
  `content` varchar(255) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '评论内容',
  `reply_to` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '回复给哪个用户',
  `reply_to_id` int(2) NULL DEFAULT NULL COMMENT '回复给哪个用户id',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comments
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `buyerId` int(11) NOT NULL COMMENT '卖家id',
  `buyerName` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '买家姓名',
  `sellerId` int(11) NOT NULL COMMENT '卖家id',
  `sellerName` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '卖家姓名',
  `active` tinyint(4) NULL DEFAULT NULL COMMENT '订单状态',
  `createDate` date NULL DEFAULT NULL COMMENT '简历日期',
  `contactBuyer` tinyint(1) NOT NULL DEFAULT 0 COMMENT '卖家联系买家',
  `received` tinyint(1) NOT NULL DEFAULT 0 COMMENT '买家确认收货',
  `sendOut` tinyint(1) NOT NULL DEFAULT 0 COMMENT '卖家已发货',
  `bookId` int(11) NOT NULL,
  `bookName` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `num` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (3, 4, '小羊', 3, '小程', 0, '2020-12-18', 1, 1, 1, 8, '皮囊', 1);
INSERT INTO `orders` VALUES (5, 3, '小程', 4, '小羊', 0, '2020-12-18', 1, 1, 1, 14, '二刻拍案惊奇', 1);
INSERT INTO `orders` VALUES (7, 1, 'chuck', 3, '小程', 1, '2021-01-16', 1, 0, 1, 19, '可是', 1);
INSERT INTO `orders` VALUES (8, 7, 'admin', 3, '小程', 1, '2021-01-19', 1, 0, 0, 19, '可是', 1);
INSERT INTO `orders` VALUES (9, 11, '李四', 6, 'monica', 1, '2021-01-19', 0, 0, 0, 17, '人间词话', 1);
INSERT INTO `orders` VALUES (10, 13, '沉重', 6, 'monica', 0, '2021-01-25', 0, 1, 0, 18, '我与地坛', 1);
INSERT INTO `orders` VALUES (11, 3, '小程', 4, '小羊', 1, '2021-01-26', 0, 0, 0, 13, '关汉卿元曲集', 1);
INSERT INTO `orders` VALUES (12, 3, '小程', 6, 'monica', 0, '2021-01-26', 1, 1, 1, 18, '我与地坛', 1);

-- ----------------------------
-- Table structure for recommend
-- ----------------------------
DROP TABLE IF EXISTS `recommend`;
CREATE TABLE `recommend`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `bookId` int(11) NOT NULL COMMENT '书籍id',
  `types` tinyint(8) NOT NULL DEFAULT 0 COMMENT '1-推荐 0-没有',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of recommend
-- ----------------------------
INSERT INTO `recommend` VALUES (1, 12, 1);
INSERT INTO `recommend` VALUES (2, 14, 0);
INSERT INTO `recommend` VALUES (3, 8, 0);
INSERT INTO `recommend` VALUES (4, 14, 0);
INSERT INTO `recommend` VALUES (5, 15, 0);
INSERT INTO `recommend` VALUES (6, 18, 0);
INSERT INTO `recommend` VALUES (7, 7, 0);
INSERT INTO `recommend` VALUES (8, 17, 0);
INSERT INTO `recommend` VALUES (9, 25, 0);
INSERT INTO `recommend` VALUES (10, 16, 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `password` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `phone` varchar(45) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '联系方式',
  `gender` varchar(8) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  `profile` varchar(80) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `realName` varchar(20) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = gbk COLLATE = gbk_chinese_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'chuck', '123456', '13154655750', '男', '1a85fbe00-3151-46aa-9170-3f67104e26eb.jpeg', '姜航青');
INSERT INTO `users` VALUES (3, '小程', '1234567', '13154655750', '女', '3d414ef0f-5a8e-498a-ade2-30bb609e656a.jpg', '何岚清');
INSERT INTO `users` VALUES (4, '小羊', '123456', '13154655750', '男', '41c4df501-144d-4900-85fe-6e6338f487a5.jpg', '李湘江');
INSERT INTO `users` VALUES (6, 'monica', '123456', '+8618148002667', '女', '68429b896-c4a7-47e1-831c-d48ee91c642e.jpeg', '邓晴');
INSERT INTO `users` VALUES (7, 'admin', '1234567', '15284819119', '男', '753949944-5607-4fea-9c02-258d55d931af.jpeg', '邱云峰');
INSERT INTO `users` VALUES (12, '刘士余', '123456', '879193', '男', '126fa3e4c7-94d9-4675-bef3-b7c17326a8de.jpg', '刘世宇');
INSERT INTO `users` VALUES (13, '沉重', '123456', '13154655750', '男', '13dd300877-fe83-469b-ace0-e288785447f2.jpeg', '陈中伟');

SET FOREIGN_KEY_CHECKS = 1;
