package com.factory.task.config;

import com.factory.task.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    public AuthInterceptor getSecurityInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.addPathPatterns("/guard/**");
//        addInterceptor.excludePathPatterns("/user/**");
//        addInterceptor.excludePathPatterns("/attendance/**");
//        addInterceptor.excludePathPatterns("/inventory/**");
//        addInterceptor.excludePathPatterns("/department/**");
//        addInterceptor.excludePathPatterns("/jobManager/**");
//        addInterceptor.excludePathPatterns("/taskTpl/**");

        // 拦截配置
//        addInterceptor.addPathPatterns("/**");
    }
}
