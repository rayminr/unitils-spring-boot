# Unitils的springboot版本

问题记录：
1.在主工程报错“java.lang.IllegalStateException: Unable to read meta-data for class org.unitils.spring.boot.autoconfigure.UnitilsAutoConfiguration”
解决：
1）可能spring.factories中bean的前后有空格
2）封装starter时一定不要在pom中引用spring-boot-maven-plugin插件以及不要将主启动类打包进去

2.自动装配类中在执行单元测试方式时加载不到mybatis配置项，目前时写死的
解决：
可能跟testContext的初始化有关，有待解决