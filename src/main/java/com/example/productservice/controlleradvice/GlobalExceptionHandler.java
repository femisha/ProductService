package com.example.productservice.controlleradvice;

import com.example.productservice.dtos.ErrorDto;
import com.example.productservice.dtos.ResponseStatus;
import com.example.productservice.exception.ProductNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Controller
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());
        errorDto.setStatus(ResponseStatus.FAILURE);

        ResponseEntity<ErrorDto> errorDtoResponseEntity=new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));

        return errorDtoResponseEntity;
    }
}
