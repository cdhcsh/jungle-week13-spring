package org.jungle.code_post.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.comment.dto.CommentResponseDTO;
import org.jungle.code_post.comment.entity.Comment;
import org.jungle.code_post.post.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
public class PostResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String link;
    private String category;
    private Integer score;
    private String author;
    private List<CommentResponseDTO> comments;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static PostResponseDTO of(Post post) {
        return PostResponseDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .link(post.getLink())
                .content(post.getContent())
                .category(post.getCategory())
                .score(post.getScore())
                .author(post.getAuthor())
                .comments(post.getComments().stream()
                        .map(CommentResponseDTO::of)
                        .collect(Collectors.toList()))
                .created_at(post.getCreatedAt())
                .updated_at(post.getUpdatedAt())
                .build();
    }
}
