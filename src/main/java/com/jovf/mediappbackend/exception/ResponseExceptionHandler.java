package com.jovf.mediappbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice //Busca a otras anotaciones de tipo exception handler, de forma independientes gestionar errores
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    //Response entity genera la petición total a json - evita usar try catch y se centraliza
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> manejarTodasLasExceptions(Exception ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModeloNotFoundException.class)
    public ResponseEntity<ExceptionResponse> manejarModeloNotFoundException(ModeloNotFoundException ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String mensaje = ex.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage().concat(", ")).collect(Collectors.joining());
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(),mensaje,request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }
}
