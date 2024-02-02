package com.devlog.api.config.security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf((csrfconfig) -> csrfconfig.disable())
//                .headers((headersConfig) -> headersConfig.frameOptions((frameOptionsConfig -> frameOptionsConfig.sameOrigin())))
//                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers("/v2/api-docs/**", "/swagger-resources/**").permitAll()
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//}
