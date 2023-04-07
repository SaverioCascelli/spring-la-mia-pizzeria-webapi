package org.experis.lamiapizzeria.auth;

import org.experis.lamiapizzeria.service.DatabaseUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
  
  @Bean
  UserDetailsService userDetailsService() {
    return new DatabaseUserDetailService();
  }
  
  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
  
  @Bean
  DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }
  
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests()
        .requestMatchers("/webjars/**").hasAnyAuthority("USER", "ADMIN")
        .requestMatchers("/", "/pizza", "/pizza/*").hasAuthority("USER")
        .requestMatchers(HttpMethod.POST).hasAuthority("ADMIN")
        .requestMatchers("/**").hasAuthority("ADMIN")
        .and().formLogin()
        .and().logout()
        .and().exceptionHandling();
    return http.build();
  }
}
