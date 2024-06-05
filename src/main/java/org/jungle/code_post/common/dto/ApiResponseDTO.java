package org.jungle.code_post.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO<T> {
    private Integer code;
    private String message;
    private T data;
}
