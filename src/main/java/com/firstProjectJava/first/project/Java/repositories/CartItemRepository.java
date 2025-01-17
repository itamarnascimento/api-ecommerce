package com.firstProjectJava.first.project.Java.repositories;

import com.firstProjectJava.first.project.Java.models.CartItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {
  Page<CartItemEntity> findAllCartById(Integer cartId, Pageable page);
}
