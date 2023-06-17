package com.example.hotelmanagementapi.exception;

import com.example.hotelmanagementapi.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse> responseNotFoundException(UserAlreadyExistException ex){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("User already register");
        apiResponse.setError_status("Email exists");
        return new  ResponseEntity<>(apiResponse, HttpStatus.FOUND);
    }
}
