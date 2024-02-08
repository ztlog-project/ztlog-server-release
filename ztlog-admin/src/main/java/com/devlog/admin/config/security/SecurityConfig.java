package com.devlog.admin.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * security 설정 적용
     *
     * @param http http 설정
     * @return 필터 체인
     * @throws Exception 공통 예외
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers((headersConfig) -> headersConfig.frameOptions((HeadersConfigurer.FrameOptionsConfig::sameOrigin)))
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers("/v2/api-docs/**", "/swagger-resources/**").permitAll()
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    /**
     * 적용 제외할 uri 패턴 적용
     * @return security 설정
     */
    @Bean
    public WebSecurityCustomizer webSecurity(){
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ); // 토큰 인증 무시 uri 지정
    }
}
