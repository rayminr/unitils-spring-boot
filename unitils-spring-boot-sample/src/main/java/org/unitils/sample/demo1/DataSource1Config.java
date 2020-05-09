package org.unitils.sample.demo1;

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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({MybatisProperties.class})
@MapperScan(basePackages= {"org.unitils.sample.demo1"}, sqlSessionFactoryRef="sqlSessionFactory1")
public class DataSource1Config {

    @Autowired
    private MybatisProperties properties;

    @Bean(name="dataSource1")//注入到这个容器
    @ConfigurationProperties(prefix="spring.datasource.demo1") //表示取application.properties配置文件中的前缀
    @Primary //primary是设置优先，因为有多个数据源，在没有明确指定用哪个的情况下，会用带有primary的，这个注解必须有一个数据源要添加
    public DataSource dataSource() throws Exception {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="sqlSessionFactory1")
    @Primary
    @ConfigurationProperties(prefix="demo1.mybatis")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource1") DataSource dataSource) throws Exception {
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

    @Bean(name="sqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) {
        ExecutorType executorType = this.properties.getExecutorType();
        return executorType != null ? new SqlSessionTemplate(sqlSessionFactory, executorType) : new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name="transactionManager1")//配置事务
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
