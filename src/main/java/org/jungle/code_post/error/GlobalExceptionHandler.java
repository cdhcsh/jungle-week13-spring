package org.jungle.code_post.error;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.common.dto.ApiResponseDTO;
import org.jungle.code_post.error.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomGlobalException.class)
    public ApiResponseDTO<?> customGlobalExceptionHandler(CustomGlobalException e, HttpServletResponse response){
        log.debug("[resolved]:{}",e.getClass());
        response.setStatus(e.getHttpStatus().value());
        return ApiResponseDTO.builder()
                .code(e.getErrorCode())
                .message(e.getErrorMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDTO<?> notValidExceptionHandler(MethodArgumentNotValidException e){
        log.debug("resolved:[{}: {}]",e.getClass(),e.getMessage());
        return ApiResponseDTO.builder()
                .code("failed")
                .data(e.getBindingResult().getFieldErrors().stream()
                        .map((fieldError ->
                            ErrorResponseDTO.builder()
                                    .field(fieldError.getField())
                                    .code(fieldError.getCode())
                                    .message(fieldError.getDefaultMessage())
                                    .build()
                        )).collect(Collectors.toList())
                        )
                .message("bad request")
                .build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponseDTO<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e){
        return ApiResponseDTO.builder()
                .code("failed")
                .message("올바르지 않은 입력입니다.")
                .build();
    }
}
