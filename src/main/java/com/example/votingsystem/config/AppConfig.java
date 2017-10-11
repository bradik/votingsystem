package com.example.votingsystem.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Brad on 16.09.2017.
 */

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(value = "com.example.votingsystem.repository")
public class AppConfig {

}
