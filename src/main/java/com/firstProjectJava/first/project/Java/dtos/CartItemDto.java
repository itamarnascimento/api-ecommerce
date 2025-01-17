package com.firstProjectJava.first.project.Java.dtos;

import com.firstProjectJava.first.project.Java.models.CartItemEntity;

public record CartItemDto(
    Integer id,
    ProductDto product,
    Integer quantity,
    Integer cartId
) {

  public CartItemDto(CartItemEntity cartItemEntity) {
    this(cartItemEntity.getId(), new ProductDto(cartItemEntity.getProductEntity()),
        cartItemEntity.getQuantity(), cartItemEntity.getCart().getId());
  }

}
