package org.unitils.spring.boot.autoconfigure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.unitils.database.UnitilsDataSourceFactoryBean;

import javax.sql.DataSource;

@Component
public class DataSourcePostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //替换成UnitilsDataSource
        if (beanName.contains("dataSource") && (bean instanceof DataSource)) {
            try {
                return new UnitilsDataSourceFactoryBean().getObject();
            } catch (Exception exp) {
                throw new RuntimeException("replace database failed, can not continue to process", exp);
            }
        }
        return bean;
    }
}
