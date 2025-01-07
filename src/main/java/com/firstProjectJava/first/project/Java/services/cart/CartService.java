package com.firstProjectJava.first.project.Java.services.cart;

import com.firstProjectJava.first.project.Java.dtos.CartDto;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionNotFound;
import com.firstProjectJava.first.project.Java.models.CartEntity;
import com.firstProjectJava.first.project.Java.repositories.CartRepository;
import com.firstProjectJava.first.project.Java.services.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService implements BaseService<CartDto, Integer> {
  private final CartRepository cartRepository;

  public CartDto create(CartDto cartDto) {
    CartEntity cartEntity = new CartEntity(cartDto);
    return this.toDto(cartRepository.save(cartEntity));
  }

  @Override
  public CartDto findOne(Integer id) {
    return this.toDto(cartRepository.findById(id).orElseThrow(() -> new ExceptionNotFound("Cart")));
  }

  @Override
  public List<CartDto> findAll() {
    return (cartRepository.findAll().stream().map(this::toDto).toList());
  }

  @Override
  public void remove(Integer id) {
    this.findOne(id);
    cartRepository.deleteById(id);
  }

  @Override
  public void update(Integer id, CartDto data) {
    CartEntity cartEntity = new CartEntity(this.findOne(id));

    cartEntity.setAmount(data.amount());
    cartRepository.save(cartEntity);
  }

  private CartDto toDto(CartEntity cartEntity) {
    return new CartDto(cartEntity);
  }

}

