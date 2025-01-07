package com.firstProjectJava.first.project.Java.repositories;

import com.firstProjectJava.first.project.Java.models.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
}
