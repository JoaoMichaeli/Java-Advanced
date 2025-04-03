package br.com.fiap.fin_money_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestControllerAdvice
public class ValidationHandler{

    record ValidationError(String field, String message){
        public ValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationError> handler(MethodArgumentNotValidException e){
        return e.getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .toList();
    };
}
