package com.firstProjectJava.first.project.Java.errors;

import com.firstProjectJava.first.project.Java.dtos.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {



  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse<String>> handleException(Exception e){
    return ResponseEntity.status(500).body(new ErrorResponse<>(e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse<?>> handleException(MethodArgumentNotValidException e){
    return ResponseEntity.status(400).body(new ErrorResponse<>(e.getDetailMessageArguments()));
  }


}



