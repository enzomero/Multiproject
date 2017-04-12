package com.jonny.dao.impl;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@ComponentScan(basePackages = "com.jonny.dao")
@TestConfiguration
public class DepartmentConfig {
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setConnectionProperties("INIT=runscript from 'classpath:createTestDep.sql';");
        dataSource.setInitialSize(1);
        dataSource.setInitSQL("RUNSCRIPT FROM 'classpath:initTestDep.sql';");
        return dataSource;
    }
}
