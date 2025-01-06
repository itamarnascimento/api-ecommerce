package com.firstProjectJava.first.project.Java.repositories;

import com.firstProjectJava.first.project.Java.models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
}
