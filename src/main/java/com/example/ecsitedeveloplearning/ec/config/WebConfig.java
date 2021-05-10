package com.example.ecsitedeveloplearning.ec.config;

import java.io.File;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String rootPath = System.getProperty("user.dir");
	    String imagePath = "file:"+rootPath + File.separator + "images/";
	    System.out.println(imagePath);
        registry.addResourceHandler(
                "/webjars/**",
                "/css/**",
                "/js/**",
                "/images/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        imagePath);
    }
}