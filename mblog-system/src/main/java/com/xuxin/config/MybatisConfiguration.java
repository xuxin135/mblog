package com.xuxin.config;


import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisConfiguration {

    @Bean
    public PageInterceptor getInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
//        properties.setProperty("dialect","mysql");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
