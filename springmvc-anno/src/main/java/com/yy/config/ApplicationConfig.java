package com.yy.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.sql.DataSource;

/**
 * @Author: chen
 * @Date: 2023/2/17 19:01
 * @Desc:
 */
@ComponentScan(value = "com.yy", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class})
})
@PropertySource("classpath:jdbc.properties")
@MapperScan("com.yy.dao")
@Import({MybatisPlusConfig.class, TransactionAdviceConfig.class})
// 开启基于aop的事务管理
@EnableAspectJAutoProxy
// 开启基于注解的事务管理
// @EnableTransactionManagement
public class ApplicationConfig {
    @Value("${jdbc.jdbcUrl}")
    private String url;

    @Value("${jdbc.driverClass}")
    private String driver;

    @Value("${jdbc.userName}")
    private String username;

    @Value("${jdbc.password}")
    private String password;


    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
