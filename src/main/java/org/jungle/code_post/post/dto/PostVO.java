package org.jungle.code_post.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jungle.code_post.post.entity.Post;

@Getter
@Builder
@RequiredArgsConstructor
public class PostVO {
    private final Long id;
    private final String title;
    private final String content;
    private final String link;
    private final String category;
    private final Integer score;
    private final String author;
    private final int password;

    public Post toEntity(){
        return Post.builder()
                .id(id)
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
