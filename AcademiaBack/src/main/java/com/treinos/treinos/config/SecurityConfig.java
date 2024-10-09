package com.treinos.treinos.config;

import com.treinos.treinos.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desativa CSRF para facilitar no desenvolvimento
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // Permite todos acessarem todos os endpoints sem autenticação
                )
                .formLogin(form -> form.disable()) // Desabilita o formulário de login
                .logout(logout -> logout.permitAll()); // Permite que todos possam se deslogar

        return http.build();
    }


    // Define o PasswordEncoder como um bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
