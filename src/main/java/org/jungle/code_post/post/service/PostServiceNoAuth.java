package org.jungle.code_post.post.service;

import org.jungle.code_post.post.dto.PostCreateRequestDTO;
import org.jungle.code_post.post.dto.PostResponseDTO;
import org.jungle.code_post.post.dto.PostVO;
import org.jungle.code_post.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceNoAuth implements PostService{

    @Autowired
    private PostRepository postRepository;


    @Override
    public PostResponseDTO insertPost(PostVO postDTO) {
        return PostResponseDTO.of(postRepository.save(postDTO.toEntity()));
    }
}
