package com.sp.library.Exception;

import com.sp.library.dto.ErrorResponseDto;
import com.sp.library.util.enums.CustomResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(value = UserRegisteredBeforeException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponseDto handleRegisteredBeforeException(UserRegisteredBeforeException ex){
        return new ErrorResponseDto
                (CustomResponseStatus.FAIL, ex.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDto HandleUserNotFoundException(UserNotFoundException ex){
        return new ErrorResponseDto(CustomResponseStatus.FAIL, ex.getMessage());
    }
}
