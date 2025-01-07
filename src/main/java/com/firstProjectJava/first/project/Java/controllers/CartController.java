package com.firstProjectJava.first.project.Java.controllers;

import com.firstProjectJava.first.project.Java.dtos.CartDto;
import com.firstProjectJava.first.project.Java.dtos.ResponseDto;
import com.firstProjectJava.first.project.Java.services.BaseService;
import com.firstProjectJava.first.project.Java.services.cart.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("cart")
@RequiredArgsConstructor

public class CartController {

  private final CartService cartService;

  @PostMapping
  public ResponseEntity<ResponseDto<CartDto>> create(@RequestBody @Valid CartDto data, UriComponentsBuilder uriComponentsBuilder) {
    ResponseDto<CartDto> cartCreatedSuccessfully = ResponseDto.of("Cart created successfully", cartService.create(data));
    URI uri = uriComponentsBuilder.fromUri(URI.create("/cart/{id}")).buildAndExpand(cartCreatedSuccessfully.data().id()).toUri();
    return ResponseEntity.created(uri).body(cartCreatedSuccessfully);
  }

  @GetMapping("{id}")
  public ResponseEntity<ResponseDto<CartDto>> findOne(@PathVariable("id") Integer id) {
    CartDto cartDto = cartService.findOne(id);
    return ResponseEntity.ok(ResponseDto.of("Find one Cart", cartDto));
  }

  @GetMapping
  public ResponseEntity<ResponseDto<List<CartDto>>> findAll() {
    List<CartDto> list = cartService.findAll();
    return ResponseEntity.ok(ResponseDto.of("Find All Cart", list));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ResponseDto<?>> remove(@PathVariable("id") Integer id) {
    cartService.remove(id);
    return ResponseEntity.ok(ResponseDto.of("Cart removed successfully"));
  }

  @PutMapping("{id}")
  public ResponseEntity<ResponseDto<?>> update(@PathVariable("id") Integer id, CartDto data) {
    cartService.update(id, data);
    return ResponseEntity.ok(ResponseDto.of("Cart updated successfully"));
  }
}
