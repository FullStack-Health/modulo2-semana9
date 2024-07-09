package com.example.aula11.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //indica que essa classe configura e ativa a segurança do spring security
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> //lambda para definir quais endpoints serão liberados e quais não
                        auth
                                .requestMatchers(HttpMethod.GET, "/hello").permitAll()
                                .anyRequest().authenticated() // todas as requisições precisam de autorização
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}
