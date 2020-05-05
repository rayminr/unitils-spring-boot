package org.unitils.sample.test2;

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
import org.unitils.database.UnitilsDataSourceFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({MybatisProperties.class})
@MapperScan(basePackages= {"org.unitils.sample.test2"}, sqlSessionFactoryRef="test2SqlSessionFactory")
public class DataSource2Config {

    @Autowired
    private MybatisProperties properties;

    @Bean(name="test2DataSource")//注入到这个容器
    @ConfigurationProperties(prefix="spring.datasource.test2") //表示取application.properties配置文件中的前缀
    public DataSource testDataSource() throws Exception {
        return DataSourceBuilder.create().build();
        //return (DataSource)(new UnitilsDataSourceFactoryBean().getObject());
    }

    @Bean(name="test2SqlSessionFactory")
    //@Qualifier("xxx")的含义是告诉他使用哪个DataSource
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test2DataSource") DataSource dataSource) throws Exception {
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

    @Bean(name="test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="test2TransactionManager")//配置事务
    public DataSourceTransactionManager testTransactionManager(@Qualifier("test2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
