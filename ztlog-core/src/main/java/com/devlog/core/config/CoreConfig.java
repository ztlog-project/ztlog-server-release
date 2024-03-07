package com.devlog.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CoreConfig {

    /**
     * 비밀번호 암호화 encoder
     * @return encoder
     */
//    @Bean
//    public Pbkdf2PasswordEncoder passwordEncoder() {
//        return new Pbkdf2PasswordEncoder("secret", 16, 310000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512);
//    }

    /**
     * rest template
     * @return  rest template
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
