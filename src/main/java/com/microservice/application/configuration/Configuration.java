package com.microservice.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@org.springframework.context.annotation.Configuration
@EnableSwagger2
public class Configuration {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String databaseUsername;

    @Value("${spring.datasource.password}")
    private String databasePassword;

    @Bean
    public Connection getDatabaseConnection() throws SQLException {
       return DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
    }

    @Bean
    public Statement getSqlStatement() throws SQLException {
        return getDatabaseConnection().createStatement();
    }

}
