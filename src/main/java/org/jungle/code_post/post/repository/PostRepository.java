package org.jungle.code_post.post.repository;

import org.jungle.code_post.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
