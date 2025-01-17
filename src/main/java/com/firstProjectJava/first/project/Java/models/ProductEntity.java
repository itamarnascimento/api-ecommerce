package com.firstProjectJava.first.project.Java.models;

import com.firstProjectJava.first.project.Java.dtos.ProductDto;
import com.firstProjectJava.first.project.Java.dtos.ResponseCartItems;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductEntity {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String description;
  private Double price;
  private Integer quantity;

  @ManyToOne
  @JoinColumn(name = "category", referencedColumnName = "id")
  private CategoryEntity category;

  public ProductEntity(ProductDto productDto) {
    this.id = productDto.id();
    this.name = productDto.name();
    this.description = productDto.description();
    this.price = productDto.price();
    this.quantity = productDto.quantity();
    this.category = new CategoryEntity();
    this.category.setId(productDto.categoryId());

  }

  public ProductEntity(ResponseCartItems prodResponseCartItems){
    this.id = prodResponseCartItems.productId();
    this.name = prodResponseCartItems.name();
    this.price = prodResponseCartItems.price();
    this.quantity = prodResponseCartItems.quantity();
  }
}
