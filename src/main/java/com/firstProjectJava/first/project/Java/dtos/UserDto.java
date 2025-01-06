package com.firstProjectJava.first.project.Java.dtos;

import com.firstProjectJava.first.project.Java.models.UserEntity;
import com.firstProjectJava.first.project.Java.models.enums.Role;
import jakarta.validation.constraints.*;

public record UserDto(
    Integer id,

    @NotBlank(message = "Name is required")
    String name,

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email,

    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    String password,

    @NotNull(message = "Role is required")
    Role role,
    String status,
    String token) {

  public UserDto(UserEntity userEntity) {
    this(userEntity.getId(), userEntity.getName(), userEntity.getEmail(),
        userEntity.getPassword(), userEntity.getRole(), userEntity.getStatus(), userEntity.getToken());

  }

}
