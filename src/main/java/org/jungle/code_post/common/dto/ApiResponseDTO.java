package org.jungle.code_post.common.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO<T> {
    private Integer code;
    private T data;
    private String message;
}
