package com.firstProjectJava.first.project.Java.exceptions;

public class ExceptionInsufficientQuantity extends RuntimeException{
  public ExceptionInsufficientQuantity() {
    super("insufficient quantity");
  }
}
