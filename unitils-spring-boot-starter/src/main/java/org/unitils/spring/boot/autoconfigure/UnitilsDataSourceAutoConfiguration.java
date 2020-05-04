package org.unitils.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.unitils.database.UnitilsDataSourceFactoryBean;

import javax.sql.DataSource;


public class UnitilsDataSourceAutoConfiguration {

    UnitilsDataSourceAutoConfiguration() {
    }
/*
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
    }*/
}
