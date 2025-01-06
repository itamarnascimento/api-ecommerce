package com.firstProjectJava.first.project.Java.configurations;

import com.firstProjectJava.first.project.Java.services.auth.CustomUserDetailsService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CustomFilter extends OncePerRequestFilter {
  private final CustomUserDetailsService customUserDetailsService;
  private final JwtSecurity jwtSecurity;

  public CustomFilter(CustomUserDetailsService customUserDetailsService, JwtSecurity jwtSecurity) {
    this.customUserDetailsService = customUserDetailsService;
    this.jwtSecurity = jwtSecurity;
  }

  @Override
  protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                  @Nonnull HttpServletResponse response,
                                  @Nonnull FilterChain filterChain) throws ServletException, java.io.IOException {


    String authHeader = request.getHeader("Authorization");
    String token = null;
    String username = null;

    if(authHeader != null && authHeader.startsWith("Bearer ")){
      token = authHeader.substring(7);
      username = jwtSecurity.extractUsername(token);
    }


    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
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

