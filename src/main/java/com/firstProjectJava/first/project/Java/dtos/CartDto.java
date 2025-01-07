package com.firstProjectJava.first.project.Java.dtos;

import com.firstProjectJava.first.project.Java.models.CartEntity;
import com.firstProjectJava.first.project.Java.models.CartItemEntity;
import com.firstProjectJava.first.project.Java.models.UserEntity;

import java.util.List;

public record CartDto(
    Integer id,
    Integer userId,
    Double amount,
    List<CartItemDto> cartItems
) {

  public CartDto(CartEntity cartEntity) {
    this(cartEntity.getId(), cartEntity.getUser().getId(),
        cartEntity.getAmount(), cartEntity.getCartItems().stream().map(CartItemDto::new).toList());
  }
}
