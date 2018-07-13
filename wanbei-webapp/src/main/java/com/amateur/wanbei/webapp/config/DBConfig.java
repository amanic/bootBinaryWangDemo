package com.amateur.wanbei.webapp.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 * Created by chenhaitao on 2018/6/5.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties(DBProperties.class)
@ConditionalOnClass(DruidDataSource.class)
@MapperScan(value = "com.amateur.wanbei.dao.mapper",sqlSessionFactoryRef = "sqlSessionFactory")
public class DBConfig {

    @Autowired
    private DBProperties dbProperties;

    @Bean
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dbProperties.getUrl());
        druidDataSource.setUsername(dbProperties.getUser());
        druidDataSource.setPassword(dbProperties.getPassword());
        druidDataSource.setInitialSize(dbProperties.getInitialSize());
        druidDataSource.setMinIdle(dbProperties.getMinIdle());
        druidDataSource.setMaxActive(dbProperties.getMaxActive());
        druidDataSource.setMaxWait(dbProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(dbProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(dbProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(dbProperties.getValidationQuery());
        druidDataSource.setTestWhileIdle(dbProperties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(dbProperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(dbProperties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(dbProperties.isPoolPreparedStatements());
        druidDataSource.setFilters(dbProperties.getFilters());
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

}
