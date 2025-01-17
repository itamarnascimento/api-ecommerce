package com.firstProjectJava.first.project.Java.controllers;

import com.firstProjectJava.first.project.Java.dtos.CartItemDto;
import com.firstProjectJava.first.project.Java.dtos.ResponseCartItems;
import com.firstProjectJava.first.project.Java.dtos.ResponseDto;
import com.firstProjectJava.first.project.Java.services.cart.CartItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carts/items")
@RequiredArgsConstructor
public class cartItemsController {
  private final CartItemService cartItemService;

  @PostMapping
  ResponseCartItems create(@RequestBody CartItemDto date) {
    return cartItemService.create(date);
  }

  @GetMapping("{id}")
  ResponseEntity<ResponseDto<ResponseCartItems>> findOne(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(ResponseDto.of("Find One Item", cartItemService.findOne(id)));
  }

  @GetMapping
  Page<ResponseCartItems> findAllByCartId(@RequestParam Integer cartId, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
    return cartItemService.findAllCartById(cartId, page, size);

  }

  @PutMapping("{id}")
  ResponseEntity<ResponseDto<CartItemDto>> update(@PathVariable("id") Integer id, @RequestBody @Valid CartItemDto data) {
    cartItemService.update(id, data);
    return ResponseEntity.ok(ResponseDto.of("Quantity updated successfully", null));
  }

  @DeleteMapping("{id}")
  void remove(@PathVariable("id") Integer id) {
    this.remove(id);
    ResponseEntity.ok().build();
  }
}
