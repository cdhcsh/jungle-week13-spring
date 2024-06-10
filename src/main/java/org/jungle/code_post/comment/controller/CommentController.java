package org.jungle.code_post.comment.controller;

import org.jungle.code_post.auth.jwt.dto.MemberDetails;
import org.jungle.code_post.comment.dto.CommentCreateRequestDTO;
import org.jungle.code_post.comment.dto.CommentResponseDTO;
import org.jungle.code_post.comment.dto.CommentUpdateRequestDTO;
import org.jungle.code_post.comment.service.CommentService;
import org.jungle.code_post.comment.service.CommentServiceImpl;
import org.jungle.code_post.common.dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService = new CommentServiceImpl();

    @PostMapping
    @PreAuthorize("hasAnyRole('MEMBER_ROLE_USER','MEMBER_ROLE_ADMIN')")
    public CommentResponseDTO repleComment(@Validated @RequestBody CommentCreateRequestDTO requestDTO
            , @AuthenticationPrincipal MemberDetails memberDetails) {
        return commentService.insertComment(requestDTO.toVO(), memberDetails);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEMBER_ROLE_USER','MEMBER_ROLE_ADMIN')")
    public CommentResponseDTO editComment(@PathVariable Long id
            , @Validated @RequestBody CommentUpdateRequestDTO requestDTO
            , @AuthenticationPrincipal MemberDetails memberDetails) {
        return commentService.updateComment(id,requestDTO.toVO(), memberDetails);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MEMBER_ROLE_USER','MEMBER_ROLE_ADMIN')")
    public MessageResponseDTO deleteComment(@PathVariable Long id
            , @AuthenticationPrincipal MemberDetails memberDetails) {
        return commentService.deleteComment(id, memberDetails);
    }

}
