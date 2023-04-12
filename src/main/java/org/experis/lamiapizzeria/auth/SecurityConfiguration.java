package org.experis.lamiapizzeria.auth;

import org.experis.lamiapizzeria.service.DatabaseUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        .requestMatchers("/api/**").permitAll()
        .requestMatchers("/pizza/create").hasAuthority("ADMIN")
        .requestMatchers("/ingredients/**").hasAuthority("ADMIN")
        .requestMatchers("/discount/**").hasAuthority("ADMIN")
        .requestMatchers("/webjars/**").hasAnyAuthority("USER", "ADMIN")
//        .requestMatchers(HttpMethod.POST, "/pizza/**").hasAuthority("ADMIN")
        .requestMatchers("/**").permitAll()
        .and().formLogin()
        .and().logout()
        .and().exceptionHandling();
    http.csrf().disable();
    return http.build();
  }
}
