package org.jungle.code_post.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateRequestDTO {

    @NotNull
    @NotBlank
    @Length(max = 256)
    private String content;

    public CommentVO toVO() {
        return CommentVO.builder()
                .content(content)
                .build();
    }
}

