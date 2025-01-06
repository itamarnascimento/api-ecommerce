package com.firstProjectJava.first.project.Java.dtos;

import java.time.LocalDateTime;

public record TokenJwtDto(
    String accessToken,
    LocalDateTime expiresAt,
    String refreshToken
) {

}
