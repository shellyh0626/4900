package com.bcstudents.personnelmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Extend the WebMvcConfigurer interface
@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

}