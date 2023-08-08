package com.example.rentalcar.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserExistsException(UserExistsException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setDescription("Email/CNH/CPF já Existem");
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setDescription("Credenciais incorretas");
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(DataNotFoundException ex, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setDescription("Dado não encontrado");
        response.setMessage(ex.getMessage());
        response.setTimestamp(new Date());

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidations(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        Map mapErrors = new HashMap();

        ex.getBindingResult().getFieldErrors().stream().forEach(
                e -> mapErrors.put(e.getField(), e.getDefaultMessage())
        );

        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setDescription("Erro de Validação");
        response.setMessage(mapErrors.values().toString());
        response.setTimestamp(new Date());

        return ResponseEntity.status(status).body(response);
    }
}
