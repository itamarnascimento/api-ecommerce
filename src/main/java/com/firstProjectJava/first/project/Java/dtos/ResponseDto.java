package com.firstProjectJava.first.project.Java.dtos;

public record ResponseDto<T>(
    boolean success,
    String message,
    T data
) {

  public static <T> ResponseDto<T> of(String message, T data) {
    return new ResponseDto<>(true, message, data);
  }

  public static ResponseDto<?> of(String message) {
    return new ResponseDto<>(true, message, null);
  }
}
