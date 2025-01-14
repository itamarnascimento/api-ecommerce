package com.firstProjectJava.first.project.Java.models;

import com.firstProjectJava.first.project.Java.dtos.CartDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
  @Id
  @GeneratedValue
  private Integer id;

  @OneToOne
  private UserEntity user;
  private Double amount;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.REMOVE)
  private List<CartItemEntity> cartItems;

  public CartEntity(CartDto cartDto) {
    this.id = cartDto.id();
    this.user = new UserEntity();
    this.user.setId(cartDto.userId());
    this.amount = cartDto.amount();
    this.cartItems = new ArrayList<>();
  }
}
