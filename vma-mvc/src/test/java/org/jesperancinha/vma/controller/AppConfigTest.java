package org.jesperancinha.vma.controller;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
@MapperScan("org.jesperancinha.vma.controller.mapper")
public class AppConfigTest {
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class.getName());

    @Bean
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:h2:~/vma-mvc");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOGGER.info("Creating tables");
        jdbcTemplate.execute("drop table  if exists OriginalInfo");
        jdbcTemplate.execute("create table OriginalInfo(id serial, title varchar(255))");

        return dataSource;
    }
}