package org.alibou.demo.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {


    // grouping the exceptions
    @ExceptionHandler({RuntimeException.class, Exception.class, ArithmeticException.class})
    public ResponseEntity<?> handleException() {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("123");
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> handleException(EntityNotFoundException exp) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleException(MethodArgumentNotValidException exp) {
        Map<String, String> errors = new HashMap<>();
        // errorCode (dictionary): fieldName
        // 1: firstname
        // 3: email
        // 4: email
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(errorMessage, fieldName);
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);

    }
}
