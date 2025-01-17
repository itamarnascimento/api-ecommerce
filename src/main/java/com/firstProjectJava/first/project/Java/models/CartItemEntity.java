package com.firstProjectJava.first.project.Java.models;

import com.firstProjectJava.first.project.Java.dtos.CartItemDto;
import com.firstProjectJava.first.project.Java.dtos.ResponseCartItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity {
  @Id
  @GeneratedValue
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private ProductEntity productEntity;
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "cart_id", referencedColumnName = "id")
  private CartEntity cart;

  public CartItemEntity(CartItemDto cartItemDto) {
    this.id = cartItemDto.id();
    this.productEntity = new ProductEntity(cartItemDto.product());
    this.quantity = cartItemDto.quantity();
    this.cart = new CartEntity();
    this.cart.setId(cartItemDto.cartId());
  }

  public CartItemEntity(ResponseCartItems responseCartItems) {
    this.id = responseCartItems.id();
    this.productEntity = new ProductEntity();
    this.productEntity.setId(responseCartItems.productId());
    this.quantity = responseCartItems.quantity();
    this.cart = new CartEntity();
    this.cart.setId(responseCartItems.cartId());
  }

}
