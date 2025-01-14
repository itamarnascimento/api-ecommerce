package com.firstProjectJava.first.project.Java.controllers;

import com.firstProjectJava.first.project.Java.dtos.CartDto;
import com.firstProjectJava.first.project.Java.dtos.ResponseDto;
import com.firstProjectJava.first.project.Java.services.BaseService;
import com.firstProjectJava.first.project.Java.services.cart.CartService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
  public ResponseEntity<ResponseDto<CartDto>> create(@RequestBody @Valid CartDto data) {
    CartDto cartDto = cartService.create(data);
    ResponseDto<CartDto> cartCreatedSuccessfully = ResponseDto.of("Cart created successfully", cartDto);

    URI uri = UriComponentsBuilder.fromPath("/cart/{id}" ).buildAndExpand(cartDto.id()).toUri();
    return ResponseEntity.created(uri).body(cartCreatedSuccessfully);
  }

  @GetMapping("{id}")
  public ResponseEntity<ResponseDto<CartDto>> findOne(@PathVariable("id") Integer id) {
    CartDto cartDto = cartService.findOne(id);
    return ResponseEntity.ok(ResponseDto.of("Find one Cart", cartDto));
  }

  @GetMapping
  public Page<CartDto> findAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
    return cartService.findAll(page, size);
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
