package com.example.paymentservice.config;

import com.example.paymentservice.filters.JwtCreationFilter;
import com.example.paymentservice.filters.JwtValidationFilter;
import com.example.paymentservice.filters.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().disable()
                .csrf().disable()
                .addFilterAfter(new JwtCreationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtValidationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
