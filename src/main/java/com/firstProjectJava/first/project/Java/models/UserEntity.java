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


  public UserEntity() {

  }

  public UserEntity(Integer id, String name, String email, String password, Role role, String status, String token) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.status = status;
    this.token = token;
  }

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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}








