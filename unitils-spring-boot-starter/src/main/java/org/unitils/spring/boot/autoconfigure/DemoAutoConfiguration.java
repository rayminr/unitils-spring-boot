package org.unitils.spring.boot.autoconfigure;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * SpringBoot 自动加载测试类
 * @Author: rayminr
 * @Date:Created in 2020-05-03
 */
@Configuration
@EnableConfigurationProperties(UnitilsProperties.class)
@ConditionalOnClass({DemoService.class})
@ConditionalOnProperty(prefix = "unitils.spring.boot", value = "enabled", matchIfMissing = true)
public class DemoAutoConfiguration {

    @Autowired
    private UnitilsProperties unitilsProperties;

    @Bean
    @ConditionalOnMissingBean(DemoService.class)
    public DemoService demoService() {
        return new DemoService(unitilsProperties);
    }

}