package com.firstProjectJava.first.project.Java.services.cart;

import com.firstProjectJava.first.project.Java.dtos.CartItemDto;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionNotFound;
import com.firstProjectJava.first.project.Java.models.CartItemEntity;
import com.firstProjectJava.first.project.Java.repositories.CartItemRepository;
import com.firstProjectJava.first.project.Java.services.BaseService;
import com.firstProjectJava.first.project.Java.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService implements BaseService<CartItemDto, Integer> {

  private final CartItemRepository cartItemRepository;
  private final CartService cartService;
  private final ProductService productService;

  @Override
  public CartItemDto create(CartItemDto data) {
    cartService.findOne(data.cartId());

    CartItemEntity cartItemEntity = new CartItemEntity(data);

    productService.validPriceAndQuantity(data.product().id(), data.quantity());
    return this.toDto(cartItemRepository.save(cartItemEntity));
  }

  @Override
  public CartItemDto findOne(Integer id) {

    CartItemEntity cartItem = cartItemRepository.findById(id).orElseThrow(() -> new ExceptionNotFound("Cart item"));
    return this.toDto(cartItem);
  }

  @Override
  public List<CartItemDto> findAll() {
    return cartItemRepository.findAll().stream().map(this::toDto).toList();

  }

  @Override
  public void remove(Integer id) {
    this.findOne(id);
    cartItemRepository.deleteById(id);
  }

  @Override
  public void update(Integer id, CartItemDto data) {
    CartItemEntity cartItemEntity = new CartItemEntity(this.findOne(id));
    productService.validIfQuantityProductEnough(id, data.quantity());

    cartItemEntity.setQuantity(data.quantity());

    cartItemRepository.save(cartItemEntity);
  }

  private CartItemDto toDto(CartItemEntity cartItemEntity) {
    return new CartItemDto(cartItemEntity);
  }


}
