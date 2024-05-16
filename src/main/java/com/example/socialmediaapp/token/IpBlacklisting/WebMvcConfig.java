package com.example.socialmediaapp.token.IpBlacklisting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private IPBlacklistInterceptor ipBlacklistInterceptor;

    public WebMvcConfig(IPBlacklistInterceptor ipBlacklistInterceptor) {
        this.ipBlacklistInterceptor = ipBlacklistInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipBlacklistInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}