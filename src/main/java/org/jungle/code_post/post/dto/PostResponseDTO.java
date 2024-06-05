package org.jungle.code_post.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jungle.code_post.post.entity.Post;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String link;
    private String category;
    private Integer score;
    private String author;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static PostResponseDTO of(PostVO post) {
        return PostResponseDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .link(post.getLink())
                .category(post.getCategory())
                .score(post.getScore())
                .author(post.getAuthor())
                .created_at(post.getCreatedAt())
                .updated_at(post.getUpdatedAt())
                .build();
    }
}
