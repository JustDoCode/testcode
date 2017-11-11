package com.cpgps.canbus.repository.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
/**
 * @author wangshuai01@e6yun.com 2017年5月12日
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "canbusDataSource")
    @Qualifier("canbusDataSource")
    @ConfigurationProperties(prefix="spring.datasource.canbus")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "infoDataSource")
    @Qualifier("infoDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.info")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
