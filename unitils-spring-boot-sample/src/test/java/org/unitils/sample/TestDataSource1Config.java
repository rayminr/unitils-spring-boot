package org.unitils.sample;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.unitils.database.UnitilsDataSourceFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({MybatisProperties.class})
@MapperScan(basePackages= {"org.unitils.sample.demo1"}, sqlSessionFactoryRef="testSqlSessionFactory1")
public class TestDataSource1Config {

    @Autowired
    private MybatisProperties properties;

    @Bean(name="testDataSource1")//注入到这个容器
    @ConfigurationProperties(prefix="test1.spring.datasource") //表示取application.properties配置文件中的前缀
    public DataSource testDataSource() throws Exception {
        return (DataSource)(new UnitilsDataSourceFactoryBean().getObject());
    }

    @Bean(name="testSqlSessionFactory1")
    @ConfigurationProperties(prefix="test1.mybatis")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("testDataSource1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(this.properties.getConfigLocation()));
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            bean.setMapperLocations(this.properties.resolveMapperLocations());
        }
        return bean.getObject();
    }

    @Bean(name="testSqlSessionTemplate1")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("testSqlSessionFactory1") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="testTransactionManager1")//配置事务
    public DataSourceTransactionManager testTransactionManager(@Qualifier("testDataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
