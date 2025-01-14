package com.firstProjectJava.first.project.Java.services.product;

import com.firstProjectJava.first.project.Java.dtos.ProductDto;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionInsufficientQuantity;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionNotFound;
import com.firstProjectJava.first.project.Java.exceptions.ExecptionInvalidValue;
import com.firstProjectJava.first.project.Java.models.CategoryEntity;
import com.firstProjectJava.first.project.Java.models.ProductEntity;
import com.firstProjectJava.first.project.Java.repositories.ProductRepository;
import com.firstProjectJava.first.project.Java.services.category.CategoryService;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final CategoryService categoryService;
  private static final ExceptionNotFound EXCEPTION_NOT_FOUND = new ExceptionNotFound("Product");


  @Transient
  public ProductDto create(ProductDto productDto) {
    ProductEntity productEntity = new ProductEntity(productDto);

    CategoryEntity categoryEntity = new CategoryEntity(categoryService.findOne(productDto.categoryId()));
    productEntity.setCategory(categoryEntity);
    ProductEntity save = productRepository.save(productEntity);
    return new ProductDto(save);
  }

  public ProductDto findOne(Integer id) {
    return productRepository.findById(id).map(ProductDto::new).orElseThrow(() -> EXCEPTION_NOT_FOUND);
  }

  public Page<ProductDto> findAll(Integer pageIndex, Integer size) {
    Pageable page = PageRequest.of(pageIndex, size);
    return productRepository.findAll(page).map(ProductDto::new);
  }

  public void remove(Integer id) {
    ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> EXCEPTION_NOT_FOUND);
    productRepository.delete(productEntity);
  }

  public ProductDto update(Integer id, ProductDto productDto) {
    ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> EXCEPTION_NOT_FOUND);

    productEntity.setName(productDto.name());
    productEntity.setDescription(productDto.description());
    productEntity.setPrice(productDto.price());
    productEntity.setQuantity(productDto.quantity());

    return new ProductDto(productRepository.save(productEntity));
  }

  public List<ProductEntity> findAllByCategoryId(Integer id) {
    return productRepository.findAllByCategoryId(id);
  }

  public void validPriceAndQuantity(int id, int quantity) {
    ProductDto productDto = this.findOne(id);
    this.validIfQuantityProductEnough(id, quantity);
    this.validIfPriceNotNegativeOrZero(productDto);
  }

  public void validIfPriceNotNegativeOrZero(ProductDto productDto) {
    if (productDto.price() <= 0) {
      throw new ExecptionInvalidValue("Invalid price");
    }
  }

  public void validIfQuantityProductEnough(int id, int quantity) {
    ProductDto productDto = this.findOne(id);
    if (productDto.quantity() < quantity) {
      throw new ExceptionInsufficientQuantity();
    }
  }


}
