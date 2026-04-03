package com.back.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationFilter customAuthenticationFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/v1/members/login", "/api/v1/members/join").permitAll()
                        // .requestMatchers("/**").permitAll() // 전체 경로 허용.
                        .anyRequest().authenticated()) // 나머지 경로는 인증 필요.
                .csrf((csrf) -> csrf.disable())
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}