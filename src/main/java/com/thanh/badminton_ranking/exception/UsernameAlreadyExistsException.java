package com.thanh.badminton_ranking.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class UsernameAlreadyExistsException extends  RuntimeException{
   public UsernameAlreadyExistsException(String message){
       super(String.format("Username '%s' is already taken.",message));
   }
}
