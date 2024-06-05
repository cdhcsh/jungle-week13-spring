package org.jungle.code_post.post.service;

import org.jungle.code_post.post.dto.PostCreateRequestDTO;
import org.jungle.code_post.post.dto.PostResponseDTO;
import org.jungle.code_post.post.dto.PostVO;
import org.jungle.code_post.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceNoAuth implements PostService{

    @Autowired
    private PostRepository postRepository;


    @Override
    public List<PostResponseDTO> getAllPost() {
        return postRepository.findAll().stream()
                .map(PostResponseDTO::of)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponseDTO insertPost(PostVO postDTO) {
        return PostResponseDTO.of(postRepository.save(postDTO.toEntity()));
    }
}
