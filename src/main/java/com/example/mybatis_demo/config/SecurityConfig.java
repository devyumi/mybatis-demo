package com.example.mybatis_demo.config;

import com.example.mybatis_demo.config.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@RequiredArgsConstructor
@Component
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final LoginSuccess loginSuccess;
    private final LoginFail loginFail;
    private final LogOutSuccess logOutSuccess;
    private final CustomAccessDeniedHandler deniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers("/members").hasRole("SUPER_ADMIN")
                                .requestMatchers("/products").authenticated()
                                .requestMatchers("/products/save", "/products/update/**").hasAnyRole("SUPER_ADMIN", "ADMIN")
                                .anyRequest().permitAll())

                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .successHandler(loginSuccess)
                        .failureHandler(loginFail)
                        .permitAll())

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .logoutSuccessHandler(logOutSuccess))

                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new CustomAccessDeniedHandler()))

                .userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
