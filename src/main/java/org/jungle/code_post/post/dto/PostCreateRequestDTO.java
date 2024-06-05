package org.jungle.code_post.post.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jungle.code_post.post.entity.Post;

@Getter
@RequiredArgsConstructor
public class PostCreateRequestDTO {
    private final String title;
    private final String content;
    private final String link;
    private final String category;
    private final Integer score;
    private final String author;
    private final Integer password;

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
