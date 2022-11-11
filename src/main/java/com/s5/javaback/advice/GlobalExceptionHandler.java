package com.s5.javaback.advice;

import com.s5.javaback.util.constants.DateFormatConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception ex) {
        final Map<String, String> body = new LinkedHashMap<>();

        body.put("timestamp", timestamp());
        body.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(body);
    }

    private String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DateFormatConstants.DATE_TIME_FORMAT));
    }

}
