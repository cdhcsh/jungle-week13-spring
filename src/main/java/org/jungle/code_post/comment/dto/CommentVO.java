package org.jungle.code_post.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jungle.code_post.comment.entity.Comment;
import org.jungle.code_post.post.dto.PostVO;
import org.jungle.code_post.post.entity.Post;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class CommentVO {
    private final Long id;
    private final String content;
    private final String author;
    private final Long post_id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


    public Comment toEntity(){
        return Comment.builder()
                .id(id)
                .content(content)
                .author(author)
                .post(Post.builder().id(post_id).build())
                .build();
    }
}
