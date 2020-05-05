/*创建测试数据库对象*/
DROP TABLE IF EXISTS `msg_tb`;
CREATE TABLE `msg_tb` (
  `code` int(11) NOT NULL,
  `msg` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`code`)
);

