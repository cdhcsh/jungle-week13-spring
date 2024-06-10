package org.jungle.code_post.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.jungle.code_post.post.dto.PostVO;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequestDTO {

    @NotNull
    private Long post_id;

    @NotNull
    @NotBlank
    @Length(max = 256)
    private String content;

    public CommentVO toVO(){
        return CommentVO.builder()
                .post_id(post_id)
                .content(content)
                .build();
    }
}
