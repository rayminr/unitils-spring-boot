/*创建测试数据库*/
drop database if exists test1;
drop database if exists test2;
drop user if exists 'test'@'%';

create database test1 character set utf8mb4;
create database test2 character set utf8mb4;
create user 'test'@'%' identified by 'test';
grant all privileges on test1.* to 'test'@'%';
grant all privileges on test2.* to 'test'@'%';

flush privileges;