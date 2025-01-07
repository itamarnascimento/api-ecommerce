package com.firstProjectJava.first.project.Java.repositories;

import com.firstProjectJava.first.project.Java.models.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {
}
