package dev.harshal.scaler.exceptions;

import dev.harshal.scaler.dtos.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity(
                new ExceptionDTO(HttpStatus.NOT_FOUND,notFoundException.getMessage()),

                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    private ResponseEntity<ExceptionDTO> handleArrayIndexOutOfBoundException(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
        return new ResponseEntity(
                new ExceptionDTO(HttpStatus.NOT_FOUND,arrayIndexOutOfBoundsException.getMessage()),

                HttpStatus.NOT_FOUND);
    }

}
