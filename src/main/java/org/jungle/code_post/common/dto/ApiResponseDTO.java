package org.jungle.code_post.common.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseDTO<T> {
    @Builder.Default
    private String code = "success";
    private T data;
    @Builder.Default
    private String message = "ok";
}
