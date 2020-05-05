# 单元测试框架Unitls for spring boot版本使用方式说明
1）支持注解方式
2）支持多数据库、多schema



mabits逆向工程配置文件路径如下：
/Users/sanny/work/auto-test/unitils-spring-boot/unitils-spring-boot-sample/db/mybatis
重复生成时注意mapper/xxx.xml会追加写入。

问题：mapper文件加载不到
解决：以下两者都需确认
1）需要在启动文件指定MapperScan(basePackages = "org.unitils.sample.mapper")
2）配置文件中指定，mybatis.mapper-locations=classpath:mapper/SampleMapper.xml