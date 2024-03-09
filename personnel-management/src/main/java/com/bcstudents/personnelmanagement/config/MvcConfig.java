package com.bcstudents.personnelmanagement.config;

import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Extend the WebMvcConfigurer interface
@Configuration
//@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/", "/user/login", "/css/*", "/js/**", "/img/**");
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}