package org.jungle.code_post.common.advice;

import org.jungle.code_post.common.dto.ApiResponseDTO;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@RestControllerAdvice(basePackages = {
        "org.jungle.code_post.post",
        "org.jungle.code_post.member",
        "org.jungle.code_post.comment"})
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public ApiResponseDTO<?> beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                             Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                             ServerHttpResponse response) {

        return ApiResponseDTO.builder()
                .data(body)
                .build();
    }
}
