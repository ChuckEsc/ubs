package com.baizhi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class myWebConfigurer implements WebMvcConfigurer {
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("file:C:\\Users\\123\\IdeaProjects\\usedBookStore\\src\\main\\webapp\\static");
        registry.addResourceHandler("/bootstrap-3.3.7-dist/**").addResourceLocations("file:C:\\Users\\123\\IdeaProjects\\usedBookStore\\src\\main\\webapp\\bootstrap-3.3.7-dist");
        registry.addResourceHandler("/static/covers").addResourceLocations("file:C:\\Users\\123\\IdeaProjects\\Upfile-Vue\\src\\main\\resources\\static\\covers");
    }
}
