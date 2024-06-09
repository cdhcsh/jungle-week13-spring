package org.jungle.code_post.error.post;

import org.jungle.code_post.error.CustomGlobalException;
import org.springframework.http.HttpStatus;

public class PostPasswordIncorrectException extends CustomGlobalException {
     public PostPasswordIncorrectException(){
         super(HttpStatus.BAD_REQUEST,"failed","post password was incorrect");
     }
}