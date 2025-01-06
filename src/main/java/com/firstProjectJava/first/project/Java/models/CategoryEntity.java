package com.firstProjectJava.first.project.Java.models;

import com.firstProjectJava.first.project.Java.dtos.CategoryDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryEntity {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private List<ProductEntity> products;

  public CategoryEntity(CategoryDto categoryDto) {
    this.id = categoryDto.id();
    this.name = categoryDto.name();
  }
}
