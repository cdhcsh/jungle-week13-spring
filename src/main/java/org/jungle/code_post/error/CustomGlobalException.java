package org.jungle.code_post.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class CustomGlobalException extends RuntimeException{
    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;
}
