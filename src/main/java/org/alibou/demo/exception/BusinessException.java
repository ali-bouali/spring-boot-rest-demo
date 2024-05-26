package org.alibou.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.alibou.demo.exception.ExceptionType.NO_DEFINED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class BusinessException extends RuntimeException {

    private ExceptionType exceptionType = NO_DEFINED;
    private HttpStatus status = BAD_REQUEST;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public BusinessException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public BusinessException(ExceptionType exceptionType, HttpStatus status) {
        this.exceptionType = exceptionType;
        this.status = status;
    }
}
