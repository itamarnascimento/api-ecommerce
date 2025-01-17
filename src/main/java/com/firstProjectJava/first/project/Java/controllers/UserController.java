package com.firstProjectJava.first.project.Java.controllers;

import com.firstProjectJava.first.project.Java.dtos.ResponseDto;
import com.firstProjectJava.first.project.Java.dtos.TokenJwtDto;
import com.firstProjectJava.first.project.Java.dtos.UserDto;
import com.firstProjectJava.first.project.Java.services.auth.AuthService;
import com.firstProjectJava.first.project.Java.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@ResponseBody
@RequestMapping("users")
public class UserController {
  private final UserService userService;
  private final AuthService authService;

  public UserController(UserService userService, AuthService authService) {
    this.userService = userService;
    this.authService = authService;
  }

  @PostMapping
  public UserDto create(@RequestBody @Valid UserDto data) {
    return userService.create(data);
  }

  @GetMapping("{id}")
  public UserDto findOne(@PathVariable("id") Integer id) {
    return userService.findOne(id);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ResponseDto<?>> remove(@PathVariable("id") Integer id) {
    userService.remove(id);
    return ResponseEntity.ok(ResponseDto.of("User removed"));
  }

  @PostMapping("auth")
  public ResponseEntity<ResponseDto<TokenJwtDto>> authenticate(@RequestBody UserDto userDto) {
    TokenJwtDto authenticate = authService.authenticate(userDto);

    return ResponseEntity.ok(ResponseDto.of("Token gerado",authenticate));
  }
}
