package com.sastaa.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .csrf().disable() // Disable CSRF for simplicity (only for development)
//	            .authorizeHttpRequests(authorize -> authorize
//	                .requestMatchers("/api/cart/**").permitAll() // Allow access to /cart endpoints
//	                .requestMatchers("/api/cart/**").permitAll()
//	                .anyRequest().authenticated() // Require authentication for other requests
//	            );
//	        return http.build();
//	    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for development purposes
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()); // Allow all requests without authentication
        return http.build();
    }
}
