package com.firstProjectJava.first.project.Java.services;

import com.firstProjectJava.first.project.Java.dtos.CartDto;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BaseService<T, I> {
  T create(T data);

  T findOne(I id);

  Page<T> findAll(Integer page, Integer size);

  void remove(I id);

  void update(I id, T data);
}
