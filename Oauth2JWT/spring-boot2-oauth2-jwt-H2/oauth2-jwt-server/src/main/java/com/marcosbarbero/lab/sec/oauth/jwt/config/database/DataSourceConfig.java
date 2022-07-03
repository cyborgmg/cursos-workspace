package com.marcosbarbero.lab.sec.oauth.jwt.config.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.database.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}
