package com.firstProjectJava.first.project.Java.repositories;

import com.firstProjectJava.first.project.Java.dtos.UserDto;
import com.firstProjectJava.first.project.Java.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  Optional<UserEntity> findByEmail(String email);
}
