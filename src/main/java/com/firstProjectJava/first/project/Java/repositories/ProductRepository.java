package com.firstProjectJava.first.project.Java.repositories;

import com.firstProjectJava.first.project.Java.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
  List<ProductEntity> findAllByCategoryId(Integer id);
}
