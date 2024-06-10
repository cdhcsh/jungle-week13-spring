package org.jungle.code_post.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequestDTO {
    @NotNull
    @NotBlank
    @Length(max = 256)
    private String title;

    @NotNull
    @NotBlank
    @Length(max = 8096)
    private String content;

    @NotNull
    @NotBlank
    @Length(max = 256)
    private String link;

    @NotNull
    @NotBlank
    @Length(max = 256)
    private String category;

    @NotNull
    private Integer score = 0;


    public PostVO toVO(){
        return PostVO.builder()
                .title(title)
                .content(content)
                .link(link)
                .category(category)
                .score(score)
                .build();
    }
}
