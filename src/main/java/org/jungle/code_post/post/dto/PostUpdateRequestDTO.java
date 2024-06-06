package org.jungle.code_post.post.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequestDTO {
    private String title;
    private String content;
    private String link;
    private String category;
    private Integer score = 0;
    private Integer password;

    public PostVO toVO(){
        return PostVO.builder()
                .title(title)
                .content(content)
                .link(link)
                .category(category)
                .score(score)
                .password(password)
                .build();
    }
}
