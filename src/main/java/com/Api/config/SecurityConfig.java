package com.Api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CSRF protection is enabled by default. Disable it if you're using stateless authentication (like JWT)
            .csrf(csrf -> csrf.disable())  // Disable CSRF protection for simplicity (useful for JWT authentication)

            // Defining authorization rules
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/auth/login/**").permitAll()  // Allow login without authentication
                .requestMatchers("/api/employees/**").hasRole("HR")  // HR role required to access employee data
                .requestMatchers("/api/profile/**").hasAnyRole("EMPLOYEE", "HR")  // Both Employee and HR can access profiles
                .anyRequest().authenticated()  // Require authentication for all other requests
            )
            // Configuring basic authentication
            .httpBasic();  // Enable HTTP Basic Authentication (or configure JWT if needed)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encoding
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }
}
