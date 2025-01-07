package com.firstProjectJava.first.project.Java.services;

import com.firstProjectJava.first.project.Java.dtos.CartDto;

import java.util.List;


public interface BaseService<T, I> {
  T create(T data);

  T findOne(I id);

  List<T> findAll();

  void remove(I id);

  void update(I id, T data);
}
