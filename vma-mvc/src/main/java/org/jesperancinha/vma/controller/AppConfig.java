package org.jesperancinha.vma.controller;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
@MapperScan("org.jesperancinha.vma.controller.mapper")
public class AppConfig {
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getName());

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/vmaDB");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOGGER.info("Creating tables");
        jdbcTemplate.execute("drop table  if exists OriginalInfo");
        jdbcTemplate.execute("create table OriginalInfo(id serial, title varchar(255))");

        return dataSource;
    }
}