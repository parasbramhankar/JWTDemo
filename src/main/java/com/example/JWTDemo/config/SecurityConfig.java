package com.example.JWTDemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(e->e.disable())
                .authorizeHttpRequests(req->req.requestMatchers("/**")
                        .permitAll().anyRequest().authenticated()
                );
        return http.build();
    }

}