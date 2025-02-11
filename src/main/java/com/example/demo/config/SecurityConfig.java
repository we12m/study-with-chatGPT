package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(crsf -> crsf.disable()) // csrf 보호 기능 비활성화, JWT(json Web Token)을 사용하기 위함
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/**", "/swagger-ui/**", "/swagger-ui.html/**", "/v3/api-docs/**", "/notify/**", "/actuator/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // 내부적으로 "ROLE_ADMIN"과 비교함
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider) , UsernamePasswordAuthenticationFilter.class);
                //.httpBasic(Customizer.withDefaults()); // 기본 로그인폼은 사용하지 않도록 설정
        return http.build();
    }

}
