package com.orienteering.rest.demo;

import com.orienteering.rest.demo.model.ImageUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Main class
 */
@SpringBootApplication
@EnableConfigurationProperties(ImageUploadProperties.class)
public class RestApplication {

	/**
	 * Run application
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}



}
