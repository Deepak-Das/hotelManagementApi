package com.example.hotelmanagementapi.exception;

import com.example.hotelmanagementapi.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ApiResponse> AlreadyExistException(UserAlreadyExistException ex){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("User already register");
        apiResponse.setError_status("Email exists");
        return new  ResponseEntity<>(apiResponse, HttpStatus.FOUND);
    }
    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ApiResponse> responseNotFoundException(UserNotExistException ex){
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("User Not register");
        apiResponse.setError_status("Email Not exists");
        return new  ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
