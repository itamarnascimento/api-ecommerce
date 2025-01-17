package com.firstProjectJava.first.project.Java.errors;

import com.firstProjectJava.first.project.Java.dtos.ErrorResponse;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionCannotRemove;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionInsufficientQuantity;
import com.firstProjectJava.first.project.Java.exceptions.ExceptionNotFound;
import com.firstProjectJava.first.project.Java.exceptions.ExecptionInvalidValue;
import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {


  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse<String>> handleException(Exception e) {
    return ResponseEntity.status(500).body(new ErrorResponse<>(e.getMessage()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse<?>> handleException(MethodArgumentNotValidException e) {
    var errors = e.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
    return ResponseEntity.status(400).body(new ErrorResponse<>(errors));
  }

  @ExceptionHandler(ExceptionNotFound.class)
  public ResponseEntity<ErrorResponse<?>> handleException(ExceptionNotFound e) {
    return ResponseEntity.status(404).body(new ErrorResponse<>(e.getMessage()));
  }

  @ExceptionHandler(ExceptionCannotRemove.class)
  public ResponseEntity<ErrorResponse<?>> handleException(ExceptionCannotRemove e) {
    return ResponseEntity.status(409).body(new ErrorResponse<>(e.getMessage()));
  }

  @ExceptionHandler({ExpiredJwtException.class, ClaimJwtException.class})
  public ResponseEntity<ErrorResponse<?>> handleException(ClaimJwtException e) {
    return ResponseEntity.status(403).body(new ErrorResponse<>("Token Expired " + e.getMessage()));
  }

  @ExceptionHandler(ExceptionInsufficientQuantity.class)
  public ResponseEntity<ErrorResponse<?>> handleException(ExceptionInsufficientQuantity e) {
    return ResponseEntity.status(400).body(new ErrorResponse<>(e.getMessage()));
  }
  @ExceptionHandler(ExecptionInvalidValue.class)
  public ResponseEntity<ErrorResponse<?>> handleException(ExecptionInvalidValue e) {
    return ResponseEntity.status(400).body(new ErrorResponse<>(e.getMessage()));
  }
}



