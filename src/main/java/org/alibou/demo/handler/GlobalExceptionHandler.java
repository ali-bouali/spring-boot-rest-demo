package org.alibou.demo.handler;

import jakarta.persistence.EntityNotFoundException;
import org.alibou.demo.exception.BusinessException;
import org.alibou.demo.exception.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {


    // grouping the exceptions
    // @ExceptionHandler({RuntimeException.class, Exception.class, ArithmeticException.class})
    public ResponseEntity<?> handleException() {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("123");
    }


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> handleException(BusinessException exp) {

        Map<String, Object> errors = new HashMap<>();
        errors.put("ErrorCode", exp.getExceptionType().getCode());
        errors.put("ErrorDesc", exp.getExceptionType().getDesc());

        if (exp.getExceptionType() == ExceptionType.PAYMENT_EXCEPTION) {
            handlePaymentException(exp);
        }

        return ResponseEntity
                .status(exp.getStatus())
                .body(errors);
    }

    private void handlePaymentException(BusinessException exp) {

    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> handleException(EntityNotFoundException exp) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exp.getMessage());
    }

    @ExceptionHandler({DisabledException.class})
    public ResponseEntity<?> handleException(DisabledException exp) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("User account is disabled, please contact your admin.");
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
