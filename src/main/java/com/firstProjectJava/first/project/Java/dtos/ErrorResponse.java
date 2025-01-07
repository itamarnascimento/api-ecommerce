package com.firstProjectJava.first.project.Java.dtos;

public record ErrorResponse<T>(T message, boolean success) {
  public ErrorResponse(T message) {
    this(message, false);
  }
}