package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.UUID;

@EnableWebSecurity
@Configuration
public class SpringConfig {
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}123456")
                .roles("USER")
                .build();

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user));
        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorization
        http.authorizeHttpRequests(a -> a
                        .requestMatchers("").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/literature").permitAll()
                        .requestMatchers("/dashboard", "/dashboard/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/owl_carousel/**").permitAll()
                        .requestMatchers("/style/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin((form) -> form
                        .loginPage("/auth/go/login")
                        .loginProcessingUrl("/auth/login")
                        .failureUrl("/auth/go/login?error=true")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }


}
