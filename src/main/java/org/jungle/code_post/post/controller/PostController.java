package org.jungle.code_post.post.controller;

import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.post.dto.PostCreateRequestDTO;
import org.jungle.code_post.post.dto.PostResponseDTO;
import org.jungle.code_post.post.service.PostService;
import org.jungle.code_post.post.service.PostServiceNoAuth;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@Slf4j
public class PostController {

    PostService postService = new PostServiceNoAuth();

    @PostMapping
    public PostResponseDTO createPost(@RequestBody PostCreateRequestDTO requestDTO) {
        return postService.insertPost(requestDTO.toVO());
    }

}
