package com.treinos.treinos.config;

import com.treinos.treinos.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Definindo o bean do PasswordEncoder
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desativa CSRF para facilitar no desenvolvimento
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/gym/workouts/**").hasAnyRole("USER", "TRAINER")
                        .requestMatchers("/gym/exercises/**").hasRole("TRAINER")
                        .requestMatchers("/gym/user/**").hasRole("TRAINER")
                        .anyRequest().permitAll()  // Qualquer outra requisição é permitida
                )
                .formLogin(form -> form
                        .loginPage("/login.html")  // Define a página de login
                        .loginProcessingUrl("/api/auth/login")  // URL do login POST
                        .defaultSuccessUrl("/dashboard", true)  // Redireciona após sucesso
                        .failureUrl("/login.html?error=true")  // Redireciona em caso de falha
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login.html?logout=true")
                        .permitAll()
                );

        return http.build();
    }
}
