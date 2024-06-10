package org.jungle.code_post.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jungle.code_post.member.dto.MemberVO;
import org.jungle.code_post.post.entity.Post;

import java.time.LocalDateTime;

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
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public static PostVO of(Post post){
        return PostVO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .link(post.getLink())
                .category(post.getCategory())
                .score(post.getScore())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
    public Post toEntity(){
        return Post.builder()
                .id(id)
                .title(title)
                .content(content)
                .link(link)
                .category(category)
                .score(score)
                .build();
    }
}
