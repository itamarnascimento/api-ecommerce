package com.firstProjectJava.first.project.Java.services.category;

import com.firstProjectJava.first.project.Java.dtos.CategoryDto;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionCannotRemove;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionNotFound;
import com.firstProjectJava.first.project.Java.models.CategoryEntity;
import com.firstProjectJava.first.project.Java.models.ProductEntity;
import com.firstProjectJava.first.project.Java.repositories.CategoryRepository;
import com.firstProjectJava.first.project.Java.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final ProductRepository productRepository;

  private static final ExceptionNotFound EXCEPTION_NOT_FOUND = new ExceptionNotFound("Category");

  public CategoryDto create(CategoryDto categoryDto) {
    return new CategoryDto(categoryRepository.save(new CategoryEntity(categoryDto)));
  }

  public CategoryDto findOne(Integer id) {
    return categoryRepository.findById(id).map(CategoryDto::new).orElseThrow(() -> EXCEPTION_NOT_FOUND);
  }

  public void remove(Integer id) {
    CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> EXCEPTION_NOT_FOUND);
    List<ProductEntity> productEntity = productRepository.findAllByCategoryId(id);

    if (!productEntity.isEmpty()) {
      throw new ExceptionCannotRemove("There are products linked to the category");
    }

    categoryRepository.delete(categoryEntity);
  }

  public void update(Integer id, CategoryDto categoryDto) {
    CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() -> EXCEPTION_NOT_FOUND);
    categoryEntity.setName(categoryDto.name());
    new CategoryDto(categoryRepository.save(categoryEntity));
  }

  public Page<CategoryDto> findAll(Integer pageIndex, Integer size) {
    Pageable page = PageRequest.of(pageIndex, size);
    return categoryRepository.findAll(page).map(CategoryDto::new);
  }
}
