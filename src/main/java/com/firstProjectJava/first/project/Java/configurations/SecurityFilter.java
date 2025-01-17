package com.firstProjectJava.first.project.Java.configurations;

import com.firstProjectJava.first.project.Java.models.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilter {
  private final CustomFilter customFilter;

  public SecurityFilter(CustomFilter customFilter) {
    this.customFilter = customFilter;
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers(HttpMethod.POST, "/users", "/users/auth").permitAll()
            .requestMatchers("/h2-console/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/products").hasRole(Role.ADMIN.name())
            .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole(Role.ADMIN.name())
            .requestMatchers(HttpMethod.POST, "/categories").hasRole(Role.ADMIN.name())
            .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole(Role.ADMIN.name())
            .anyRequest().authenticated()
        ).sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);


    return http.build();
  }
}
