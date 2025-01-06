package com.firstProjectJava.first.project.Java.models;

import com.firstProjectJava.first.project.Java.dtos.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "products")
@Entity
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
    this.name = productDto.name();
    this.description = productDto.description();
    this.price = productDto.price();
    this.quantity = productDto.quantity();
  }

}
