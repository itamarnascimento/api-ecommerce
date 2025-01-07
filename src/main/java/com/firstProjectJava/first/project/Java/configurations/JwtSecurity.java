package com.firstProjectJava.first.project.Java.configurations;

import com.firstProjectJava.first.project.Java.models.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtSecurity {
  private static final String SECRET_KEY = "YourSuperSecretKeyForJWT1234567890"; // Deve ter pelo menos 256 bits
  private static final long EXPIRATION_TIME = 3600000; // 1 hora em milissegundos
  private final Key key;

  public JwtSecurity() {
    this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    ;
  }

  public String generateTokenJwt(UserEntity user) {
    return Jwts.builder()
        .setSubject(user.getEmail())
        .setIssuer(String.valueOf(new Date()))
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    boolean equals = this.extractUsername(token).equals(userDetails.getUsername());
    boolean tokenExpired = this.isTokenExpired(token);
    return equals && !tokenExpired;
  }

  public String extractUsername(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

  public boolean isTokenExpired(String token) {
    return this.extractExpriteAt(token).before(new Date());

  }

  public Date extractExpriteAt(String token) {
    Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    return claims.getExpiration();
  }

}
