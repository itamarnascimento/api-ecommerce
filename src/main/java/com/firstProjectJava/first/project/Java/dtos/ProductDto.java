package com.firstProjectJava.first.project.Java.dtos;

import com.firstProjectJava.first.project.Java.models.ProductEntity;
import jakarta.validation.constraints.*;

public record ProductDto(

    Integer id,
    @NotEmpty(message = "Name is required")
    String name,
    String description,

    @Positive(message = "Price must be greater than 0")
    Double price,

    Integer quantity,

    @NotNull(message = "Category is required")
    Integer categoryId
) {

  public ProductDto(ProductEntity productEntity) {
    this(productEntity.getId(),productEntity.getName(), productEntity.getDescription(),
        productEntity.getPrice(), productEntity.getQuantity(), productEntity.getCategory().getId());
  }

}
