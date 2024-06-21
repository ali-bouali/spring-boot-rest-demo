package org.alibou.demo.common.exception;


import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.alibou.demo.common.ErrorDetails;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> EntityNotFoundException(EntityNotFoundException ex,
      WebRequest request) {

    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails,
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<?> badRequestException(BadRequestException ex,
      WebRequest request) {

    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails,
        HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globalExceptionHandler(Exception ex,
      WebRequest request) {

    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails,
        HttpStatus.INTERNAL_SERVER_ERROR);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetails> handleValidationErrors(MethodArgumentNotValidException ex,
      WebRequest request) {
    List<String> errors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(fe -> String.format("Field '%s': %s", fe.getField(), fe.getDefaultMessage()))
        .collect(Collectors.toList());

    String combinedErrors = String.join(", ", errors);

    ErrorDetails errorDetails = new ErrorDetails(
        HttpStatus.BAD_REQUEST.value(),
        new Date(),
        "Validation failed",
        combinedErrors
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(AccessDeniedException.class)
  @ResponseBody
  public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex,
      HttpServletRequest request) {

    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.FORBIDDEN.value(),
        new Date(),
        ex.getMessage(),
        request.getRequestURI());
    return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
  }


  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    String message = ex.getMostSpecificCause()
        .getMessage();
    ErrorDetails errorDetails;
    if (message.contains("Cannot deserialize value of type") && message.contains("from String")
        && message.contains("not one of the values accepted for Enum class")) {
      String errorMessage = "The provided value is invalid. Accepted values are: MALE, " +
          "FEMALE, OTHER.";
      errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
          new Date(),
          errorMessage,
          request.getDescription(false));
    } else {
      errorDetails = new ErrorDetails(status.value(),
          new Date(),
          "Error processing request",
          request.getDescription(false));
    }
    return new ResponseEntity<>(errorDetails,
        HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody String annotationExceptionHandler(ConstraintViolationException exception) {
    return exception.getMessage();
  }

  @ExceptionHandler(EntityExistsException.class)
  public ResponseEntity<ErrorDetails> handleEntityExistsException(
      EntityExistsException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
        HttpStatus.CONFLICT.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false)
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(SubjectMaxLimitExceeded.class)
  public ResponseEntity<ErrorDetails> handleSubjectMaxLimitExceeded(SubjectMaxLimitExceeded ex,
      WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails,
        HttpStatus.NOT_FOUND);
  }
}
