package com.example.rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.rest.common.Consts;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{


    /**
     * 通すURL
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/" + Consts.REST_URL_BASE + Consts.REST_URL_AUTHENTICATION + "/**").permitAll()
        );
        return http.build();
    }
    
    //TODO:何らかのログイン認証。5.7くらいで大幅に変わってる

}