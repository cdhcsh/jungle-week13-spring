package org.jungle.code_post.post.controller;

import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.post.dto.PostCreateRequestDTO;
import org.jungle.code_post.post.dto.PostResponseDTO;
import org.jungle.code_post.post.service.PostService;
import org.jungle.code_post.post.service.PostServiceNoAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@Slf4j
public class PostController {

    @Autowired
    PostService postService = new PostServiceNoAuth();

    @GetMapping
    public List<PostResponseDTO> findAllPost(){
        return postService.getAllPost();
    }
    @PostMapping
    public PostResponseDTO createPost(@RequestBody PostCreateRequestDTO requestDTO) {
        return postService.insertPost(requestDTO.toVO());
    }

}
