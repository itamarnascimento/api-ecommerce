package com.firstProjectJava.first.project.Java.exceptions;

public class ExceptionNotFound extends RuntimeException {
  public ExceptionNotFound(String prefix) {
    super(prefix + " not found");
  }
}
