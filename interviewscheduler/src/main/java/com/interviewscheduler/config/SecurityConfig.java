package com.interviewscheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable csrf for postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // ✅ allow everything
                )
                .formLogin(form -> form.disable()) // disable login form
                .httpBasic(httpBasic -> httpBasic.disable()); // disable basic auth

        return http.build();
    }
}
