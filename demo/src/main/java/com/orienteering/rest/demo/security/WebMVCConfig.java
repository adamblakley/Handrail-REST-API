package com.orienteering.rest.demo.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Config class implements WEBMVCConfigurer
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    private final long MAXIMUM_TIME_SECONDS = 3600;

    // Implements Cors mapping for cross domain communication
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAXIMUM_TIME_SECONDS);
    }

    // Add resource handlers for the upload of files to the correct filepath.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/photographs/**").addResourceLocations("file:uploads/photographs/");
    }
}



