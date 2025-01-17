package com.firstProjectJava.first.project.Java.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firstProjectJava.first.project.Java.dtos.ErrorResponse;
import com.firstProjectJava.first.project.Java.services.auth.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.PrintWriter;

@Component
public class CustomFilter extends OncePerRequestFilter {
  private final CustomUserDetailsService customUserDetailsService;
  private final JwtSecurity jwtSecurity;
  private final ObjectMapper objectMapper;

  public CustomFilter(CustomUserDetailsService customUserDetailsService, JwtSecurity jwtSecurity, ObjectMapper objectMapper) {
    this.customUserDetailsService = customUserDetailsService;
    this.jwtSecurity = jwtSecurity;
    this.objectMapper = objectMapper;
  }

  @Override
  protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                  @Nonnull HttpServletResponse response,
                                  @Nonnull FilterChain filterChain) throws ServletException, java.io.IOException {


    String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      token = authHeader.substring(7);
      try {
        username = jwtSecurity.extractUsername(token);
      } catch (ExpiredJwtException e) {
        response.setStatus(403);
        ErrorResponse<String> error =  new ErrorResponse<>("Token Expired");
        ServletOutputStream writer = response.getOutputStream();
        byte[] json = objectMapper.writeValueAsBytes(error);
        writer.write(json);
        return;
      }
    }


    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

      // Validate token and set authentication
      if (jwtSecurity.validateToken(token, userDetails)) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    filterChain.doFilter(request, response);
  }
}

