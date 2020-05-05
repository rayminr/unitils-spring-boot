/*创建测试数据库*/
drop database if exists unitlis_sample;
drop user if exists 'unitlis_sample'@'*';

create database unitlis_sample character set utf8mb4;
create user 'unitlis_sample'@'%' identified by '123';
grant all privileges on unitlis_sample.* to 'unitlis_sample'@'%';

flush privileges;