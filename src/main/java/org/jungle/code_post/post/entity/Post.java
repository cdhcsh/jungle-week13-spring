package org.jungle.code_post.post.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jungle.code_post.comment.entity.Comment;
import org.jungle.code_post.common.entity.BaseTimestampEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimestampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,length = 8096)
    private String content;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private String category;

    private Integer score;

    @Column(nullable = false)
    private String author;

    private int password;

    @OneToMany(mappedBy = "post",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("createdAt desc")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();


}
