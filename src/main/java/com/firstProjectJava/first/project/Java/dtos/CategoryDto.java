package com.firstProjectJava.first.project.Java.dtos;

import com.firstProjectJava.first.project.Java.models.CategoryEntity;
import jakarta.validation.constraints.NotBlank;


public record CategoryDto(
    Integer id,
    @NotBlank(message = "Name is mandatory")
    String name
) {

  public CategoryDto(CategoryEntity categoryEntity) {
    this(categoryEntity.getId(), categoryEntity.getName());
  }
}
