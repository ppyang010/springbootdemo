package com.code.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author ccy
 */
@Configuration
@EnableConfigurationProperties
public class DataSourceConfig {

    @Primary
    @Bean(name = "oneDataSource")
    @Qualifier("oneDataSource")
    @ConfigurationProperties(prefix="spring.datasource.one")
    public DataSource oneDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "twoDataSource")
    @Qualifier("twoDataSource")
    @ConfigurationProperties(prefix="spring.datasource.two")
    public DataSource twoDataSource() {
        return DataSourceBuilder.create().build();
    }
}
