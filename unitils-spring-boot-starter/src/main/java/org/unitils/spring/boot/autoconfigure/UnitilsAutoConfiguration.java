package org.unitils.spring.boot.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.unitils.UnitilsBlockJUnit4ClassRunner;
import org.unitils.database.UnitilsDataSourceFactoryBean;

import javax.sql.DataSource;


/**
 * Unitils支持springboot的自动装配类
 * @Author: rayminr
 * @Date:Created in 2020-05-03
 */
@Configuration
@EnableConfigurationProperties(UnitilsProperties.class)
@ConditionalOnClass({UnitilsBlockJUnit4ClassRunner.class, UnitilsService.class})
public class UnitilsAutoConfiguration {

    @Autowired
    private UnitilsProperties unitilsProperties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "unitils.spring.boot",value = "enabled",matchIfMissing = true)
    public UnitilsService msgService() {
        return new UnitilsService(unitilsProperties.getMsg());
    }

    @Configuration(
            proxyBeanMethods = false
    )
    @ConditionalOnClass({org.unitils.database.UnitilsDataSourceFactoryBean.class})
    @ConditionalOnMissingBean({DataSource.class})
    @ConditionalOnProperty(
            name = {"spring.datasource.type"},
            havingValue = "org.unitils.database.UnitilsDataSourceFactoryBean",
            matchIfMissing = true
    )
    static class UnitilsDataSource {
        UnitilsDataSource() {
        }

        @Bean
        @ConfigurationProperties(
                prefix = "spring.datasource.unitils"
        )
        DataSource dataSource(DataSourceProperties properties) throws Exception {
            return (DataSource)(new UnitilsDataSourceFactoryBean().getObject());
        }
    }
}