package org.jungle.code_post.comment.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jungle.code_post.common.entity.BaseTimestampEntity;
import org.jungle.code_post.post.entity.Post;

@Builder
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;


}
