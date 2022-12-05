package com.s5.javaback.advice;

import com.s5.javaback.util.constants.DateFormatConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception ex) {
        final Map<String, String> body = new LinkedHashMap<>();

        body.put("timestamp", timestamp());
        body.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(body);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final Map<String, String> body = new LinkedHashMap<>();

        body.put("timestamp", timestamp());

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> body.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(body, headers, status);
    }

    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        final Map<Object, Object> body = new LinkedHashMap<>();

        body.put("timestamp", timestamp());

        ex.getConstraintViolations().forEach(error -> {
            body.put("message", error.getMessage());
            body.put(error.getPropertyPath(), error.getInvalidValue());
        });

        return ResponseEntity.badRequest().body(body);
    }

    private String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateFormatConstants.DATE_TIME_FORMAT));
    }

}
