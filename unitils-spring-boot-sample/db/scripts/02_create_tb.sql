/*创建测试数据库对象*/
DROP TABLE IF EXISTS `sample_tb`;
CREATE TABLE `sample_tb` (
  `id` int(11) NOT NULL,
  `value` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
)