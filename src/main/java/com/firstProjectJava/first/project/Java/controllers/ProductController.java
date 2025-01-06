package com.firstProjectJava.first.project.Java.controllers;

import com.firstProjectJava.first.project.Java.dtos.ProductDto;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionNotFound;
import com.firstProjectJava.first.project.Java.services.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@ResponseBody
@RequestMapping("product")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @PostMapping
  public ProductDto create(@RequestBody @Valid ProductDto productDto) {
    return productService.create(productDto);
  }

  @GetMapping("{id}")
  public ProductDto findOne(@PathVariable("id") Integer id) throws ExceptionNotFound {
    return productService.findOne(id);
  }

  @GetMapping
  public Page<ProductDto> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    return productService.findAll(page, size);
  }

  @PutMapping("{id}")
  public ResponseEntity<ProductDto> update(@PathVariable("id") Integer id, @RequestBody @Valid ProductDto productDto) {
    return ResponseEntity.ok(productService.update(id, productDto));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> remove(@PathVariable("id") Integer id) {
    productService.remove(id);
    return ResponseEntity.ok().build();
  }

}
