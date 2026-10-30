package com.ecommerce.user.handler;

import com.ecommerce.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorResponse> userNotFound(UserNotFoundException exception){
        UserErrorResponse response = new UserErrorResponse();
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.setMessage(exception.getMessage());
        response.setTimestamp(new Date());
        return  new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
