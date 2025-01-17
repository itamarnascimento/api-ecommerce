package com.firstProjectJava.first.project.Java.dtos;

import com.firstProjectJava.first.project.Java.models.CartEntity;

public record CartDto(
    Integer id,
    Integer userId,
    Double amount
) {

  public CartDto(CartEntity cartEntity) {
    this(cartEntity.getId(), cartEntity.getUser().getId(),
        cartEntity.getAmount());
  }
}
