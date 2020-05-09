/*创建测试数据库*/
drop database if exists demo1;
drop database if exists demo2;
drop user if exists 'test'@'%';

create database demo1 character set utf8mb4;
create database demo2 character set utf8mb4;
create user 'test'@'%' identified by 'test';
grant all privileges on demo1.* to 'test'@'%';
grant all privileges on demo2.* to 'test'@'%';

flush privileges;