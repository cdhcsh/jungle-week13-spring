package org.jungle.code_post.post.controller;

import lombok.extern.slf4j.Slf4j;
import org.jungle.code_post.auth.jwt.dto.MemberDetails;
import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.jungle.code_post.post.dto.PostCreateRequestDTO;
import org.jungle.code_post.post.dto.PostResponseDTO;
import org.jungle.code_post.post.dto.PostUpdateRequestDTO;
import org.jungle.code_post.post.service.PostService;
import org.jungle.code_post.post.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@Slf4j
public class PostController {

    @Autowired
    PostService postService = new PostServiceImpl();

    @GetMapping("/{id}")
    public PostResponseDTO findPost(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    @GetMapping
    public List<PostResponseDTO> findAllPost() {
        return postService.findAllPost();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('MEMBER_ROLE_USER','MEMBER_ROLE_ADMIN')")
    public PostResponseDTO createPost(@Validated @RequestBody PostCreateRequestDTO requestDTO
            , @AuthenticationPrincipal MemberDetails memberDetails) {
        return postService.insertPost(requestDTO.toVO(), memberDetails);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEMBER_ROLE_USER','MEMBER_ROLE_ADMIN')")
    public PostResponseDTO updatePost(@PathVariable Long id
            , @Validated @RequestBody PostUpdateRequestDTO requestDTO
            , @AuthenticationPrincipal MemberDetails memberDetails) {
        return postService.updatePost(id, requestDTO.toVO(), memberDetails);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEMBER_ROLE_USER','MEMBER_ROLE_ADMIN')")
    public MessageResponseDTO deletePost(@PathVariable Long id
            , @AuthenticationPrincipal MemberDetails memberDetails) {
        return postService.deletePost(id, memberDetails);
    }

}
