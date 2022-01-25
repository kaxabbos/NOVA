package com.nova.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path_img}")
    private String uploadPathImg;
    @Value("${upload.path_static}")
    private String uploadPathStatic;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + uploadPathImg + "/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:" + uploadPathStatic + "/");
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
    }
}
