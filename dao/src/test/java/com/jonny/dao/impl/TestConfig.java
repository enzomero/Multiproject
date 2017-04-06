package com.jonny.dao.impl;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@ComponentScan(basePackages = "com.jonny")
@TestConfiguration
public class TestConfig {
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setConnectionProperties("INIT=runscript from 'classpath:createTest.sql';");
        dataSource.setInitialSize(1);
        dataSource.setInitSQL("RUNSCRIPT FROM 'classpath:initTest.sql';");
        return dataSource;
    }
}
