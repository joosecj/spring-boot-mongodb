package com.devsuperior.workshopmongo.controllers.exception;

import com.devsuperior.workshopmongo.services.exeptions.DataBaseException;
import com.devsuperior.workshopmongo.services.exeptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> ResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError error = new StandardError(System.currentTimeMillis(), status.value(), "Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}
