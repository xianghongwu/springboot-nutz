package com.xhw.springbootnutz.configure;

import com.xhw.springbootnutz.interceptors.AppInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebResourcesConfigure implements WebMvcConfigurer {


    /*配置拦截器*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AppInterceptor()).addPathPatterns("/app/**");
    }
}
