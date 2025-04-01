package org.example.config;

import jakarta.servlet.Filter;
import org.example.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<Filter> authFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());  // 필터 등록
        registrationBean.addUrlPatterns("/api/*");     // URL 경로 지정
        registrationBean.setOrder(1);                  // 필터 실행 순서
        return registrationBean;
    }
}

