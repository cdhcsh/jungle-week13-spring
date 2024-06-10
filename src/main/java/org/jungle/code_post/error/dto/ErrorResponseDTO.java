package org.jungle.code_post.error.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {
    private String field;
    private String code;
    private String message;
}
