package com.infoPulse.lessons.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement

public class DataConfig {

    // For Spring Security
    @Autowired
    DataSource dataSource;


    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService() {
        // UserDetailsServiceRetrieves implementation which retrieves the
        // user details (username, password, enabled flag, and authorities) from a database using JDBC queries.

        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource);
        jdbcDao.setUsersByUsernameQuery("select login as username ,password, enabled from users where login=?");
        jdbcDao.setAuthoritiesByUsernameQuery("select u.login as username, r.name as rolename " +
                "from users_roles ur " +
                "join roles r on ur.role_id = r.id " +
                "join users u on ur.user_id = u.id " +
                "where u.login=?");
        return jdbcDao;
    }
    // End For Spring Security


    @Bean
    public Logger getLogger() {
        return LogManager.getLogger("ua.com.univerpulse.loggers.module1");
    }

}