package com.chen.confi;

import com.chen.interceptor.AllowOriginInterceptor;
import com.chen.interceptor.CheckTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author chen
 * @crete 2022-08-30-22:59
 */

@Configuration
public class CheckTokenInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private CheckTokenInterceptor checkTokenInterceptor;

    @Autowired
    private AllowOriginInterceptor allowOriginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");

        registry.addInterceptor(allowOriginInterceptor)
                .addPathPatterns("/**");
    }
}