package com.example.mybatis_demo.config;

import com.example.mybatis_demo.config.auth.CustomUserDetailsService;
import com.example.mybatis_demo.config.auth.LogOutSuccess;
import com.example.mybatis_demo.config.auth.LoginFail;
import com.example.mybatis_demo.config.auth.LoginSuccess;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizationManager -> authorizationManager
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
                        .logoutSuccessHandler(logOutSuccess)
                        .deleteCookies("JSESSIONID"))

                .userDetailsService(customUserDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
