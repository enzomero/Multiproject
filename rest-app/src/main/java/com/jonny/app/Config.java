package com.jonny.app;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@ComponentScan(basePackages = "com.jonny")
@Configuration
public class Config{

    @Bean
    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return new NamedParameterJdbcTemplate(getDataSource());
    }

    @Bean
    public DataSource getDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setConnectionProperties("INIT=runscript from 'classpath:/create.sql';");
        dataSource.setInitialSize(1);
        dataSource.setInitSQL("RUNSCRIPT FROM 'classpath:/init.sql';");
        return dataSource;
    }
}
