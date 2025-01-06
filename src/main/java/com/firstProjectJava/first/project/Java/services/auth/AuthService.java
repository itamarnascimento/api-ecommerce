package com.firstProjectJava.first.project.Java.services.auth;

import com.firstProjectJava.first.project.Java.configurations.JwtSecurity;
import com.firstProjectJava.first.project.Java.dtos.TokenJwtDto;
import com.firstProjectJava.first.project.Java.dtos.UserDto;
import com.firstProjectJava.first.project.Java.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service

public class AuthService {

  private final JwtSecurity jwtSecurity;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthService(JwtSecurity jwtSecurity, UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.jwtSecurity = jwtSecurity;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public TokenJwtDto authenticate(UserDto userDto) {
    var user = userRepository.findByEmail(userDto.email());

    if (user.isEmpty() || !passwordEncoder.matches(userDto.password(), user.get().getPassword())) {
      throw new RuntimeException("password or email invalid");
    }

    String accessToken = jwtSecurity.generateTokenJwt(user.get());
    LocalDateTime expiresAt = jwtSecurity
        .extractExpriteAt(accessToken)
        .toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime();
    return new TokenJwtDto(accessToken, expiresAt, accessToken);
  }
}
