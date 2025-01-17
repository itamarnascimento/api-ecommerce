package com.firstProjectJava.first.project.Java.dtos;

import com.firstProjectJava.first.project.Java.models.CartItemEntity;

public record ResponseCartItems(

    Integer productId,
    String name,
    Double price,
    Integer quantity,
    Integer cartId,
    Integer id


) {

  public ResponseCartItems(CartItemEntity cartItemEntity) {
    this(cartItemEntity.getProductEntity().getId(), cartItemEntity.getProductEntity().getName(),
        cartItemEntity.getProductEntity().getPrice(), cartItemEntity.getQuantity(),
        cartItemEntity.getCart().getId(), cartItemEntity.getId());
  }

}
