package org.jesperancinha.vma.controller;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Configuration
@MapperScan("org.jesperancinha.vma.controller.mapper")
public class AppTxConfig {
    private Logger LOGGER = Logger.getLogger(AppTxConfig.class.getName());

    private final DataSource dataSource;

    public AppTxConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        return sessionFactory.getObject();
    }
}