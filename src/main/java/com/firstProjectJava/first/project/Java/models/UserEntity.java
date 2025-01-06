package com.firstProjectJava.first.project.Java.models;

import com.firstProjectJava.first.project.Java.dtos.UserDto;
import com.firstProjectJava.first.project.Java.models.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements UserDetails {


  @Id
  @GeneratedValue
  private Integer id;

  private String name;

  @Column(unique = true, nullable = false)

  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;
  private String status;
  private String token;


  public UserEntity(UserDto userDto) {
    this.id = userDto.id();
    this.name = userDto.name();
    this.email = userDto.email();
    this.password = userDto.password();
    this.role = userDto.role();
    this.status = userDto.status();
    this.token = userDto.token();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getUsername() {
    return this.email;
  }
}








