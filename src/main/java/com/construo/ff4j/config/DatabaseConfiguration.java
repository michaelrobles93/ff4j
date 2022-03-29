package com.construo.ff4j.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;

//@Configuration
@Log4j2
public class DatabaseConfiguration {

    /*@Bean
    public DataSource dataSource() {
        return (DataSource) dataSourceFactory();
    }

    @Bean
    public JndiObjectFactoryBean dataSourceFactory() {

        return new JndiObjectFactoryBean();
    }*/
}
