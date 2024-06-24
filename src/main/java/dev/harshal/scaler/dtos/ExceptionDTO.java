package dev.harshal.scaler.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class ExceptionDTO {
    private HttpStatus errorcode;
    private String message;
   public ExceptionDTO(HttpStatus errorcode, String message){
        this.errorcode = errorcode;
        this.message = message;
    }
}
