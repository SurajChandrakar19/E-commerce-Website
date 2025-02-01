package com.sastaa.configration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
	
	@Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/api/**") // Match your endpoint pattern
	                .allowedOrigins("*") // Update with specific origins in production
	                .allowedMethods("GET", "POST", "PUT", "DELETE");
	}

}
