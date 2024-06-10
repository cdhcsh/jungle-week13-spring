package org.jungle.code_post.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.jungle.code_post.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public static CommentResponseDTO of(Comment comment){
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getAuthor())
                .created_at(comment.getCreatedAt())
                .updated_at(comment.getUpdatedAt())
                .build();
    }
}
