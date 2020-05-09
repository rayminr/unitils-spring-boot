package org.unitils.sample.demo2;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({MybatisProperties.class})
@MapperScan(basePackages= {"org.unitils.sample.demo2"}, sqlSessionFactoryRef="sqlSessionFactory2")
public class DataSource2Config {

    @Autowired
    private MybatisProperties properties;

    @Bean(name="dataSource2")//注入到这个容器
    @ConfigurationProperties(prefix="spring.datasource.demo2") //表示取application.properties配置文件中的前缀
    public DataSource dataSource() throws Exception {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="sqlSessionFactory2")
    //@Qualifier("xxx")的含义是告诉他使用哪个DataSource
    @ConfigurationProperties(prefix="demo2.mybatis")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(this.properties.getConfigLocation()));
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            bean.setMapperLocations(this.properties.resolveMapperLocations());
        }
        return bean.getObject();
    }

    @Bean(name="sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) {
        ExecutorType executorType = this.properties.getExecutorType();
        return executorType != null ? new SqlSessionTemplate(sqlSessionFactory, executorType) : new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="transactionManager2")//配置事务
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource2") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
