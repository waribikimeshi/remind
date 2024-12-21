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
     * 通すURL。getはそのまま通るが、postはトークンないと403。
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.authorizeHttpRequests(requests -> requests
                .requestMatchers("/" + Consts.REST_URL_BASE + Consts.REST_URL_AUTHENTICATION + "/**").permitAll()
        );
        return http.build();
    }
    
    //TODO:何らかのログイン認証。5.7くらいで大幅に変わってる

}