# Unitils的springboot版本

问题记录：
1.在主工程报错“java.lang.IllegalStateException: Unable to read meta-data for class org.unitils.spring.boot.autoconfigure.UnitilsAutoConfiguration”
解决：
1）可能spring.factories中bean的前后有空格
2）封装starter时一定不要在pom中引用spring-boot-maven-plugin插件以及不要将主启动类打包进去