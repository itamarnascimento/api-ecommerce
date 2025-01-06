package com.firstProjectJava.first.project.Java.services.user;

import com.firstProjectJava.first.project.Java.dtos.UserDto;
import com.firstProjectJava.first.project.Java.models.UserEntity;
import com.firstProjectJava.first.project.Java.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;


  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UserDto create(UserDto data) {

    UserEntity userEntity = new UserEntity(data);

    var user = userRepository.findByEmail(data.email());
    if (user.isPresent()) {
      throw new RuntimeException("User already exists");
    }

    userEntity.setPassword(passwordEncoder.encode(data.password()));
    UserEntity save = userRepository.save(userEntity);
    return new UserDto(save);
  }

  public UserDto findOne(Integer id) {
    return userRepository.findById(id).map(UserDto::new).orElseThrow(() -> new RuntimeException("User not found"));
  }

  public List<UserDto> findAll() {
    return userRepository.findAll().stream().map(UserDto::new).toList();
  }

  public void remove(Integer id) {
    if (!userRepository.existsById(id)) {
      throw new RuntimeException("User not found");
    }

    userRepository.deleteById(id);
  }
}
