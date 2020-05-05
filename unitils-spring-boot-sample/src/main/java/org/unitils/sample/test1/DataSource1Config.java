package org.unitils.sample.test1;

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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.unitils.database.UnitilsDataSourceFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({MybatisProperties.class})
@MapperScan(basePackages= {"org.unitils.sample.test1"}, sqlSessionFactoryRef="test1SqlSessionFactory")
public class DataSource1Config {

    @Autowired
    private MybatisProperties properties;

    @Bean(name="test1DataSource")//注入到这个容器
    @ConfigurationProperties(prefix="spring.datasource.test1") //表示取application.properties配置文件中的前缀
    @Primary //primary是设置优先，因为有多个数据源，在没有明确指定用哪个的情况下，会用带有primary的，这个注解必须有一个数据源要添加
    public DataSource testDataSource() throws Exception {
        return DataSourceBuilder.create().build();
        //return (DataSource)(new UnitilsDataSourceFactoryBean().getObject());
    }

    @Bean(name="test1SqlSessionFactory")
    @Primary
    //@Qualifier("xxx")的含义是告诉他使用哪个DataSource
    @ConfigurationProperties(prefix="mybatis")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource) throws Exception {
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

    @Bean(name="test1SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="test1TransactionManager")//配置事务
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("test1DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
