package org.jungle.code_post.post.dto;

import lombok.*;
import org.jungle.code_post.post.entity.Post;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDTO {
    private String title;
    private String content;
    private String link;
    private String category;
    private Integer score = 0;
    private String author;
    private Integer password;

    public PostVO toVO(){
        return PostVO.builder()
                .title(title)
                .content(content)
                .link(link)
                .category(category)
                .score(score)
                .author(author)
                .password(password)
                .build();
    }
}
