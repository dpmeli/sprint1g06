package com.bootcamp.be_java_hisp_w16_g06.exceptions;

import com.bootcamp.be_java_hisp_w16_g06.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> catchUserNotFound(UserNotFoundException e){
        Response exceptionBlogDTO = new Response(e.getMessage(), 404);
        return new ResponseEntity<>(exceptionBlogDTO, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(FollowedNotFounException.class)
    public ResponseEntity<Response> catchFollowedNotFound(FollowedNotFounException e){
        Response exceptionBlogDTO = new Response(e.getMessage(), 404);
        return new ResponseEntity<>(exceptionBlogDTO, HttpStatus.NOT_FOUND);

    }

}
