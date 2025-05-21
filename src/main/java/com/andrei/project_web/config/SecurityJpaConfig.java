package com.andrei.project_web.config;

import com.andrei.project_web.service.security.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("mysql")
public class SecurityJpaConfig {
    private final JpaUserDetailsService userDetailsService;

    public SecurityJpaConfig(JpaUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/product/form").hasRole("ADMIN")
                        .requestMatchers("/", "/webjard/**", "/login", "/resources/**").permitAll()
                        .requestMatchers("/product/*").authenticated()
                        .requestMatchers("/categories/*").hasAnyRole("ADMIN", "GUEST")
                        .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .permitAll()
                                .loginProcessingUrl("/perform_login")
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/access_denied"))
                .build();
    }
}
