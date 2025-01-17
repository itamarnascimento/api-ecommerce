package com.firstProjectJava.first.project.Java.controllers;

import com.firstProjectJava.first.project.Java.dtos.CategoryDto;
import com.firstProjectJava.first.project.Java.dtos.ResponseDto;
import com.firstProjectJava.first.project.Java.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping
  public CategoryDto create(@RequestBody CategoryDto data) {
    return categoryService.create(data);
  }

  @GetMapping("{id}")
  public ResponseEntity<ResponseDto<CategoryDto>> findOne(@PathVariable("id") Integer id) {
    return ResponseEntity.ok(ResponseDto.of("Find one category", categoryService.findOne(id)));
  }


  @GetMapping
  public ResponseEntity<ResponseDto<Page<CategoryDto>>> findAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
    return ResponseEntity.ok(ResponseDto.of("Find All category", categoryService.findAll(page, size)));
  }

  @PutMapping("{id}")
  public ResponseEntity<ResponseDto<?>> update(@PathVariable("id") Integer id, CategoryDto categoryDto) {
    categoryService.update(id, categoryDto);
    return ResponseEntity.ok(ResponseDto.of("category updated successfully"));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<ResponseDto<?>> remove(@PathVariable("id") Integer id) {
    categoryService.remove(id);
    return ResponseEntity.ok(ResponseDto.of("category removed successfully"));
  }

}
