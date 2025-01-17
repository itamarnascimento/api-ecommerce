package com.firstProjectJava.first.project.Java.services.cart;

import com.firstProjectJava.first.project.Java.dtos.CartItemDto;
import com.firstProjectJava.first.project.Java.dtos.ResponseCartItems;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionNotFound;
import com.firstProjectJava.first.project.Java.models.CartItemEntity;
import com.firstProjectJava.first.project.Java.repositories.CartItemRepository;
import com.firstProjectJava.first.project.Java.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {

  private final CartItemRepository cartItemRepository;
  private final CartService cartService;
  private final ProductService productService;


  public ResponseCartItems create(CartItemDto data) {
    cartService.findOne(data.cartId());

    CartItemEntity cartItemEntity = new CartItemEntity(data);

    productService.validPriceAndQuantity(data.product().id(), data.quantity());
    return this.toDto(cartItemRepository.save(cartItemEntity));
  }


  public ResponseCartItems findOne(Integer id) {
    CartItemEntity cartItem = cartItemRepository.findById(id).orElseThrow(() -> new ExceptionNotFound("Item not found"));
    return this.toDto(cartItem);
  }


  public Page<CartItemDto> findAll(Integer pageIndex, Integer size) {
    Pageable page = PageRequest.of(pageIndex, size);
    return cartItemRepository.findAll(page).map(CartItemDto::new);

  }

  public Page<ResponseCartItems> findAllCartById(Integer cartId, Integer pageIndex, Integer size) {
    Pageable page = PageRequest.of(pageIndex, size);
    return cartItemRepository.findAllCartById(cartId,page).map(ResponseCartItems::new);

  }


  public void remove(Integer id) {
    this.findOne(id);
    cartItemRepository.deleteById(id);
  }


  public void update(Integer id, CartItemDto data) {
    CartItemEntity cartItemEntity = new CartItemEntity(this.findOne(id));
    productService.validIfQuantityProductEnough(id, data.quantity());

    cartItemEntity.setQuantity(data.quantity());

    cartItemRepository.save(cartItemEntity);
  }

  private ResponseCartItems toDto(CartItemEntity cartItemEntity) {
    return new ResponseCartItems(cartItemEntity);
  }


}
